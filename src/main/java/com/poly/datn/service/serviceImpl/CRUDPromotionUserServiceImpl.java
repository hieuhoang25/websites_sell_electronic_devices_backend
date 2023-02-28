package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.PromotionUserRequest;
import com.poly.datn.dto.response.PromotionUserResponse;
import com.poly.datn.entity.PromotionType;
import com.poly.datn.entity.PromotionUser;
import com.poly.datn.entity.User;
import com.poly.datn.repository.PromotionTypeRepository;
import com.poly.datn.repository.PromotionUserRepository;
import com.poly.datn.repository.UserRepository;
import com.poly.datn.service.CRUDPromotionUserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CRUDPromotionUserServiceImpl implements CRUDPromotionUserService {
    private final PromotionUserRepository promotionUserRepository;
    private final UserRepository userRepository;
    private final PromotionTypeRepository promotionTypeRepository;
    private final ModelConverter modelConverter;
    @Override
    public PromotionUserResponse create(PromotionUserRequest promotionUserRequest) {
        PromotionUser promotionUser = modelConverter.map(promotionUserRequest, PromotionUser.class);
        PromotionType promotionType = getPromotionType(promotionUserRequest.getPromotion_type_id());
        User user = getUser(promotionUserRequest.getUser_id());
        promotionUser.setUser(user);
        promotionUser.setPromotionType(promotionType);
        return modelConverter.map(promotionUser,PromotionUserResponse.class );

    }

    @Override
    public PromotionUserResponse update(PromotionUserRequest promotionUserRequest) {
        PromotionUser promotionUser = modelConverter.map(promotionUserRequest, PromotionUser.class);
        PromotionType promotionType = getPromotionType(promotionUserRequest.getPromotion_type_id());
        User user = getUser(promotionUserRequest.getUser_id());
        promotionUser.setUser(user);
        promotionUser.setPromotionType(promotionType);
        return modelConverter.map(promotionUser,PromotionUserResponse.class );
    }

    private PromotionType getPromotionType(Integer promotionTypeId) {
        return promotionTypeRepository.findById(promotionTypeId).orElseThrow(() ->  new EntityNotFoundException("PromotionType not found"));
    }


    private User getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() ->  new EntityNotFoundException("User not found"));
    }

    @Override
    public void delete(Integer id) {
        promotionUserRepository.deleteById(id);
    }

    @Override
    public List<PromotionUserResponse> findAll() {
        return modelConverter.mapAllByIterator(promotionUserRepository.findAll(),PromotionUserResponse.class );
    }

    @Override
    public PromotionUserResponse findById(Integer id) {
        return modelConverter.map(promotionUserRepository.findById(id), PromotionUserResponse.class);
    }
}
