package com.poly.datn.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.datn.common.mapper.ModelConverter;
import com.poly.datn.dto.request.UserAddressRequest;
import com.poly.datn.dto.response.AddressResponse;
import com.poly.datn.entity.Address;
import com.poly.datn.entity.Address.AddressBuilder;
import com.poly.datn.entity.User;
import com.poly.datn.repository.AddressRepository;
import com.poly.datn.service.UserAddressService;
import com.poly.datn.service.UserInfoByTokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserAddressServiceImpl implements UserAddressService {

    private final AddressRepository addressRepository;
    private final ModelConverter converter;
    private final Sort idSort = Sort.by("id");
    private final Sort isDefaultSort = Sort.by(Direction.DESC, "isDefault");
    private final UserInfoByTokenService userInfoService;

    @Override
    public List<AddressResponse> findAllByUserId(Integer userId) {
        try {

            List<Address> addressList = addressRepository.findAllByUserId(userId, isDefaultSort.and(idSort))
                    .orElseGet(ArrayList::new);
            return converter.mapAllByIterator(addressList, AddressResponse.class);
        } catch (Exception e) {
            log.error("FIND ALL ERRER: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AddressResponse findById(Integer id) {
        try {
            return converter.map(findEntityById(id), AddressResponse.class);
        }catch(Exception e) {
            if(e instanceof EntityNotFoundException) {
                log.info("error: " + e.getMessage());
            }
        }
        return null;
     
    }

    public Address findEntityById(Integer id) {
        User user = userInfoService.getCurrentUser();
      return  addressRepository.findByIdAndUser(id, user).orElseThrow(() -> new EntityNotFoundException("!!! Can't find address with id from current user "));
    }

    private Address findDefaultAddressEntityOfUser() {
        return addressRepository.findDefaultAddressOfUser(userInfoService.getCurrentUser()).orElseThrow(() -> new EntityNotFoundException("Can't find any default address from user"));
    }
    private Integer getDefaultAddressId() {
        try {
            return findDefaultAddressEntityOfUser().getId();
        }catch(EntityNotFoundException e) {
            return -1;
        }
    }

    @Override
    public AddressResponse findDefaultAddress() {
       Address defaulAddress = addressRepository.findDefaultAddressOfUser(userInfoService.getCurrentUser()).orElseThrow(() -> new EntityNotFoundException("Can't find any default address from user"));
       return converter.map(defaulAddress, AddressResponse.class);
    }

    @Override
    public AddressResponse updateAddress(UserAddressRequest request) {
        try {
            Address address = findEntityById(request.getId());
            Integer defaultId =  getDefaultAddressId();


            if(defaultId != -1 && request.getIs_default()) {
                log.info("check correct default");
                if(defaultId != request.getId()) {
                    request.setIs_default(true);
                    Address defaultAdd = findDefaultAddressEntityOfUser();   
                    defaultAdd.setIsDefault(false);
                    addressRepository.saveAllAndFlush(List.of(defaultAdd));
                }
            }else if(defaultId == request.getId() && !request.getIs_default()) {
                log.info("set defautl false for current default");
                request.setIs_default(false);
                boolean setNext = setNextAddressToDefault(address);
                if(!setNext) throw new RuntimeException("Chỉ có 1 địa chỉ trong danh sách, không thể tắt mặc định, vui lòng thêm địa chỉ khác để tắt mặc định cho địa chỉ này");
            }
            Address updatedAddress = buildAddressEntity(false, request);
            addressRepository.save(updatedAddress);
            return converter.map(updatedAddress, AddressResponse.class);
        }catch(Exception e){
            if(e instanceof EntityNotFoundException) {
                throw new RuntimeException("Can't found request address to update");
            }else throw new RuntimeException(e.getMessage());
        }
       
    }

    @Override
    public boolean removeAddress(Integer requestId) {
        try {
           Address addresss = findEntityById(requestId);  

        //  !  set default when remove current default  address
           setNextAddressToDefault(addresss);           
           log.info("remove address with id " + requestId);
           addressRepository.delete(addresss);
           log.info("finish removed " + requestId);
           return true;
        }catch(EntityNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean setNextAddressToDefault(Address requestedAddress) {
        List<Address> addressList = getCurrentUserAddressList();
        Integer addressCount = addressList.size();  
        if(requestedAddress.getIsDefault() &&  addressCount > 1) {
            Address nextAddress =  addressList.get(1);
            nextAddress.setIsDefault(true);
            addressRepository.save(nextAddress);
            return true;
        }   else if(addressCount < 1) {
            return false;
        }
        return false;
    }

    private List<Address> getCurrentUserAddressList() {
        User currentUser =  userInfoService.getCurrentUser();
        return addressRepository.findAllByUserId(currentUser.getId(), isDefaultSort.and(idSort)).get();
    }

    @Override
    public AddressResponse addAddress(UserAddressRequest request) {
        try {
            AddressBuilder builder = Address.builder();
            // prevent change default through add request"
            request.setIs_default(false);
            if(getDefaultAddressId() == -1) {
                request.setIs_default(true);
                log.info("Set isDefault on request = true" + request.getIs_default());
            }
            Address newAddress = buildAddressEntity(true, request);
            // Address defaultAdd = findDefaultAddressEntityOfUser();
           Address addedAddress =  addressRepository.save(newAddress);
           
           return converter.map(addedAddress, AddressResponse.class);

        }catch(Exception e) {
            log.info("Set default address for the first one");
            return null;
        }
    }

    private Address buildAddressEntity(boolean isNew, UserAddressRequest request) {
        Integer id = isNew? 0 : request.getId();
        AddressBuilder builder = Address.builder();
        User user  = userInfoService.getCurrentUser();
        Boolean isDefault = request.getIs_default() == null? false : request.getIs_default();
        // boolean isDefault = request.getIs_default();
        Address newAddress = builder.withUser(user)
                                    .withId(id).
                                    withAddressLine(request.getAddress_line())
                                    .withDistrict(request.getDistrict())
                                    .withPostalId(request.getPostal_id())
                                    .withProvince(request.getProvince())
                                    .withWards(request.getWards())
                                    .withIsDefault(isDefault)
                                    .build();
        return newAddress;                            
    }



    @Override
    public boolean setDefaultAddress(Integer addressId) {

        if(addressRepository.findAll().size() == 0)  throw new RuntimeException("Address list is empty");
        Address address = null;
        try {
            address = findEntityById(addressId);
            Integer defaultId = getDefaultAddressId();

        if(defaultId != -1) {
            if(address.getId() != defaultId) {
               Address defaultAdd = findDefaultAddressEntityOfUser();   
               defaultAdd.setIsDefault(false);
               address.setIsDefault(true);
               addressRepository.saveAllAndFlush(List.of(defaultAdd,address));
            }

        }else {
            address.setIsDefault(true);
            addressRepository.save(address);           
        }
            return true;
        }catch(Exception e) {
            if(e instanceof EntityNotFoundException) {
                throw new RuntimeException(e.getMessage());
            }
        }
       return false;
    }

    
}
