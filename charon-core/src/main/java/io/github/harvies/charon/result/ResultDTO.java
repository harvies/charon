package io.github.harvies.charon.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
