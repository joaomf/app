import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFuncionario, Funcionario } from 'app/shared/model/funcionario.model';
import { FuncionarioService } from './funcionario.service';
import { ICargo } from 'app/shared/model/cargo.model';
import { CargoService } from 'app/entities/cargo/cargo.service';

@Component({
  selector: 'jhi-funcionario-update',
  templateUrl: './funcionario-update.component.html'
})
export class FuncionarioUpdateComponent implements OnInit {
  isSaving = false;
  cargos: ICargo[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [],
    telefone: [],
    cargoId: []
  });

  constructor(
    protected funcionarioService: FuncionarioService,
    protected cargoService: CargoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ funcionario }) => {
      this.updateForm(funcionario);

      this.cargoService.query().subscribe((res: HttpResponse<ICargo[]>) => (this.cargos = res.body || []));
    });
  }

  updateForm(funcionario: IFuncionario): void {
    this.editForm.patchValue({
      id: funcionario.id,
      nome: funcionario.nome,
      telefone: funcionario.telefone,
      cargoId: funcionario.cargoId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const funcionario = this.createFromForm();
    if (funcionario.id !== undefined) {
      this.subscribeToSaveResponse(this.funcionarioService.update(funcionario));
    } else {
      this.subscribeToSaveResponse(this.funcionarioService.create(funcionario));
    }
  }

  private createFromForm(): IFuncionario {
    return {
      ...new Funcionario(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      telefone: this.editForm.get(['telefone'])!.value,
      cargoId: this.editForm.get(['cargoId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFuncionario>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICargo): any {
    return item.id;
  }
}
