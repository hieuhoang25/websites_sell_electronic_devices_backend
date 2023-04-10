package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.NotificationResponse;
import com.poly.datn.repository.NotificationRepository;
import com.poly.datn.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private ModelConverter modelConverter;
    @Override
    public List<NotificationResponse> getAll() {
        return modelConverter.mapAllByIterator(notificationRepository.findAll(),NotificationResponse.class);
    }

    @Override
    public List<NotificationResponse> delete(Integer id) {
        notificationRepository.deleteById(id);
        return getAll();
    }

    @Override
    public List<NotificationResponse> deleteAll() {
        notificationRepository.deleteAll();
        return getAll();
    }
}
