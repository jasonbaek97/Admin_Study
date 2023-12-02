package com.example.admin_study.controller.api;

import com.example.admin_study.ifs.CrudInterface;
import com.example.admin_study.model.network.Header;
import com.example.admin_study.model.network.request.OrderGroupApiRequest;
import com.example.admin_study.model.network.response.OrderGroupApiResponse;
import com.example.admin_study.service.OrderGroupApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")        // create
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        log.info("OrderGroup create() :{}",request);
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")     // read
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        log.info("OrderGroup read() id : {}",id);
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")         // update
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("/api/{id}") // delete
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
