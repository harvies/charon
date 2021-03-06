package io.github.harvies.charon.spring.boot.web.result;

import lombok.*;

import java.util.List;

/**
 * @author harvies
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Deprecated
public class PageResultDTO<T> extends BaseResult {

    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    @NonNull
    private List<T> rows;
}
