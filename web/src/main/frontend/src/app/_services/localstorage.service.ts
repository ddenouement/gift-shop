import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class LocalStorageService {

  existsCartInMemory(){
   return localStorage.getItem('products');
  }
  clearLocalStorage(){
    localStorage.clear() ;
  }
  getOrderLines() {
    return JSON.parse(localStorage.getItem('products'));

  }
}
