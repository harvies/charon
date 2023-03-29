package io.github.harvies.charon.picture.compress;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.File;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class FileContext extends BaseFileContext {

    @NotNull
    private File file;

}
