package com.futurice.futuricecalculus.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@ConfigurationProperties("key")
@Configuration
public class ConfigurationProps {

    private final String add;
    private final String multiply;
    private final String divide;
    private final String subtract;

}
