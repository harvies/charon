package io.github.harvies.charon.result;

import lombok.*;

/**
 * @author harvies
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ResultDTO<T> extends BaseResult {
    /**
     * 返回值
     */
    private T data;
}
