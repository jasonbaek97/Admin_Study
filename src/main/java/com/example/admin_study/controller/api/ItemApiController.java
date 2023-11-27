package com.example.admin_study.controller.api;

import com.example.admin_study.ifs.CrudInterface;
import com.example.admin_study.model.network.Header;
import com.example.admin_study.model.network.request.ItemApiRequest;
import com.example.admin_study.model.network.response.ItemApiResponse;
import com.example.admin_study.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")        // api/item  Create
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        log.info("Item create() request : {}",request);
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")     // api/item/1 ... Read
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        log.info("Item read() id : {}",id);
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")         // api/item  Update
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        log.info("Item update() request : {}",request);
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")  // api/item/1 .. Delete
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
