package com.poly.datn.service.serviceImpl;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.OrderDetailRequest;
import com.poly.datn.entity.ProductVariant;
import com.poly.datn.repository.ProductVariantRepository;
import com.poly.datn.service.CancelOrderService;
import com.poly.datn.service.UpdateOrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CancelOrderServiceImpl implements CancelOrderService {
    private final ModelConverter converter;
    private final ProductVariantRepository productVariantRepository;
    private final UpdateOrderStatusService updateOrderStatusService;

    @Override
    public void cancelOrder(Integer idOrder, List<OrderDetailRequest> request) {
        //update order to cancel status
        updateOrderStatusService.updateStatusToCancelled(idOrder);
        //filter request to get list id of productVariant
        List<Integer> listIdProductVariants = request.stream()
                .map(m -> m.getProductVariant_id())
                .collect(Collectors.toList());
        //find by list variant id
       List<ProductVariant> productVariants = productVariantRepository.findByIdIn(listIdProductVariants);

       //calculate quantity of productVariants after cancelled
       request.stream().forEach(rq -> {
           productVariants.stream().forEach(pr ->{
                if(rq.getProductVariant_id() == pr.getId())
                    pr.setQuantity(pr.getQuantity() + rq.getQuantity());
           });
       });

       //update quantity of productVariants
       productVariantRepository.saveAllAndFlush(productVariants);
    }
}
