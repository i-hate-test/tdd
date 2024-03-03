package com.aihoshistar.sample.common.config.database

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.aihoshistar.sample.user.adapter.out.persistence.jpa"])
class SpringDataJpaConfig
