import {by, element} from 'protractor';
export class CartPage {

  get sumField() {
    return element(by.className('sum-field'));
  }
  get deleteCartBtn(){
    return element(by.className('delete-cart-btn'));
  }
  get createOrderBtn(){
    return element(by.className('create-order-btn'));
  }

}
