package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.constant.EOrderStatus;
import com.poly.datn.dto.request.OderStatusChangeRequest;
import com.poly.datn.entity.Order;
import com.poly.datn.entity.OrderStatus;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.repository.OrderStatusRepository;
import com.poly.datn.service.MailService;
import com.poly.datn.service.OrderStatusChangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
@AllArgsConstructor
@Slf4j
public class OrderStatusChangeServiceImpl implements OrderStatusChangeService {
    private  OrderRepository orderRepository;
    private  OrderStatusRepository orderStatusRepository;

    private  MailService mailService;
    @Override
    public void changeOrderStatus(OderStatusChangeRequest request) {
          Order o = orderRepository.findById(request.getOrder().getId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng này!"));
        if (o.getStatus().getName().equalsIgnoreCase(EOrderStatus.DELIVERED.getName())){
            log.info("Don't send---mail");
            throw new RuntimeException("Đơn hàng đã được giao hàng!");
        }
        if (o.getStatus().getName().equalsIgnoreCase(EOrderStatus.DELIVERING.getName())){
            OrderStatus os =   orderStatusRepository.findById(EOrderStatus.DELIVERED.getI())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm trạng thái đơn hàng này!"));
            o.setStatus(os);
            orderRepository.save(o);
            log.info("Send---mail");
            mailService.sendOrderStatusMail(request.getOrder().getUser_fullName(),
                    request.getOrder().getUser_email(),
                    os.getName());
        }
        if (o.getStatus().getName().equalsIgnoreCase(EOrderStatus.PENDING.getName())){
            // change status for order
            OrderStatus os =   orderStatusRepository.findById(EOrderStatus.DELIVERING.getI())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm trạng thái đơn hàng này!"));
            o.setStatus(os);
            orderRepository.save(o);
            log.info("Send---mail");
            mailService.sendOrderStatusMail(request.getOrder().getUser_fullName(),
                    request.getOrder().getUser_email(),
                    os.getName());
            // send mail
        }

    }
}
