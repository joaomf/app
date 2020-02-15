export interface IFuncionario {
  id?: number;
  nome?: string;
  telefone?: string;
  cargoId?: number;
}

export class Funcionario implements IFuncionario {
  constructor(public id?: number, public nome?: string, public telefone?: string, public cargoId?: number) {}
}
