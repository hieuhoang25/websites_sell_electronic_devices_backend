package com.poly.datn.service.serviceImpl;

import static java.util.Comparator.comparing;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.FindAllUserPromoRequest;
import com.poly.datn.dto.response.FindAllUserPromoResponse;
import com.poly.datn.entity.PromotionUser;
import com.poly.datn.repository.PromotionUserRepository;
import com.poly.datn.repository.specification.PromoUserSpecification;
import com.poly.datn.service.FindAllUserPromotionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllUserPromotionServiceImpl implements FindAllUserPromotionService {

    private final PromotionUserRepository promoRepo;
    private final ModelConverter converter;

    @Override
    public List<FindAllUserPromoResponse> findAllUserPromo(Integer userId) {
        PromoUserSpecification specification = new PromoUserSpecification();
        try {
            List<FindAllUserPromoResponse> promoUser = converter
                    .mapAllByIterator(promoRepo.findAll(specification.ofUser(userId)), FindAllUserPromoResponse.class);

            if (promoUser == null || promoUser.size() == 0)
                return null;
            sortResponse(promoUser);
            return promoUser;
        } catch (Exception e) {
            log.info("ERROR: " + e.getMessage());
            return null;
        }

    }

    @Override
    public List<FindAllUserPromoResponse> findAllValidUserPromo(Integer userId) {
        try {
            Instant from = getInstantNow();
            PromoUserSpecification specification = new PromoUserSpecification();
            List<PromotionUser> promoList = promoRepo.findAll(specification.getValidPromo(from, userId));

            List<FindAllUserPromoResponse> promoUser = converter.mapAllByIterator(promoList,
                    FindAllUserPromoResponse.class);
            if (promoUser == null || promoUser.size() == 0)
                return null;
            sortResponse(promoUser);
            return promoUser;
        } catch (Exception e) {
            log.info("erorr: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<FindAllUserPromoResponse> findPromoByCode(Integer userId, String code) {
        try {
            Instant from = getInstantNow();
            PromoUserSpecification specification = new PromoUserSpecification();
            List<PromotionUser> promoList = promoRepo.findAll(specification.getByCodeAndUserId(code, userId));
            List<FindAllUserPromoResponse> promoUser = converter.mapAllByIterator(promoList,
                    FindAllUserPromoResponse.class);
            if (promoUser == null || promoUser.size() == 0) {
                throw new EntityNotFoundException("Mã giảm giá không hợp lệ");
            } else {
                promoUser.forEach(p -> {
                    if (p.getExpiration_date().isBefore(getInstantNow())) {
                        throw new IllegalArgumentException("Mã giảm hết hạn");
                    } else if (p.getStart_date().isAfter(getInstantNow())) {
                        String PATTERN_FORMAT = "dd/MM/yyyy hh:mm:ss";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                                .withZone(ZoneId.systemDefault());

                        String formattedInstant = formatter.format(p.getStart_date());
                        throw new IllegalArgumentException("Mã giảm có hiệu lực từ " + formattedInstant);
                    }

                });
            }

            return promoUser;
        } catch (Exception e) {
            log.info("erorr: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    @Override
    public List<FindAllUserPromoResponse> findPromoForOrder(Integer userId, FindAllUserPromoRequest request) {
        try {
            Instant from = getInstantNow();
            PromoUserSpecification specification = new PromoUserSpecification();
            List<PromotionUser> promoList = promoRepo
                    .findAll(specification.getValidPromoForOrder(from, request.getOrder_total(), userId));
            List<FindAllUserPromoResponse> promoUser = converter.mapAllByIterator(promoList,
                    FindAllUserPromoResponse.class);
            if (promoUser == null || promoUser.size() == 0)
                return null;
            sortResponse(promoUser);
            return promoUser;
        } catch (Exception e) {
            log.info("erorr: " + e.getMessage());
            return null;
        }

    }

    private void sortResponse(List<FindAllUserPromoResponse> promoUser) {
        promoUser.sort(comparing(FindAllUserPromoResponse::getExpiration_date)
                .thenComparing(FindAllUserPromoResponse::getStart_date));
    }

    private Instant getInstantNow() {
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        return LocalDateTime.now().toInstant(offset);
    }

    @Override
    public List<FindAllUserPromoResponse> findPromoByCode(Integer userId, FindAllUserPromoRequest request) {
        try {
            Double total = (request.getOrder_total() == null) ? 0.0 : request.getOrder_total();
            String code = request.getCode() == null ? null : request.getCode();

            if (code == null)
                throw new RuntimeException("Vui lòng nhập mã code cần kiểm");
            PromoUserSpecification specification = new PromoUserSpecification();
            List<PromotionUser> promoList = promoRepo.findAll(specification.getByCodeAndUserId(code, userId));
            List<FindAllUserPromoResponse> promoUser = converter.mapAllByIterator(promoList,
                    FindAllUserPromoResponse.class);
            if (promoUser == null || promoUser.size() == 0) {
                throw new EntityNotFoundException("Mã giảm giá không hợp lệ");
            } else {
                promoUser.forEach(p -> {
                    Integer minimum = p.getPromotion_type_conditionMinimum();
                    if (p.getExpiration_date().isBefore(getInstantNow())) {
                        throw new IllegalArgumentException("Mã giảm hết hạn");
                    } else if (p.getStart_date().isAfter(getInstantNow())) {
                        String PATTERN_FORMAT = "dd/MM/yyyy hh:mm:ss";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                                .withZone(ZoneId.systemDefault());

                        String formattedInstant = formatter.format(p.getStart_date());
                        throw new IllegalArgumentException("Mã giảm có hiệu lực từ " + formattedInstant);
                    } else if (minimum != null && minimum > total) {
                        throw new IllegalArgumentException(
                                "Đơn hàng phải bằng hoặc hơn " + minimum + " để áp dụng mã ");
                    }
                }
                );
            }
            return promoUser;
        } catch (Exception e) {
            log.info("erorr: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
