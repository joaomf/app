import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CargoComponentsPage, CargoDeleteDialog, CargoUpdatePage } from './cargo.page-object';

const expect = chai.expect;

describe('Cargo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let cargoComponentsPage: CargoComponentsPage;
  let cargoUpdatePage: CargoUpdatePage;
  let cargoDeleteDialog: CargoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Cargos', async () => {
    await navBarPage.goToEntity('cargo');
    cargoComponentsPage = new CargoComponentsPage();
    await browser.wait(ec.visibilityOf(cargoComponentsPage.title), 5000);
    expect(await cargoComponentsPage.getTitle()).to.eq('appApp.cargo.home.title');
    await browser.wait(ec.or(ec.visibilityOf(cargoComponentsPage.entities), ec.visibilityOf(cargoComponentsPage.noResult)), 1000);
  });

  it('should load create Cargo page', async () => {
    await cargoComponentsPage.clickOnCreateButton();
    cargoUpdatePage = new CargoUpdatePage();
    expect(await cargoUpdatePage.getPageTitle()).to.eq('appApp.cargo.home.createOrEditLabel');
    await cargoUpdatePage.cancel();
  });

  it('should create and save Cargos', async () => {
    const nbButtonsBeforeCreate = await cargoComponentsPage.countDeleteButtons();

    await cargoComponentsPage.clickOnCreateButton();

    await promise.all([cargoUpdatePage.setNomeInput('nome'), cargoUpdatePage.setSalarioCargoInput('5')]);

    expect(await cargoUpdatePage.getNomeInput()).to.eq('nome', 'Expected Nome value to be equals to nome');
    expect(await cargoUpdatePage.getSalarioCargoInput()).to.eq('5', 'Expected salarioCargo value to be equals to 5');

    await cargoUpdatePage.save();
    expect(await cargoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await cargoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Cargo', async () => {
    const nbButtonsBeforeDelete = await cargoComponentsPage.countDeleteButtons();
    await cargoComponentsPage.clickOnLastDeleteButton();

    cargoDeleteDialog = new CargoDeleteDialog();
    expect(await cargoDeleteDialog.getDialogTitle()).to.eq('appApp.cargo.delete.question');
    await cargoDeleteDialog.clickOnConfirmButton();

    expect(await cargoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
