package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.RatingProductRequest;
import com.poly.datn.dto.response.OrderDetailResponse;
import com.poly.datn.dto.response.ProductRatingResponse;
import com.poly.datn.entity.OrderDetail;
import com.poly.datn.entity.Rating;
import com.poly.datn.repository.RatingRepository;
import com.poly.datn.service.RatingProductService;
import com.poly.datn.service.UserInfoByTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class RatingProductServiceImpl implements RatingProductService {
    private final ModelConverter converter;
    private final RatingRepository repository;
    private final UserInfoByTokenService userInfoByTokenService;

    @Override
    public void rateProduct(List<RatingProductRequest> request) {
        List<Rating> rating = converter.mapAllByIterator(request, Rating.class);
        rating.stream().forEach(rt -> {
            rt.setUser(userInfoByTokenService.getCurrentUser());
        });
        repository.saveAllAndFlush(rating);
//        List<ProductRatingResponse> ratingResponse =
//                converter.mapAllByIterator(repository.saveAll(rating), ProductRatingResponse.class);
    }

    @Override
    public List<OrderDetailResponse> isRating(Integer orderId) {
        List<OrderDetail> orderDetails =
                repository.isRating(orderId,userInfoByTokenService.getCurrentUser().getId());
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
        if (orderDetails.isEmpty())
            return orderDetailResponses;
        orderDetailResponses = converter.mapAllByIterator(orderDetails, OrderDetailResponse.class);
        return orderDetailResponses;
    }
}
