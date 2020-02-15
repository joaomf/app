export interface ICliente {
  id?: number;
  nome?: string;
  telefone?: string;
  email?: string;
  cpf?: string;
}

export class Cliente implements ICliente {
  constructor(public id?: number, public nome?: string, public telefone?: string, public email?: string, public cpf?: string) {}
}
