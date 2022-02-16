package com.setpoint.reservapistes.config.core;

import com.setpoint.reservapistes.modules.shared.core.SharedModuleConfigurationAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class SharedModuleConfiguration extends SharedModuleConfigurationAdapter {
    @Autowired
    public SharedModuleConfiguration(){}
}
