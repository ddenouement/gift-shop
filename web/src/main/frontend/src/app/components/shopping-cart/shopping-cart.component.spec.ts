import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ShoppingCartComponent} from './shopping-cart.component';
import {
  browser,
  by,
  element,
  ElementArrayFinder,
  ElementFinder,
  protractor
} from "protractor";
import {LogInComponent} from "../log-in/log-in.component";
import {LoginPage} from "../../e2e/login.po";
import {CartPage} from "../../e2e/cart.po";
import {ProductViewPage} from "../../e2e/product-view.po";
import {Header} from "../../e2e/header.po";
import {log} from "util";
import {buildDriverProvider} from "protractor/built/driverProviders";

describe('ShoppingCartComponent', () => {
  let component: ShoppingCartComponent;
  let page: CartPage;
  let product_page: ProductViewPage;
  let header: Header;
  let login_page: LoginPage;
  const EC = protractor.ExpectedConditions;
  const id_of_product = 2;
  const amount_of_product = 5;


  beforeEach(() => {
    page = new CartPage();
    header = new Header();
    login_page = new LoginPage();
    product_page = new ProductViewPage();
    browser.get('/#/cart');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  async function tryDisplayed(elem: any): Promise<boolean> {
    try {
      var isPresent = await elem.isPresent();
      if (!isPresent) {
        return false;
      }
      return await elem.isDisplayed();
    } catch (error) {
      return false;
    }
  }

  it('should delete all products from cart', () => {
    browser.wait(EC.visibilityOf(page.deleteCartBtn));
    page.deleteCartBtn.click();

    expect(tryDisplayed(by.className('one-cart-item'))).toBeFalsy();
  });
  it('should display correct sum of one added product', () => {
    browser.wait(EC.visibilityOf(page.deleteCartBtn));
    page.deleteCartBtn.click();

    browser.get('/#/product/' + id_of_product);
    product_page = new ProductViewPage();

    let product_price = 0;
    browser.driver.findElement(by.className('price-field')).getText().then(function (text) {
        product_price = +text;
        expect(product_price).toBeDefined();
      }
    );

    product_page.addToCartBtn.click();
    browser.driver.switchTo().alert().accept();
    browser.get('/#/cart');
    browser.wait(EC.visibilityOf(page.sumField));
    let sum_all = 0;
    page.sumField.getText().then(function (text) {
      sum_all = +text;
    });
    expect(sum_all == product_price).toBeTruthy();

  });

  it('should display correct sum of several same added products', () => {
    browser.wait(EC.visibilityOf(page.deleteCartBtn));
    page.deleteCartBtn.click();

    browser.get('/#/product/' + id_of_product);
    browser.wait(EC.visibilityOf(product_page.priceField));

    let product_price = 0;
    product_page.priceField.getText().then(function (text) {
      product_price = +text;
    });

    for (var i = 0; i < amount_of_product; i++) {
      product_page.addToCartBtn.click();
      browser.driver.switchTo().alert().accept();
    }


    browser.get('/#/cart');
    browser.wait(EC.visibilityOf(page.sumField));
    let sum_all = 0;
    page.sumField.getText().then(function (text) {
      sum_all = +text;
    });
    expect(sum_all == product_price * amount_of_product).toBeTruthy();
  });


  it('should redirect to login when unauthorized user orders', () => {
    browser.wait(EC.visibilityOf(page.deleteCartBtn));
    page.deleteCartBtn.click();

     header.logoutLink.isDisplayed().then( res => {
       header.logoutLink.click();
     },
       err =>{

       })  ;

    browser.get('/#/product/' + id_of_product);
     browser.wait(EC.visibilityOf(product_page.priceField));
    for (var i = 0; i < amount_of_product; i++) {
      product_page.addToCartBtn.click();
      browser.driver.switchTo().alert().accept();
    }

    browser.get('/#/cart');
    browser.wait(EC.visibilityOf(page.createOrderBtn));

    page.createOrderBtn.click();

    browser.wait(EC.visibilityOf(login_page.email));
    expect(login_page.email.isPresent()).toBeTruthy();
  });
});
