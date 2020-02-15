import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { FuncionarioComponent } from './funcionario.component';
import { FuncionarioDetailComponent } from './funcionario-detail.component';
import { FuncionarioUpdateComponent } from './funcionario-update.component';
import { FuncionarioDeleteDialogComponent } from './funcionario-delete-dialog.component';
import { funcionarioRoute } from './funcionario.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(funcionarioRoute)],
  declarations: [FuncionarioComponent, FuncionarioDetailComponent, FuncionarioUpdateComponent, FuncionarioDeleteDialogComponent],
  entryComponents: [FuncionarioDeleteDialogComponent]
})
export class AppFuncionarioModule {}
