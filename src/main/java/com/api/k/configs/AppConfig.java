package com.api.k.configs;

import com.ofxr.OFXProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OFXProcess ofxProcess() {
        return new OFXProcess();
    }
}