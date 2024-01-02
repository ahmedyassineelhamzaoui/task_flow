package com.app.taskflow.services.impl;

import com.app.taskflow.mapper.DemandMapper;
import com.app.taskflow.models.dto.DemandDTO;
import com.app.taskflow.models.entity.Demand;
import com.app.taskflow.models.entity.Task;
import com.app.taskflow.models.entity.UserTable;
import com.app.taskflow.repositories.OrderRepository;
import com.app.taskflow.repositories.TaskRepository;
import com.app.taskflow.repositories.UserRepository;
import com.app.taskflow.services.facade.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final DemandMapper demandMapper;

    @Override
    public void addOrder(DemandDTO demandDTO) {
           Task task  = taskRepository.findById(demandDTO.getTask().getId()).orElseThrow(() -> new NoSuchElementException("task not found"));
           UserTable user    = userRepository.findById(demandDTO.getDemandBy().getId()).orElseThrow(() -> new NoSuchElementException("user not found"));
           if(demandDTO.getOperationType().equals("MODIFICATION")) {
               if(user.getId() != task.getAssignedTo().getId()){

               }
               if(task.isTaskAlreadyTakeJeton()){

               }
               if(user.getModificationCredit()<1){

               }
               orderRepository.save(demandMapper.toEntity(demandDTO));
           }else if (demandDTO.getOperationType().equals("DELETION")){
               if(user.getId() != task.getAssignedTo().getId()){

               }
               if(task.isTaskAlreadyTakeJeton()){

               }
               if(user.getDeletionCredit()<1){

               }
               orderRepository.save(demandMapper.toEntity(demandDTO));
           }else{

           }
    }

    @Override
    public List<DemandDTO> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrderById(UUID id) {

    }

    @Override
    public DemandDTO getOrderById(UUID id) {
        return null;
    }
}
