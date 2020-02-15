import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICargo } from 'app/shared/model/cargo.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CargoService } from './cargo.service';
import { CargoDeleteDialogComponent } from './cargo-delete-dialog.component';

@Component({
  selector: 'jhi-cargo',
  templateUrl: './cargo.component.html'
})
export class CargoComponent implements OnInit, OnDestroy {
  cargos: ICargo[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected cargoService: CargoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.cargos = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.cargoService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ICargo[]>) => this.paginateCargos(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.cargos = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCargos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICargo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCargos(): void {
    this.eventSubscriber = this.eventManager.subscribe('cargoListModification', () => this.reset());
  }

  delete(cargo: ICargo): void {
    const modalRef = this.modalService.open(CargoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cargo = cargo;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCargos(data: ICargo[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.cargos.push(data[i]);
      }
    }
  }
}
