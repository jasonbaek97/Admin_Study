package com.example.admin_study.service;

import com.example.admin_study.ifs.CrudInterface;
import com.example.admin_study.model.entity.Item;
import com.example.admin_study.model.entity.OrderGroup;
import com.example.admin_study.model.network.Header;
import com.example.admin_study.model.network.request.OrderGroupApiRequest;
import com.example.admin_study.model.network.response.ItemApiResponse;
import com.example.admin_study.model.network.response.OrderGroupApiResponse;
import com.example.admin_study.repository.OrderGroupRepository;
import com.example.admin_study.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        log.debug(request.toString());
        OrderGroupApiRequest body = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .arrivalDate(body.getArrivalDate())
                .user(userRepository.getReferenceById(body.getUserId()))
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        // 1. request data 가져오기
        OrderGroupApiRequest body = request.getData();
        // 2. request data로 OrderGroup 찾기
        return orderGroupRepository.findById(body.getId())
                .map(orderGroup -> {
                    // 3. update
                    orderGroup.setStatus(body.getStatus())
                            .setOrderType(body.getOrderType())
                            .setRevAddress(body.getRevAddress())
                            .setRevName(body.getRevName())
                            .setPaymentType(body.getPaymentType())
                            .setTotalPrice(body.getTotalPrice())
                            .setTotalQuantity(body.getTotalQuantity())
                            .setOrderAt(body.getOrderAt())
                            .setArrivalDate(body.getArrivalDate())
                            .setUser(userRepository.getReferenceById(body.getUserId()));
                    return orderGroup;
                })
                .map(changeOrderGroup -> orderGroupRepository.save(changeOrderGroup))
                .map(newOrderGroup -> response(newOrderGroup))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return orderGroupRepository.findById(id)
                .map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    // Response 변환함수
    public Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();
        return Header.OK(body);
    }
}
