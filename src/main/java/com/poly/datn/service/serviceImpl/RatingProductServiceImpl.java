package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.RatingProductRequest;
import com.poly.datn.dto.response.ProductRatingResponse;
import com.poly.datn.entity.Rating;
import com.poly.datn.repository.RatingRepository;
import com.poly.datn.service.RatingProductService;
import com.poly.datn.service.UserInfoByTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class RatingProductServiceImpl implements RatingProductService {
    private final ModelConverter converter;
    private final RatingRepository repository;
    private final UserInfoByTokenService userInfoByTokenService;

    @Override
    public ProductRatingResponse rateProduct(RatingProductRequest request) {
        Rating rating = converter.map(request, Rating.class);
        rating.setUser(userInfoByTokenService.getCurrentUser());
        ProductRatingResponse ratingResponse = converter.map(repository.save(rating), ProductRatingResponse.class);
        return ratingResponse;
    }

    @Override
    public Boolean isRating(Integer productId, Integer orderDetailId) {
        Boolean check = repository.isRating(productId, userInfoByTokenService.getCurrentUser().getId(), orderDetailId);
        if (check == null)
            return false;
        return check;
    }
}
