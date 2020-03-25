import { Component, OnInit } from '@angular/core';
import {Product} from "../../_models/product";
import {OrderService} from "../../_services/order.service";
import {AuthenticationService} from "../../_services";
import {Router} from "@angular/router";
import {LocalStorageService} from "../../_services/localstorage.service";
import {ProductService} from "../../_services/product.service";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  isNotLogged: boolean;
  orderLines: {productId: number ; quantity: number} [] ;
  products: { product: Product ; quantity: number}[] = [];
  sum:number;
  constructor(private local: LocalStorageService, private  productService: ProductService, private router: Router ,private orderService:OrderService, private auth: AuthenticationService, private localservice: LocalStorageService) {

  }

  ngOnInit() {

    this.isNotLogged = true;
    this.auth.isLoggedAsUser().subscribe(x =>{if(x===1) this.isNotLogged = false}, error => {this.isNotLogged = true;});

    if(this.localservice.existsCartInMemory()){
      this.orderLines = this.localservice.getOrderLines();
    }

    for (let ord of this.orderLines)
    {
      this.productService.getById(ord.productId).subscribe(
        data=>{
          this.products.push({'product': data, 'quantity': ord.quantity});

        }

      )
    }
        this.getSum();
  }

  private  getSum() {
      this.orderService.getSumByOrderLines(this.orderLines).subscribe(data =>{
          this.sum  = +data;
        }    ,
        error => {alert(error.message)});
    }

  deleteLocalStorage(){
    this.localservice.clearLocalStorage() ;
    this.orderLines=[];
    this.products=[];
    this.sum = undefined;
  }
 createOrder(){
    if(this.sum && this.orderLines) {
      if (!this.isNotLogged) {
        this.router.navigate(['/order']);
      } else {
        this.router.navigate(['/login']);
      }
    }
 }

  removeProduct(productId: number) {

    alert(this.products.length);
    let products = [];
    if (this.local.existsCartInMemory()) {
      products = this.local.getOrderLines();
    }
    const local_index = products.findIndex(k => k.productId === productId);
    products.splice(local_index, 1);

   localStorage.setItem('products', JSON.stringify(products));

   const p_index = this.products.findIndex(k => k.product.productId === productId);
   this.products.splice(p_index, 1);

}
}
