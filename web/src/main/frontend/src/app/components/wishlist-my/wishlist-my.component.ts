import { Component, OnInit } from '@angular/core';
import {WishlistService} from "../../_services/wishlist.service";
import {Product} from "../../_models/product";

@Component({
  selector: 'app-wishlist-my',
  templateUrl: './wishlist-my.component.html',
  styleUrls: ['./wishlist-my.component.css']
})
export class WishlistMyComponent implements OnInit {

  products: Product[];
  constructor(private _wishService: WishlistService) { }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
   this._wishService.getMyWishedProducts().subscribe( data => {
this.products = data;
   },
     error => {
     })
  }
  removeProduct(  id: number){
    this._wishService.removeMyWishedProduct(id+"").subscribe(data => {

      },
      error => {
        console.log(error.message);
      })
  }

}
