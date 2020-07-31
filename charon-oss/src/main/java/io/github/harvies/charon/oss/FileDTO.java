package io.github.harvies.charon.oss;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author harvies
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class FileDTO {
    @NonNull
    private String url;
    private String cdnUrl;
}
