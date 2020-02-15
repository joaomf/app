import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'festa',
        loadChildren: () => import('./festa/festa.module').then(m => m.AppFestaModule)
      },
      {
        path: 'cliente',
        loadChildren: () => import('./cliente/cliente.module').then(m => m.AppClienteModule)
      },
      {
        path: 'tipo-festa',
        loadChildren: () => import('./tipo-festa/tipo-festa.module').then(m => m.AppTipoFestaModule)
      },
      {
        path: 'pedido',
        loadChildren: () => import('./pedido/pedido.module').then(m => m.AppPedidoModule)
      },
      {
        path: 'item-pedido',
        loadChildren: () => import('./item-pedido/item-pedido.module').then(m => m.AppItemPedidoModule)
      },
      {
        path: 'funcionario',
        loadChildren: () => import('./funcionario/funcionario.module').then(m => m.AppFuncionarioModule)
      },
      {
        path: 'cargo',
        loadChildren: () => import('./cargo/cargo.module').then(m => m.AppCargoModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AppEntityModule {}
