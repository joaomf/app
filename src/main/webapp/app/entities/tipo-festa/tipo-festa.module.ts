import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { TipoFestaComponent } from './tipo-festa.component';
import { TipoFestaDetailComponent } from './tipo-festa-detail.component';
import { TipoFestaUpdateComponent } from './tipo-festa-update.component';
import { TipoFestaDeleteDialogComponent } from './tipo-festa-delete-dialog.component';
import { tipoFestaRoute } from './tipo-festa.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(tipoFestaRoute)],
  declarations: [TipoFestaComponent, TipoFestaDetailComponent, TipoFestaUpdateComponent, TipoFestaDeleteDialogComponent],
  entryComponents: [TipoFestaDeleteDialogComponent]
})
export class AppTipoFestaModule {}
