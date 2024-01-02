package com.app.taskflow.services.facade;

import com.app.taskflow.models.dto.DemandDTO;
import com.app.taskflow.models.dto.request.UpdateDemandRequest;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    void addOrder(DemandDTO demandDTO);

    List<DemandDTO> getAllOrders();

    void deleteOrderById(UUID id);

    DemandDTO getOrderById(UUID id);

    UpdateDemandRequest updateOrder(UUID id,UpdateDemandRequest updateDemandRequest);
}
