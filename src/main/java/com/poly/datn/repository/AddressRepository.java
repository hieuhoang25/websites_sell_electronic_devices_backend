package com.poly.datn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.datn.entity.Address;
import com.poly.datn.entity.User;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<List<Address>> findAllByUserId(Integer userId,Sort sort);

    Optional<Address> findByIsDefault(Boolean isDefault);

    Optional<Address> findByIdAndUser(Integer id, User user);

    @Query(value = "select a.isDefault from Address a where a.id =:addressId ")
    boolean isDefaultAddress(@Param("addressId") Integer addressId);

    @Query(value = "select a from Address a where a.user =:user and a.isDefault = true ")
    Optional<Address> findDefaultAddressOfUser(@Param("user") User user);


}