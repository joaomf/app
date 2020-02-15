import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { FestaComponent } from './festa.component';
import { FestaDetailComponent } from './festa-detail.component';
import { FestaUpdateComponent } from './festa-update.component';
import { FestaDeleteDialogComponent } from './festa-delete-dialog.component';
import { festaRoute } from './festa.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(festaRoute)],
  declarations: [FestaComponent, FestaDetailComponent, FestaUpdateComponent, FestaDeleteDialogComponent],
  entryComponents: [FestaDeleteDialogComponent]
})
export class AppFestaModule {}
