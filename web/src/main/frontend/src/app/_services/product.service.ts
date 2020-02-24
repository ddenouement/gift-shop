import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

import { environment } from '../../environments/environment';
import { Product } from '../_models/product';
import {Observable} from "rxjs";

@Injectable({ providedIn: 'root' })
export class ProductService {
  constructor(private http: HttpClient) { }

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.apiUrl}/products`);
  }

  getAmount() {
    return this.http.get<number>(`${environment.apiUrl}/products/amount`);
  }

  getFromTo(min: number, max: number, from: number, to:number): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.apiUrl}/products/${min}/${max}/${from}/${to}`);
  }
  getFromToByCategories(min: number, max: number, from: number, to:number, categoriesIds:number[], isFiltersUsed:boolean): Observable<Product[]>{
  if(isFiltersUsed) {
    if(!categoriesIds || categoriesIds.length===0){
      return this.getFromTo(min, max, from, to);
    }
    else {
      let params = new HttpParams();
      categoriesIds.forEach((n: number) => {
        params = params.append(`categories`, "" + n);
      });
      return this.http.get<Product[]>(`${environment.apiUrl}/products/${min}/${max}/category/${from}/${to}`, {params: params});
    }
  }
  else{
    return this.getFromTo(min, max, from, to);
  }

  }

  getById(id: number) {
    return this.http.get<Product>(`${environment.apiUrl}/products/${id}`);
  }

  create(product: Product) {
    return this.http.post(`${environment.apiUrl}/products`, product);
  }

  update(product: Product) {
    return this.http.put(`${environment.apiUrl}/products`, product);
  }
}
