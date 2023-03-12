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
        Map<String, Object> lastWeek =new HashMap<>();
        lastWeek.put("Mon", orderDetailRepository.envennuByWeekdayOfLastWeek(2));
        lastWeek.put("Tue", orderDetailRepository.envennuByWeekdayOfLastWeek(3));
        lastWeek.put("Wed", orderDetailRepository.envennuByWeekdayOfLastWeek(4));
        lastWeek.put("Thu", orderDetailRepository.envennuByWeekdayOfLastWeek(5));
        lastWeek.put("Fri", orderDetailRepository.envennuByWeekdayOfLastWeek(6));
        lastWeek.put("Sat", orderDetailRepository.envennuByWeekdayOfLastWeek(7));
        lastWeek.put("Sun", orderDetailRepository.envennuByWeekdayOfLastWeek(1));
        Map<String, Object> thisWeek =new HashMap<>();
        lastWeek.put("Mon", orderDetailRepository.envennuByWeekdayOfThisWeek(2));
        lastWeek.put("Tue", orderDetailRepository.envennuByWeekdayOfThisWeek(3));
        lastWeek.put("Wed", orderDetailRepository.envennuByWeekdayOfThisWeek(4));
        lastWeek.put("Thu", orderDetailRepository.envennuByWeekdayOfThisWeek(5));
        lastWeek.put("Fri", orderDetailRepository.envennuByWeekdayOfThisWeek(6));
        lastWeek.put("Sat", orderDetailRepository.envennuByWeekdayOfThisWeek(7));
        lastWeek.put("Sun", orderDetailRepository.envennuByWeekdayOfThisWeek(1));
        revenue.setLastWeek(lastWeek);
        revenue.setThisWeek(thisWeek);
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
