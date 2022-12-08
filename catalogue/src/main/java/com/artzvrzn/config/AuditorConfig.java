package com.artzvrzn.config;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(dateTimeProviderRef = "auditorDateTimeProvider")
public class AuditorConfig {

  @Bean(name = "auditorDateTimeProvider")
  public LocalDateTime auditorDateTimeProvider() {
    return LocalDateTime.now(ZoneOffset.UTC);
  }
}
