package com.setpoint.reservapistes.modules.shared.core.bus.domain.query;

import com.setpoint.reservapistes.modules.shared.core.bus.domain.Handler;
import com.setpoint.reservapistes.modules.shared.interfaces.Query;
import com.setpoint.reservapistes.modules.shared.interfaces.Response;

public interface QueryHandler<Q extends Query, R extends Response> extends Handler {
    R handle(final Q query);
}
