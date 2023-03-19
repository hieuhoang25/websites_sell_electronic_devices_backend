package com.poly.datn.service.serviceImpl;

import com.poly.datn.dto.response.ProductSellingTop;
import com.poly.datn.dto.response.RevenueByWeekResponse;
import com.poly.datn.dto.response.SpeciallyStatisticalResponse;
import com.poly.datn.repository.OrderDetailRepository;
import com.poly.datn.repository.OrderRepository;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.StatisticalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public RevenueByWeekResponse statisticRevenueByWeek() {
        RevenueByWeekResponse revenue = new RevenueByWeekResponse();
        Map<String, Object> lastMonth =new HashMap<>();
        lastMonth.put("Mon", orderDetailRepository.envennuByWeekdayOfLastMonth(0));
        lastMonth.put("Tue", orderDetailRepository.envennuByWeekdayOfLastMonth(1));
        lastMonth.put("Wed", orderDetailRepository.envennuByWeekdayOfLastMonth(2));
        lastMonth.put("Thu", orderDetailRepository.envennuByWeekdayOfLastMonth(3));
        lastMonth.put("Fri", orderDetailRepository.envennuByWeekdayOfLastMonth(4));
        lastMonth.put("Sat", orderDetailRepository.envennuByWeekdayOfLastMonth(5));
        lastMonth.put("Sun", orderDetailRepository.envennuByWeekdayOfLastMonth(6));
        Map<String, Object> thisMonth =new HashMap<>();
        thisMonth.put("Mon", orderDetailRepository.envennuByWeekdayOfThisMonth(0));
        thisMonth.put("Tue", orderDetailRepository.envennuByWeekdayOfThisMonth(1));
        thisMonth.put("Wed", orderDetailRepository.envennuByWeekdayOfThisMonth(2));
        thisMonth.put("Thu", orderDetailRepository.envennuByWeekdayOfThisMonth(3));
        thisMonth.put("Fri", orderDetailRepository.envennuByWeekdayOfThisMonth(4));
        thisMonth.put("Sat", orderDetailRepository.envennuByWeekdayOfThisMonth(5));
        thisMonth.put("Sun", orderDetailRepository.envennuByWeekdayOfThisMonth(6));
        revenue.setLastMonth(lastMonth);
        revenue.setThisMonth(thisMonth);
        return revenue;
    }

    @Override
    public SpeciallyStatisticalResponse statisticSpecially() {
        SpeciallyStatisticalResponse response = new SpeciallyStatisticalResponse();
        response.setNumberOfUser(userRepository.findAll().size());
        response.setProductSelled(orderDetailRepository.countSelled());
        response.setWeekEnvenue(orderDetailRepository.revenue(LocalDateTime.now().minusWeeks(1),LocalDateTime.now()));
        response.setOrderYetApproved(orderRepository.countOrderYetApprove());
        return response;
    }

    @Override
    public List<ProductSellingTop> statisticProductSellingTop() {
        return null;
    }
}
