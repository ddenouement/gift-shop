import { Component, OnInit } from '@angular/core';
import {Product} from "../../_models/product";
import {OrderDTO} from "../../_models/OrderDTO";
import {OrderService} from "../../_services/order.service";
import {AuthenticationService} from "../../_services";
import {IndexHtmlWebpackPlugin} from "@angular-devkit/build-angular/src/angular-cli-files/plugins/index-html-webpack-plugin";
import {IHash} from "../../_models/IHash";
import {Router} from "@angular/router";
import {LocalStorageService} from "../../_services/localstorage.service";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
    isLogged: boolean;
  orderLines: {productId: number ; quantity: number} [] ;
  products: Product[];
  sum:number;
  constructor(private router: Router ,private orderService:OrderService, private auth: AuthenticationService, private localservice: LocalStorageService) {

  }

  ngOnInit() {
    if(this.localservice.existsCartInMemory()){
      this.orderLines = this.localservice.getOrderLines();
    }
this.isLogged = false;
    this.auth.isLoggedAsUser().subscribe(x =>{if(x===1) this.isLogged = true}, error => {this.isLogged = false;});
    this.getSum();
    //todo load all these products by id from server

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
  }
 createOrder(){
    //todo if user not authorized  ask him to login
   this.router.navigate(['/order']);
 }

}
