import {by, element} from 'protractor';
export class Header {

  get logoutLink() {
    return element(by.className('logout-button'));
  }
}
