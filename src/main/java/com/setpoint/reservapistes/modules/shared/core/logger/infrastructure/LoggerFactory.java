package com.setpoint.reservapistes.modules.shared.core.logger.infrastructure;

import com.setpoint.reservapistes.modules.shared.core.logger.domain.Logger;

public class LoggerFactory {

    private LoggerFactory(){
        throw new IllegalStateException("Factory class");
    }

    public static Logger getLogger(Class<?> clazz) {
        return Log4j2Wrapper.create(clazz);
    }
}
