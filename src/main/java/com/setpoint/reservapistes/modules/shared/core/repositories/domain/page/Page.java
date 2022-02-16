package com.setpoint.reservapistes.modules.shared.core.repositories.domain.page;

import com.setpoint.reservapistes.modules.shared.interfaces.Response;

import java.util.List;

public interface Page<R extends Response> extends Response {
    List<R> getContent();

    Integer getTotalPages();

    Long getTotalElements();

    Boolean isLast();

    Integer getSize();

    Boolean isFirst();

    Integer getNumberOfElements();
}
