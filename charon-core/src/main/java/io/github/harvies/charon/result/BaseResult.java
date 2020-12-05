package io.github.harvies.charon.result;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public abstract class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String traceId = TraceContext.traceId();

    /**
     * 状态码
     */
    private String code;
    /**
     * 状态信息
     */
    private String msg;
}
