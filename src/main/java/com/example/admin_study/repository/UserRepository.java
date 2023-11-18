package com.example.admin_study.repository;

import com.example.admin_study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);


    // select * from user where account =?
    // find -> Select, ByXXX -> Where조건 , param -> where 조건 매핑
//    Optional<User> findByAccount(String account);
//
//    Optional<User> findByEmail(String email);

    // select * from user where account = ? and email = ?
    // 2개의 값으로 검색 -> query method 방식 -> Parm 1 + And + Parm 2
//    Optional<User> findByAccountAndEmail(String account, String email);

}
