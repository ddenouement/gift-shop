import { Component, OnInit } from '@angular/core';
import {Order} from "../../_models/order";
import {OrderService} from "../../_services/order.service";
import {Product} from "../../_models/product";

@Component({
  selector: 'app-order-view-admin',
  templateUrl: './order-view-admin.component.html',
  styleUrls: ['./order-view-admin.component.css']
})
export class OrderViewAdminComponent implements OnInit {

  currentOrder: number;
  showModal:boolean;
  products_of_order: { product: Product; quantity: number }[];
  orders: Order[];
  displayedColumns:  string[] = ['orderId', 'date', 'orderState', 'totalSum', 'payment', 'userId', 'products'];
  states: {name:string, id: number}[] = [{'name':"NEW",'id': 1},{'name':'INPROGRESS','id':2},{'name':'CANCELLED','id':3},{'name':'DELIVERED','id':4}];
  constructor(private order_service: OrderService) { }

  ngOnInit() {
    this.order_service.getAll().subscribe( data=>{
      console.log(data);
      this.orders=data;
    })
  }
  hide()
  {
    this.showModal = false;
  }
  getProductsOfOrder(orderId : number){
    this.showModal = true;
    this.order_service.getOrderProducts(orderId).subscribe(data=>{
      console.log(data);
      this.products_of_order = data;
    })
  }

}
