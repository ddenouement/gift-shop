import {browser, element, by} from 'protractor';
export class LoginPage {

  get email() {
    return element(by.id('email-form-input'));
  }

  get password() {
    return element(by.css('.password-form-input'));
  }
  get errorMessage() {
    return element(by.css('.error-div'));
  }
  get signIn() {
    return element(by.css('.login_button'));
  }
}
