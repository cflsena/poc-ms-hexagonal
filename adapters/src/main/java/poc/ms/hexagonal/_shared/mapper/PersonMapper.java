package poc.ms.hexagonal._shared.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.rest.dto.PersonRequest;
import poc.ms.hexagonal.rest.dto.PersonResponse;

@Component
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class PersonMapper {

    public static final PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

    public abstract PersonResponse toDTO(Person entity);

    public abstract Person toEntity(PersonRequest dto);

}
