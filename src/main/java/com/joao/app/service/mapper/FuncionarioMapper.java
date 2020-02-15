package com.joao.app.service.mapper;


import com.joao.app.domain.*;
import com.joao.app.service.dto.FuncionarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Funcionario} and its DTO {@link FuncionarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {CargoMapper.class})
public interface FuncionarioMapper extends EntityMapper<FuncionarioDTO, Funcionario> {

    @Mapping(source = "cargo.id", target = "cargoId")
    FuncionarioDTO toDto(Funcionario funcionario);

    @Mapping(source = "cargoId", target = "cargo")
    Funcionario toEntity(FuncionarioDTO funcionarioDTO);

    default Funcionario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        return funcionario;
    }
}
