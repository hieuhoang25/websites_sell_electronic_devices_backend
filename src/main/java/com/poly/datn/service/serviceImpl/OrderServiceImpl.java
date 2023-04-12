package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrderDetailResponse;
import com.poly.datn.dto.response.OrdersUserResponse;
import com.poly.datn.dto.response.ProductVariantResponse;
import com.poly.datn.entity.Order;
import com.poly.datn.repository.OrderDetailRepository;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private OrderDetailRepository orderDetailRepository;

    private ModelConverter modelConverter;


    @Override
    public List<OrdersUserResponse> findAll() {
        List<OrdersUserResponse> list= modelConverter.mapAllByIterator(orderRepository.findAll(Sort.by( "createdDate").descending()), OrdersUserResponse.class);
        list.stream()
                .forEach(o -> {
                    double sum = 0;
                    for (OrderDetailResponse od:
                         o.getOrderDetails()) {
                        double pv = od.getPromotion_value() == null ? 0 : od.getPromotion_value();
                        sum+= od.getPrice_sum() - pv;
                    }
                    o.setSum(sum);
                });
        return list;

    }
}
