package io.github.harvies.charon.picture.compress;

import io.github.harvies.charon.picture.compress.BaseFileContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.File;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class FileContext extends BaseFileContext {

    @NotNull
    private File file;

}
