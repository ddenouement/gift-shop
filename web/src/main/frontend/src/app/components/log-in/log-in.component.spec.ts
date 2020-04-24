import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {browser, protractor} from 'protractor';
import {LogInComponent} from './log-in.component';
import {LoginPage} from '../../e2e/login.po';
import {HomePage} from "../../e2e/homepage.po";
import {CartPage} from "../../e2e/cart.po";

describe('LogInComponent', () => {
  let component: LogInComponent;
  let fixture: ComponentFixture<LogInComponent>;
  let page: LoginPage;
  const EC = protractor.ExpectedConditions;


  beforeEach(() => {
    page = new LoginPage();
    browser.get('/#/login');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display an error message to the user if they provided ' +
    'incorrect credentials', () => {

    browser.wait(EC.visibilityOf(page.email));
    page.email.sendKeys('123@email.com');
    page.password.sendKeys('123');
    page.signIn.click();
    browser.wait(EC.visibilityOf(page.errorMessage));
    expect(page.errorMessage.getText()).toEqual('Provided password and login are invalid');
  });

  it('should redirect the user to the main page if they provided' +
    ' correct credentials', () => {
    const mainPage = new HomePage();
    page.email.sendKeys('julia@gmail.com');
    page.password.sendKeys('julia');
    page.signIn.click();
    browser.wait(EC.visibilityOf(mainPage.filterPanel));
    expect(mainPage.filterPanel.isPresent()).toBeTruthy();
  });
});
