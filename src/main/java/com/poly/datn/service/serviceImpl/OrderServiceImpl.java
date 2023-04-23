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

import java.util.Comparator;
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
                    double sum = 0;
                    for (OrderDetailResponse od:
                         o.getOrderDetails()) {
                        double pv = od.getPromotion_value() == null ? 0 : od.getPromotion_value();
                        sum+= od.getPrice_sum() - pv*od.getQuantity();
                    }
                    o.setSum(sum);
                });
        return list;

    }
}
