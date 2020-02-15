package com.joao.app.service.mapper;


import com.joao.app.domain.*;
import com.joao.app.service.dto.FestaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Festa} and its DTO {@link FestaDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoFestaMapper.class})
public interface FestaMapper extends EntityMapper<FestaDTO, Festa> {

    @Mapping(source = "tipoFesta.id", target = "tipoFestaId")
    @Mapping(source = "tipoFesta.nome", target = "tipoFestaNome")
    FestaDTO toDto(Festa festa);

    @Mapping(source = "tipoFestaId", target = "tipoFesta")
    Festa toEntity(FestaDTO festaDTO);

    default Festa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Festa festa = new Festa();
        festa.setId(id);
        return festa;
    }
}
