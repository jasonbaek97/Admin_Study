package com.example.admin_study.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {

    // api 통신시간
    //@JsonProperty("transaction_time")
    // --> application.properties 에서 spring.jackson.property-naming-strategy=SNAKE_CASE
    private LocalDateTime transactionTime;
    // api 응답코드
    private String resultCode;
    // api 부가 설명
    private String description;

    // Data 영역은 <T> 형태를 가짐 (generic)
    private T data;

    // OK
    public static <T> Header<T> OK(){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }
    // DATA OK
    public static <T> Header<T> OK(T data){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

}
