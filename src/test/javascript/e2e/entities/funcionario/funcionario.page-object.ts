import { element, by, ElementFinder } from 'protractor';

export class FuncionarioComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-funcionario div table .btn-danger'));
  title = element.all(by.css('jhi-funcionario div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class FuncionarioUpdatePage {
  pageTitle = element(by.id('jhi-funcionario-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomeInput = element(by.id('field_nome'));
  telefoneInput = element(by.id('field_telefone'));

  cargoSelect = element(by.id('field_cargo'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNomeInput(nome: string): Promise<void> {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput(): Promise<string> {
    return await this.nomeInput.getAttribute('value');
  }

  async setTelefoneInput(telefone: string): Promise<void> {
    await this.telefoneInput.sendKeys(telefone);
  }

  async getTelefoneInput(): Promise<string> {
    return await this.telefoneInput.getAttribute('value');
  }

  async cargoSelectLastOption(): Promise<void> {
    await this.cargoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async cargoSelectOption(option: string): Promise<void> {
    await this.cargoSelect.sendKeys(option);
  }

  getCargoSelect(): ElementFinder {
    return this.cargoSelect;
  }

  async getCargoSelectedOption(): Promise<string> {
    return await this.cargoSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class FuncionarioDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-funcionario-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-funcionario'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
