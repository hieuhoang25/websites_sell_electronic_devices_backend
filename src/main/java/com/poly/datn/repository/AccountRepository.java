package com.poly.datn.repository;

import com.poly.datn.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String userName);

    Boolean existsByUsername(String username);

    @Query(value = "Select * from account",nativeQuery = true)
    List<Account> findAllUserAndAuth(Pageable pageable);


    @Query(value = "select * from account a join user u on " +
            "a.user_id = u.id left join address adr on " +
            "adr.user_id = u.id where  username like :keysearch or " +
            "email like :keysearch or full_name like :keysearch or phone like :keysearch",nativeQuery = true)
    List<Account> findAllByFilters(Pageable pageable,@Param("keysearch")String keysearch);

    @Query(value = "select count(*) from account a join user u on " +
            "a.unique_id = u.id left join address adr on " +
            "adr.user_id = u.id where  username like :keysearch or " +
            "email like :keysearch or full_name like :keysearch or phone like :keysearch",nativeQuery = true)
    Integer countAccountByFilters(@Param("keysearch")String keysearch);

    @Query(value = "select * from account a join user u on " +
            "a.user_id = u.id left join address adr on " +
            "adr.user_id = u.id join authority auth on " +
            "auth.account_id = a.unique_id  where  username like :keysearch and role_id = :role or " +
            "email like :keysearch and role_id = :role or full_name like :keysearch " +
            "and role_id = :role or phone like :keysearch and role_id = :role",nativeQuery = true)
    List<Account> findAllByFilterWithRole(Pageable pageable,
     @Param("keysearch")String keysearch,@Param("role") Integer role);

    @Query(value = "select count(*) from account a join user u on " +
            "a.user_id = u.id left join address adr on " +
            "adr.user_id = u.id join authority auth on " +
            "auth.account_id = a.unique_id  where  username like :keysearch and role_id = :role or " +
            "email like :keysearch and role_id = :role or full_name like :keysearch " +
            "and role_id = :role or phone like :keysearch and role_id = :role",nativeQuery = true)
    Integer countAccountByFiltersWithRole(@Param("keysearch")String keysearch,@Param("role") Integer role);


}