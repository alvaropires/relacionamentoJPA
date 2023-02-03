package com.exemple.conf;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConf {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}