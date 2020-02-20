import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import {Observable} from "rxjs";
import {Category} from "../_models/category";

@Injectable({ providedIn: 'root' })
export class CategoryService {
  constructor(private http: HttpClient) { }

  getAll(): Observable<Category[]> {
    return this.http.get<Category[]>(`${environment.apiUrl}/categories`);
  }

  getById(id: number) {
    return this.http.get<Category>(`${environment.apiUrl}/categories/${id}`);
  }
}
