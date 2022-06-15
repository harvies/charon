package io.github.harvies.charon.picture.compress;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileCompressConvert {

    FileCompressConvert INSTANCE = Mappers.getMapper(FileCompressConvert.class);

    InputStreamContext convert(FileContext source);
    FileContext convert(InputStreamContext source);
}
