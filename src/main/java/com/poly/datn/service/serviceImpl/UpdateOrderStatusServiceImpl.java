package com.poly.datn.service.serviceImpl;

import com.poly.datn.repository.OrderRepository;
import com.poly.datn.service.UpdateOrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class UpdateOrderStatusServiceImpl implements UpdateOrderStatusService {
    private final OrderRepository repository;

    @Override
    public void updateStatusToReceive(Integer idOrder) {
        repository.updateStatusToReceive(idOrder);
    }

    @Override
    public void updateStatusToCompleted(Integer idOrder) {
        repository.updateStatusToCompleted(idOrder);
    }

    @Override
    public void updateStatusToCancelled(Integer idOrder) {
        repository.updateStatusToCancelled(idOrder);
    }
}
