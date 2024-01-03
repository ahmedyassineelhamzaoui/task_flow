package com.app.taskflow.services.impl;

import com.app.taskflow.common.exception.custom.OperationException;
import com.app.taskflow.common.exception.custom.OrderException;
import com.app.taskflow.enums.OrderStatus;
import com.app.taskflow.mapper.DemandMapper;
import com.app.taskflow.mapper.UserTableMapper;
import com.app.taskflow.models.dto.DemandDTO;
import com.app.taskflow.models.dto.request.UpdateDemandRequest;
import com.app.taskflow.models.entity.Demand;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.OrderRepository;
import com.app.taskflow.repositories.TaskRepository;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final DemandMapper demandMapper;
    private final UserTableMapper userTableMapper;
    @Override
    public void addOrder(DemandDTO demandDTO) {
           Task task  = taskRepository.findById(demandDTO.getTask().getId()).orElseThrow(() -> new NoSuchElementException("task not found"));
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserTable user = (UserTable) authentication.getPrincipal();
           if(demandDTO.getOperationType().equals("MODIFICATION")) {
               if(!user.getId().equals(task.getAssignedTo().getId())){
                   throw  new OrderException("this task not assign to you to update it");
               }
               if(task.isTaskAlreadyTakeJeton()){
                   throw  new OrderException("we can not change this task for you because is already change it by someone ");
               }
               if(user.getModificationCredit()<1){
                   throw  new OrderException("you do not have the necessary modification credit to update this task");
               }
               demandDTO.setDemandBy(userTableMapper.toDTO(user));
               orderRepository.save(demandMapper.toEntity(demandDTO));
           }else if (demandDTO.getOperationType().equals("DELETION")){
               if(!user.getId().equals(task.getAssignedTo().getId())){
                   throw  new OrderException("this task not assign to you to update it");
               }
               if(task.isTaskAlreadyTakeJeton()){
                   throw  new OrderException("we can not change this task for you because is already change it by someone ");
               }
               if(user.getDeletionCredit()<1){
                   throw  new OrderException("you do not have the necessary deletion credit to delete this task");
               }
               demandDTO.setDemandBy(userTableMapper.toDTO(user));
               orderRepository.save(demandMapper.toEntity(demandDTO));
           }else{
                  throw new OperationException("invalid operation type");
           }
    }

    @Override
    public List<DemandDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(demandMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrderById(UUID id) {

    }

    @Override
    public DemandDTO getOrderById(UUID id) {
        return null;
    }

    @Override
    public UpdateDemandRequest updateOrder(UUID id,UpdateDemandRequest updateDemandRequest) {
        Demand demand = orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("order not found that you want to update not found"));

        UserTable user = userRepository.findById(updateDemandRequest.getAssignedTo().getId()).orElseThrow(() -> new NoSuchElementException("user that you want to assign to it the task not exist "));
        Task task = taskRepository.findById(updateDemandRequest.getNewTask().getId()).orElseThrow(() -> new NoSuchElementException("task that you want to assign to user not exist"));
        if(task.getAssignedTo() != null){
            throw new OrderException("this task already assign to another user");
        }
        if(!demand.getStatus().equals("ACCEPTED")){
            throw new OrderException("you should update only order with status ACCEPTED because the user will benefit from the credit of modification X 2");
        }
        if(demand.getOperationType().equals("MODIFICATION")){
            user.setModificationCredit(user.getModificationCredit() - 1);
        } else if (demand.getOperationType().equals("DELETION")){
            user.setDeletionCredit(user.getDeletionCredit() - 1);
        }
        userRepository.save(user);
        task.setTaskAlreadyTakeJeton(true);
        task.setAssignedTo(user);
        taskRepository.save(task);
        demand.setStatus(OrderStatus.ACCEPTED);
        return demandMapper.toUpdateDemandRequest(orderRepository.save(demand));
    }

    @Scheduled(fixedRate = 3600000)
    public void verifyManagerResponse() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -12);
        Date currentTimeMinus12Hours = cal.getTime();

        List<Demand> orders = orderRepository.findAll().stream()
                .filter(order -> order.getCreatedAt().before(currentTimeMinus12Hours) && !order.getStatus().equals( "ACCEPTED"))
                .collect(Collectors.toList());

        for (Demand order : orders) {
            UserTable user = order.getDemandBy();
            if(order.getOperationType().equals("MODIFICATION")) {
                user.setModificationCredit(user.getModificationCredit() + 2);
                userRepository.save(user);
            }
        }
    }
}
