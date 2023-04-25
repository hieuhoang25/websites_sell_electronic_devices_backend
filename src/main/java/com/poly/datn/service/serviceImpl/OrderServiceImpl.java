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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private OrderDetailRepository orderDetailRepository;

    private ModelConverter modelConverter;



    @Override
    public List<OrdersUserResponse> findAll() {
        List<OrdersUserResponse> list= modelConverter.mapAllByIterator(orderRepository.findAll(Sort.by( "createdDate").descending()), OrdersUserResponse.class);
        Comparator<OrdersUserResponse> comparator = new Comparator<OrdersUserResponse>() {
            @Override
            public int compare(OrdersUserResponse o1, OrdersUserResponse o2) {
                String status1 =  o1.getStatus_name();
                String status2 = o2.getStatus_name();
               if (status1.equalsIgnoreCase("Chờ xác nhận") && !status2.equalsIgnoreCase("Chờ xác nhận"))
                return -1;
               else
                   if  (status1.equalsIgnoreCase("Đang giao") && !status2.equalsIgnoreCase("Đang giao"))
                       return 1;
                   else
                       if (status1.equalsIgnoreCase("Chờ xác nhận")&&status2.equalsIgnoreCase("Đang giao"))
                           return -1;
                       else
                           if (status1.equalsIgnoreCase("Đang giao")&&status2.equalsIgnoreCase("Chờ xác nhận"))
                               return 1;
                           return 0;
            }

        } ;
        list.stream()
                .sorted(comparator)
                .forEach(o -> {
                    o.setSum(o.getTotal());
                })
                ;
        return list;

    }

    @Override
    public List<OrdersUserResponse> findAll(List<String> status) {
        log.info("{}",status);
        if (status.isEmpty()) return findAll();
        else{
            return findAll()
                    .stream()
                    .filter(o->status.contains(o.getStatus_name()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Map<String, Object> countOrdersByStatus() {
        Map<String, Object> keyValue = new HashMap<>();
        keyValue.put("CXN", orderRepository.countNumberOfOrderByStatus(1));// Chờ xác nhận
        keyValue.put("DG", orderRepository.countNumberOfOrderByStatus(2));//Đang giao
        keyValue.put("HT", orderRepository.countNumberOfOrderByStatus(3));//Hoàn thành
        keyValue.put("DH", orderRepository.countNumberOfOrderByStatus(4));//Đã hủy
        return keyValue;
    }
}
