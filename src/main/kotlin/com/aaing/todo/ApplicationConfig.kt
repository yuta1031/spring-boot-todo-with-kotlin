package com.aaing.todo

import de.huxhorn.sulky.ulid.ULID
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {
    @Bean
    fun ulid(): ULID {
        return ULID()
    }
}