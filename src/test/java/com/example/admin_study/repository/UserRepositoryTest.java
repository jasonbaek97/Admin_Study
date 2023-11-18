package com.example.admin_study.repository;

import com.example.admin_study.AdminStudyApplicationTests;
import com.example.admin_study.model.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminStudyApplicationTests {

    // Dependency Injection (DI) -> @Autowired 스프링 자동주입
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "Test01";
        String password ="Test01";
        String status ="REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-1111";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");
        Assertions.assertNotNull(user);
//        System.out.println(user.toString());

    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(3L);

        user.ifPresent(selectUser->{
            selectUser.setAccount("updated");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method");

            userRepository.save(selectUser);
        });

    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);
        Assertions.assertTrue(user.isPresent());            // 삭제 대상이 있어야하니까, 항상 True 여야 함

        user.ifPresent(selectUser-> userRepository.delete(selectUser));

        Optional<User> deleteUser = userRepository.findById(1L);
        Assertions.assertFalse(deleteUser.isPresent());     // 삭제 했으므로, 항상 False 여야함
    }

}
