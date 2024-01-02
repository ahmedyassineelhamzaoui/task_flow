package com.app.taskflow.services.impl;

import com.app.taskflow.models.dto.DemandDTO;
import com.app.taskflow.services.facade.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public void addOrder(DemandDTO demandDTO) {

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
