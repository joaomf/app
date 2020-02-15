package com.joao.app.service.mapper;


import com.joao.app.domain.*;
import com.joao.app.service.dto.PedidoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pedido} and its DTO {@link PedidoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PedidoMapper extends EntityMapper<PedidoDTO, Pedido> {


    @Mapping(target = "itempedidos", ignore = true)
    @Mapping(target = "removeItempedido", ignore = true)
    Pedido toEntity(PedidoDTO pedidoDTO);

    default Pedido fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pedido pedido = new Pedido();
        pedido.setId(id);
        return pedido;
    }
}
