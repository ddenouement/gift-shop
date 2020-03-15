import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

import { environment } from '../../environments/environment';
import {Order} from "../_models/order";
import {Observable} from "rxjs";
import {Category} from "../_models/category";
import {Product} from "../_models/product";

@Injectable({ providedIn: 'root' })
export class WishlistService {

  constructor(private http: HttpClient) {

  }

  removeMyWishedProduct(productId: string):Observable<any>{
    return this.http.delete<any>(`${environment.apiUrl}/user/wishlist/${productId}`, null);

  }
  addWishedProduct(productId: string):Observable<any>{
    return this.http.put<any>(`${environment.apiUrl}/user/wishlist/${productId}`, null);
  }

  getMyWishedProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.apiUrl}/user/wishlist/`);
  }

  getOthersWishedProducts( userId: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.apiUrl}/user/wishlist/${userId}`);
  }
}
