package io.github.harvies.charon.spring.boot.web.result;

import lombok.*;

/**
 * @author harvies
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ResultDTO<T> extends BaseResult {
    /**
     * 返回值
     */
    @NonNull
    private T data;
}
