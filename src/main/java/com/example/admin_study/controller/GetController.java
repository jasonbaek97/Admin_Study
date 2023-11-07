package com.example.admin_study.controller;

import com.example.admin_study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController                    // Controller 활용
@RequestMapping("/api")         // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")    //localhost:8080/api/getMethod
    public String getRequest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")      // localhost:8080/api/getParameter?id=12345&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        // RequestParameter 항목은 local 변수 사용하지 않는것이 좋다.
        // password는 Request 이면서 local 변수 이므로, pwd 변경하고 param 매핑을위해 name 지정
        String password = "bbbb";
        System.out.println("id :"+id);
        System.out.println("password :"+pwd);

        return id+pwd;
    }

    // localhost:8080/api/mutiParameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParamter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;     // 객체 반환  : Json 구조 { "account" : "", "email" : "", "page": 0 }
    }
}
