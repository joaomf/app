import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFesta, Festa } from 'app/shared/model/festa.model';
import { FestaService } from './festa.service';
import { ITipoFesta } from 'app/shared/model/tipo-festa.model';
import { TipoFestaService } from 'app/entities/tipo-festa/tipo-festa.service';

@Component({
  selector: 'jhi-festa-update',
  templateUrl: './festa-update.component.html'
})
export class FestaUpdateComponent implements OnInit {
  isSaving = false;
  tipofestas: ITipoFesta[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [],
    descricao: [null, [Validators.required]],
    tipoFestaId: [null, Validators.required]
  });

  constructor(
    protected festaService: FestaService,
    protected tipoFestaService: TipoFestaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ festa }) => {
      this.updateForm(festa);

      this.tipoFestaService.query().subscribe((res: HttpResponse<ITipoFesta[]>) => (this.tipofestas = res.body || []));
    });
  }

  updateForm(festa: IFesta): void {
    this.editForm.patchValue({
      id: festa.id,
      nome: festa.nome,
      descricao: festa.descricao,
      tipoFestaId: festa.tipoFestaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const festa = this.createFromForm();
    if (festa.id !== undefined) {
      this.subscribeToSaveResponse(this.festaService.update(festa));
    } else {
      this.subscribeToSaveResponse(this.festaService.create(festa));
    }
  }

  private createFromForm(): IFesta {
    return {
      ...new Festa(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      descricao: this.editForm.get(['descricao'])!.value,
      tipoFestaId: this.editForm.get(['tipoFestaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFesta>>): void {
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

  trackById(index: number, item: ITipoFesta): any {
    return item.id;
  }
}
