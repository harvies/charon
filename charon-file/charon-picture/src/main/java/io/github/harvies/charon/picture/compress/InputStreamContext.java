package io.github.harvies.charon.picture.compress;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.InputStream;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class InputStreamContext extends BaseFileContext {

    @NotNull
    private InputStream inputStream;

}
