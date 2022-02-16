package com.setpoint.reservapistes.modules.shared.core.bus.domain.query;

import com.setpoint.reservapistes.modules.shared.interfaces.Query;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;

public interface QueryBus {
    <R extends Response> R ask(final Query query);
}
