package io.github.harvies.charon.oss;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class FileDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    private String url;
    private String cdnUrl;
}
