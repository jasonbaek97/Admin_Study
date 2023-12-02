package com.example.admin_study.service;

import com.example.admin_study.ifs.CrudInterface;
import com.example.admin_study.model.entity.Item;
import com.example.admin_study.model.network.Header;
import com.example.admin_study.model.network.request.ItemApiRequest;
import com.example.admin_study.model.network.response.ItemApiResponse;
import com.example.admin_study.repository.ItemRepository;
import com.example.admin_study.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        log.debug(request.toString());
        ItemApiRequest body = request.getData();
        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .title(body.getTitle())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getReferenceById(body.getPartnerId()))   // getOne -> getReferencedById 대체
                .build();

        Item newItem = itemRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();
        return itemRepository.findById(body.getId())
                .map(entityitem -> {
                    entityitem
                            .setStatus(body.getStatus())
                            .setName(body.getName())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setTitle(body.getTitle())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            ;
                    return entityitem;
                })
                .map(newEntityItem-> itemRepository.save(newEntityItem))
                .map(item -> response(item))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }


    // response 변환 함수
    private Header<ItemApiResponse> response(Item item){
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(body);
    }
}
