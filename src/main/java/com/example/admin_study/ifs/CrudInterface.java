package com.example.admin_study.ifs;


import com.example.admin_study.model.network.Header;

public interface CrudInterface<Req, Res>{
    Header<Res> create(Header<Req> request);
    Header<Res> read(Long id);
    Header<Res> update(Header<Req> request);
    Header delete(Long id);
}
