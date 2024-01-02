package com.app.taskflow.services.impl;

import com.app.taskflow.common.exception.custom.OperationException;
import com.app.taskflow.common.exception.custom.OrderException;
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
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
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
        return null;
    }
}
