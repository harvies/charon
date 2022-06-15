package io.github.harvies.charon.picture.compress;

import io.github.harvies.charon.picture.compress.BaseFileContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.InputStream;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class InputStreamContext extends BaseFileContext {

    @NotNull
    private InputStream inputStream;

}
