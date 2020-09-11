package io.github.harvies.charon.spring.boot.web.result;

import lombok.*;

/**
 * @author harvies
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> extends BaseResult {
    /**
     * 返回值
     */
    private T data;
}
