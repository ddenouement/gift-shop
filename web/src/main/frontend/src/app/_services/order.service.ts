import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

import { environment } from '../../environments/environment';
import {OrderDTO} from "../_models/OrderDTO";

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
}
