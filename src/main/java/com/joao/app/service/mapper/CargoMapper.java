package com.joao.app.service.mapper;


import com.joao.app.domain.*;
import com.joao.app.service.dto.CargoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cargo} and its DTO {@link CargoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CargoMapper extends EntityMapper<CargoDTO, Cargo> {


    @Mapping(target = "funcionarios", ignore = true)
    @Mapping(target = "removeFuncionario", ignore = true)
    Cargo toEntity(CargoDTO cargoDTO);

    default Cargo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cargo cargo = new Cargo();
        cargo.setId(id);
        return cargo;
    }
}
