import { Component, OnInit } from '@angular/core';
import {OrderDTO} from "../../_models/OrderDTO";
import {Product} from "../../_models/product";
import {OrderService} from "../../_services/order.service";
import {AuthenticationService} from "../../_services";

@Component({
  selector: 'app-create-order',
  templateUrl: './order-create.component.html',
  styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {
  selectedLink: string="";
  isLogged: boolean;
  orderLines: {productId: number ; quantity: number} [] ;
  products: Product[];
  sum:number;
  cashPayment: boolean;
  postDelivery: boolean;

  constructor(private orderService:OrderService, private auth: AuthenticationService) {

  }

  ngOnInit() {
    if(localStorage.getItem('products')){
      this.orderLines = JSON.parse(localStorage.getItem('products'));
    }
    this.auth.isLoggedAsUser().subscribe(x =>{if(x===1) this.isLogged = true}, error => {this.isLogged = false;});



    this.getSum();
  }

  private getSum() {
    this.orderService.getSumByOrderLines(this.orderLines).subscribe(data =>{
        this.sum  = +data;
      }    ,
      error => {alert(error.message)});
  }

  sendData(inputCashPayment:boolean, inputAddress:string, inputPostDelivery:boolean, inputTotalSum:number) {
      //todo example of sending created Order to backend
      const order : OrderDTO = new OrderDTO();
      order.orderDate = new Date();
      order.cashPayment = inputCashPayment;
      order.address = inputAddress;
      order.orderId = 0;
      order.postDelivery = inputPostDelivery;
      order.totalSum = inputTotalSum;
      //will get on server from token
      order.userId = 0;
      //CREATED status = 1
      order.orderState = 1;
  //set array to  DTO to send to server
      order.orderItems = this.orderLines;

      this.orderService.create(order).subscribe(data =>  {
          alert("success");
        },
        error => {alert("error"+error.message)});
    }

    setRadio(e: string): void{
      this.selectedLink = e;
    }

    isSelected(s: string): boolean{
      if (!this.selectedLink) {
        // if no radio button is selected, always return false so every nothing is shown
        return false;
      }
      // if current radio button is selected, return true, else return false
      return (this.selectedLink === s);
      }
}
