import {by, element} from 'protractor';
export class OrderCreatePage {

  get submitOrderBtn() {
    return element(by.className('submit-order-button'));
  }
}
