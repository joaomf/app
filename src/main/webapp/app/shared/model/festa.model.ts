export interface IFesta {
  id?: number;
  nome?: string;
  descricao?: string;
  tipoFestaNome?: string;
  tipoFestaId?: number;
}

export class Festa implements IFesta {
  constructor(
    public id?: number,
    public nome?: string,
    public descricao?: string,
    public tipoFestaNome?: string,
    public tipoFestaId?: number
  ) {}
}
