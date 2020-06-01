package io.github.harvies.charon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public abstract class BaseDTO {
    private static final long serialVersionUID = -4721607536018568393L;
}
