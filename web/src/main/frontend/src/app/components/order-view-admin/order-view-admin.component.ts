import { Component, OnInit } from '@angular/core';
import {Order} from "../../_models/order";
import {OrderService} from "../../_services/order.service";
import {Product} from "../../_models/product";
import {UserService} from "../../_services";

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
  displayedColumns:  string[] = ['orderId', 'date', 'orderState', 'totalSum', 'address', 'userId', 'userName', 'phone', 'email', 'products'];
  states: {name:string, id: number}[] = [{'name':"NEW",'id': 1},{'name':'INPROGRESS','id':2},{'name':'CANCELLED','id':3},{'name':'DELIVERED','id':4}];

  constructor(private userService: UserService,
              private orderService: OrderService) { }

  ngOnInit() {

    this.userService.getUserInfo(+102).subscribe(info => {
      console.log(info);
    })

    this.orderService.getAll().subscribe( data=>{

      this.orders = data;

      if (this.orders) {
        this.orders.forEach(order => {
          this.userService.getUserInfo(+order.userId).subscribe(info => {
            order.userName = info.surname+" "+info.name;
            order.phone = info.phoneNumber;
            order.email = info.email;
          })
        })
      }

      console.log(data);
    });
  }
  hide()
  {
    this.showModal = false;
  }
  getProductsOfOrder(orderId : number){
    this.showModal = true;
    this.orderService.getOrderProducts(orderId).subscribe(data=>{
      console.log(data);
      this.products_of_order = data;
    })
  }

}
