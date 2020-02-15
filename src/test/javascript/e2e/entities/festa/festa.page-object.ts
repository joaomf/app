import { element, by, ElementFinder } from 'protractor';

export class FestaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-festa div table .btn-danger'));
  title = element.all(by.css('jhi-festa div h2#page-heading span')).first();
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

export class FestaUpdatePage {
  pageTitle = element(by.id('jhi-festa-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomeInput = element(by.id('field_nome'));
  descricaoInput = element(by.id('field_descricao'));

  tipoFestaSelect = element(by.id('field_tipoFesta'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNomeInput(nome: string): Promise<void> {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput(): Promise<string> {
    return await this.nomeInput.getAttribute('value');
  }

  async setDescricaoInput(descricao: string): Promise<void> {
    await this.descricaoInput.sendKeys(descricao);
  }

  async getDescricaoInput(): Promise<string> {
    return await this.descricaoInput.getAttribute('value');
  }

  async tipoFestaSelectLastOption(): Promise<void> {
    await this.tipoFestaSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async tipoFestaSelectOption(option: string): Promise<void> {
    await this.tipoFestaSelect.sendKeys(option);
  }

  getTipoFestaSelect(): ElementFinder {
    return this.tipoFestaSelect;
  }

  async getTipoFestaSelectedOption(): Promise<string> {
    return await this.tipoFestaSelect.element(by.css('option:checked')).getText();
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

export class FestaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-festa-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-festa'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
