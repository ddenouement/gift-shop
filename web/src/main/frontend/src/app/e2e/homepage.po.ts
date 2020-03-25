import {by, element} from 'protractor';
export class HomePage {

  get filterPanel() {
    return element(by.className('filter-panel'));
  }
}
