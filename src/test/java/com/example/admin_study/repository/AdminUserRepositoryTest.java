package com.example.admin_study.repository;

import com.example.admin_study.AdminStudyApplicationTests;
import com.example.admin_study.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class AdminUserRepositoryTest extends AdminStudyApplicationTests {

    @Autowired
    AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser02");
        adminUser.setPassword("AdminUser02");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminuser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminuser);

        newAdminuser.setAccount("CHANGE");
        adminUserRepository.save(newAdminuser);
    }
}
