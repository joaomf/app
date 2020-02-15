import { Moment } from 'moment';
import { IItemPedido } from 'app/shared/model/item-pedido.model';

export interface IPedido {
  id?: number;
  dataPedido?: Moment;
  valorPedido?: number;
  itempedidos?: IItemPedido[];
}

export class Pedido implements IPedido {
  constructor(public id?: number, public dataPedido?: Moment, public valorPedido?: number, public itempedidos?: IItemPedido[]) {}
}
