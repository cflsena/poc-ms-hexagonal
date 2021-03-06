package poc.ms.hexagonal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import poc.ms.hexagonal.domain.Person;
import poc.ms.hexagonal.dto.PersonRequestDTO;
import poc.ms.hexagonal.dto.PersonResponseDTO;

@Component
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class PersonMapper {

    public static final PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

    public abstract PersonResponseDTO toDTO(Person entity);

    public abstract Person toEntity(PersonRequestDTO dto);

}
