import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Product} from "../_models/product";

@Injectable({ providedIn: 'root' })
export class ViewProductService {
  constructor(private http: HttpClient) {
  }


  getById(id: number) {
    return this.http.get<Product>(`${environment.apiUrl}/products/${id}`);
  }
}
