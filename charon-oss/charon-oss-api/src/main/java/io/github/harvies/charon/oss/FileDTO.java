package io.github.harvies.charon.oss;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author harvies
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class FileDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    private String url;
    private String cdnUrl;
}
