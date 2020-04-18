import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

import { environment } from '../../environments/environment';
import {Order} from "../_models/order";
import {Observable} from "rxjs";
import {Category} from "../_models/category";
import {Product} from "../_models/product";

@Injectable({ providedIn: 'root' })
export class OrderService {

  constructor(private http: HttpClient) {

  }

  create(order: Order) {
    return this.http.post(`${environment.apiUrl}/orders`, order);
  }
  getSumByOrderLines(ord:{productId:number, quantity:number}[]){
    console.log("sum of  : "+ord.toString());
     return this.http.post(`${environment.apiUrl}/cart/checkSum`, ord);
}

  getAll(): Observable<Order[]> {
    return this.http.get<Order[]>(`${environment.apiUrl}/orders`);
  }

  getById(id: number){
    return this.http.get<Order>(`${environment.apiUrl}/orders/${id}`);
  }

  getOrderProducts(id:number): Observable<{product:Product, quantity:number}[]> {
    return this.http.get<{product:Product, quantity:number}[]>(`${environment.apiUrl}/orders/${id}/products`);
  }
}
