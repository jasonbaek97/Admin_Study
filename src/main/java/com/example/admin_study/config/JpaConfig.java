package com.example.admin_study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing      // JpaAudit 활성화
public class JpaConfig {
}
