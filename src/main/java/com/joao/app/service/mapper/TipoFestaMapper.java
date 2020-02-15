package com.joao.app.service.mapper;


import com.joao.app.domain.*;
import com.joao.app.service.dto.TipoFestaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoFesta} and its DTO {@link TipoFestaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoFestaMapper extends EntityMapper<TipoFestaDTO, TipoFesta> {


    @Mapping(target = "festa_tipos", ignore = true)
    @Mapping(target = "removeFesta_tipo", ignore = true)
    TipoFesta toEntity(TipoFestaDTO tipoFestaDTO);

    default TipoFesta fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoFesta tipoFesta = new TipoFesta();
        tipoFesta.setId(id);
        return tipoFesta;
    }
}
