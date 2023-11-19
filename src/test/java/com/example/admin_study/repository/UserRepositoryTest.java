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

//        User user = new User();
//        user.setAccount(account);
//        user.setPassword(password);
//        user.setStatus(status);
//        user.setEmail(email);
//        user.setPhoneNumber(phoneNumber);
//        user.setRegisteredAt(registeredAt);

        // @Builder lombok 패턴을 활용한 생성자 호출, Class 변경과 생성자변경에 자유로움
        User user = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .phoneNumber(phoneNumber)
                .registeredAt(registeredAt)
                .build();

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");
        Assertions.assertNotNull(user);
        if(user != null ){
            user.getOrderGroup().stream().forEach(orderGroup -> {

                System.out.println("-------------주문묶음-------------");
                System.out.println("수령인 : "+  orderGroup.getRevName());
                System.out.println("주소 : "+orderGroup.getRevAddress());
                System.out.println("총 금액 : "+orderGroup.getTotalPrice());
                System.out.println("총 수량 :"+orderGroup.getTotalQuantity());
                System.out.println("-------------주문상세-------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 : "+orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문상품 : "+orderDetail.getItem().getName());
                    System.out.println("고객센터번호 : "+orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문상태 : "+orderDetail.getStatus());
                    System.out.println("도착 예정일시 :"+orderDetail.getArrivalDate());
                });
            });
        }


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
