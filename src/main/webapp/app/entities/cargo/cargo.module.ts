import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { CargoComponent } from './cargo.component';
import { CargoDetailComponent } from './cargo-detail.component';
import { CargoUpdateComponent } from './cargo-update.component';
import { CargoDeleteDialogComponent } from './cargo-delete-dialog.component';
import { cargoRoute } from './cargo.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(cargoRoute)],
  declarations: [CargoComponent, CargoDetailComponent, CargoUpdateComponent, CargoDeleteDialogComponent],
  entryComponents: [CargoDeleteDialogComponent]
})
export class AppCargoModule {}
