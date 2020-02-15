export interface IItemPedido {
  id?: number;
  nome?: string;
  valorItem?: number;
  pedidoId?: number;
}

export class ItemPedido implements IItemPedido {
  constructor(public id?: number, public nome?: string, public valorItem?: number, public pedidoId?: number) {}
}
