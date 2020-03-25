import {browser, by, element} from 'protractor';
export class ProductViewPage {

  get priceField() {
    return  browser.driver.findElement(by.className('price-field'));// element(by.className('price-field'));
  }
  get addToCartBtn() {
    return element(by.className('add-to-cart'));
  }

}
