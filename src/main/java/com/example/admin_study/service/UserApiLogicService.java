package com.example.admin_study.service;

import com.example.admin_study.ifs.CrudInterface;
import com.example.admin_study.model.entity.User;
import com.example.admin_study.model.network.Header;
import com.example.admin_study.model.network.request.UserApiRequest;
import com.example.admin_study.model.network.response.UserApiResponse;
import com.example.admin_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;


    // 2. User 생성
    // 3. 생성데이터  -> UserApiResponse return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1. Request Data 가져오기
        UserApiRequest userApiRequest = request.getData();
        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        // 3. User 생성 -> UserApiResponse 생성 -> Header<UserApiResponse> 생성후 반환
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id -> repository getOne, getById
        Optional<User> optional = userRepository.findById(id);
        // user -> userApi response
        return optional
                .map(user->response(user))
                .orElseGet(
                        ()->Header.ERROR("데이터없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data
        UserApiRequest userApiRequest = request.getData();
        // 2. id -> user 데이터 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());
        return optional.map(user->{
            //3. DB에서 찾은 user data 에 request 값으로 변경
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
            return user;
        })
                // 변경된 user를 DB에 udpate 처리
                .map(user -> userRepository.save(user))  // userRepository 반영
                .map(updateUser -> response(updateUser)) // update 결과 User를 Response로 변환
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
        Optional<User> optional = userRepository.findById(id);
        // 2. userRepository -> delete
        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user){
        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())   // todoe 암호화, 길이...
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unRegisteredAt(user.getUnregisteredAt())
                .build();
        // Header.OK(data)
        return Header.OK(userApiResponse);
    }
}
