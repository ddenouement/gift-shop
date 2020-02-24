import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

import { environment } from '../../environments/environment';
import {OrderDTO} from "../_models/OrderDTO";
import {Observable} from "rxjs";
import {Category} from "../_models/category";
import {Product} from "../_models/product";

@Injectable({ providedIn: 'root' })
export class OrderService {

  constructor(private http: HttpClient) {

  }

  create(orderDTO: OrderDTO) {
    return this.http.post(`${environment.apiUrl}/orders`, orderDTO);
  }
  getSumByOrderLines(ord:{productId:number, quantity:number}[]){
     return this.http.post(`${environment.apiUrl}/orders/checkSum`, ord);
}

  getAll(): Observable<OrderDTO[]> {
    return this.http.get<OrderDTO[]>(`${environment.apiUrl}/orders`);
  }

  getOrderProducts(id:number): Observable<{product:Product, quantity:number}[]> {
    return this.http.get<{product:Product, quantity:number}[]>(`${environment.apiUrl}/orders/${id}/products`);
  }
}
