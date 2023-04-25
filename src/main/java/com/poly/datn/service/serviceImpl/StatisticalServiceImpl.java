package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.response.OrdersUserResponse;
import com.poly.datn.dto.response.ProductSellingTop;
import com.poly.datn.dto.response.RevenueByWeekResponse;
import com.poly.datn.dto.response.SpeciallyStatisticalResponse;
import com.poly.datn.repository.OrderDetailRepository;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.StatisticalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {
    private OrderDetailRepository orderDetailRepository;
    private ProductVariantRepository productVariantRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    private ModelConverter modelConverter;
    @Override
    public RevenueByWeekResponse statisticRevenueByWeek() {
        int lastMonth = LocalDate.now().minusMonths(1).getMonthValue();
        int thisMonth = LocalDate.now().getMonthValue();
        RevenueByWeekResponse revenue = new RevenueByWeekResponse();
        String[] daysOfWeek = new String[] {"Mon", "Tue", "Wed", "Thu","Fri","Sat","Sun"};//1,2,3,4,5,6,7
        List<OrdersUserResponse> list= modelConverter.mapAllByIterator(orderRepository.findAll(), OrdersUserResponse.class);
        Map<String, Object> revenueOfLastMonth =new HashMap<>();
        Map<String, Object> revenueOfThisMonth =new HashMap<>();
        for (int i = 0; i < daysOfWeek.length; i++) {
            int index = i+1;
          Double sumOfDayInLastMonth = list.stream()
                    .filter(o -> {
                        LocalDateTime create_date = o.getCreated_date().atZone(ZoneId.systemDefault()).toLocalDateTime();
                       return create_date.getMonthValue() == lastMonth && create_date.getDayOfWeek().getValue()==index && o.getIs_pay()==true;
                    })
                    .mapToDouble(o -> o.getTotal())
                    .sum();
          Double sumOfDayInThisMonth = list.stream()
                  .filter(o -> {
                      LocalDateTime create_date = o.getCreated_date().atZone(ZoneId.systemDefault()).toLocalDateTime();
                      return create_date.getMonthValue() == thisMonth && create_date.getDayOfWeek().getValue()==index && o.getIs_pay()==true;
                  })
                  .mapToDouble(o -> o.getTotal())
                  .sum();
            revenueOfLastMonth.put(daysOfWeek[i],sumOfDayInLastMonth);
            revenueOfThisMonth.put(daysOfWeek[i],sumOfDayInThisMonth);
        }

        revenue.setLastMonth(revenueOfLastMonth);
        revenue.setThisMonth(revenueOfThisMonth);
        return revenue;
    }

    @Override
    public SpeciallyStatisticalResponse statisticSpecially() {
        SpeciallyStatisticalResponse response = new SpeciallyStatisticalResponse();
        response.setNumberOfUser(userRepository.findAll().size());
        response.setProductSelled(orderDetailRepository.countSelled());
        response.setWeekEnvenue(
                orderRepository.findAll().stream()
                        .filter(o->o.getIsPay())
                        .mapToDouble(o->o.getTotal())
                        .sum()
        );
        response.setOrderYetApproved(orderRepository.countOrderYetApprove());
        return response;
    }

    @Override
    public List<LinkedCaseInsensitiveMap<String>>  statisticProductSellingTop() {
        return productVariantRepository.productSellingTops();
    }
}
