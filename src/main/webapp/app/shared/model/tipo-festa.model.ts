import { IFesta } from 'app/shared/model/festa.model';

export interface ITipoFesta {
  id?: number;
  nome?: string;
  descricao?: string;
  festa_tipos?: IFesta[];
}

export class TipoFesta implements ITipoFesta {
  constructor(public id?: number, public nome?: string, public descricao?: string, public festa_tipos?: IFesta[]) {}
}
