import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FuncionarioComponentsPage, FuncionarioDeleteDialog, FuncionarioUpdatePage } from './funcionario.page-object';

const expect = chai.expect;

describe('Funcionario e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let funcionarioComponentsPage: FuncionarioComponentsPage;
  let funcionarioUpdatePage: FuncionarioUpdatePage;
  let funcionarioDeleteDialog: FuncionarioDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Funcionarios', async () => {
    await navBarPage.goToEntity('funcionario');
    funcionarioComponentsPage = new FuncionarioComponentsPage();
    await browser.wait(ec.visibilityOf(funcionarioComponentsPage.title), 5000);
    expect(await funcionarioComponentsPage.getTitle()).to.eq('appApp.funcionario.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(funcionarioComponentsPage.entities), ec.visibilityOf(funcionarioComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Funcionario page', async () => {
    await funcionarioComponentsPage.clickOnCreateButton();
    funcionarioUpdatePage = new FuncionarioUpdatePage();
    expect(await funcionarioUpdatePage.getPageTitle()).to.eq('appApp.funcionario.home.createOrEditLabel');
    await funcionarioUpdatePage.cancel();
  });

  it('should create and save Funcionarios', async () => {
    const nbButtonsBeforeCreate = await funcionarioComponentsPage.countDeleteButtons();

    await funcionarioComponentsPage.clickOnCreateButton();

    await promise.all([
      funcionarioUpdatePage.setNomeInput('nome'),
      funcionarioUpdatePage.setTelefoneInput('telefone'),
      funcionarioUpdatePage.cargoSelectLastOption()
    ]);

    expect(await funcionarioUpdatePage.getNomeInput()).to.eq('nome', 'Expected Nome value to be equals to nome');
    expect(await funcionarioUpdatePage.getTelefoneInput()).to.eq('telefone', 'Expected Telefone value to be equals to telefone');

    await funcionarioUpdatePage.save();
    expect(await funcionarioUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await funcionarioComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Funcionario', async () => {
    const nbButtonsBeforeDelete = await funcionarioComponentsPage.countDeleteButtons();
    await funcionarioComponentsPage.clickOnLastDeleteButton();

    funcionarioDeleteDialog = new FuncionarioDeleteDialog();
    expect(await funcionarioDeleteDialog.getDialogTitle()).to.eq('appApp.funcionario.delete.question');
    await funcionarioDeleteDialog.clickOnConfirmButton();

    expect(await funcionarioComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
