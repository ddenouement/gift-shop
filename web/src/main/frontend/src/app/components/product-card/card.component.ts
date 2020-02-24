import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../_models/product';
import {LocalStorageService} from "../../_services/localstorage.service";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input() product: Product;

  constructor(private local: LocalStorageService){}
  ngOnInit() {}

  addProductToCart() {
    let products = [];
    if(this.local.existsCartInMemory()){
      products =this.local.getOrderLines();
    }
    const  index =  products.findIndex(k => k.productId===this.product.productId);
    if( index === -1){
      products.push({'productId' : this.product.productId, 'quantity' : 1});

    }
    else {
      const first_quantity = products[index].amount;
      const new_quantity = first_quantity + 1;
      products.splice(index,1);
      products.push({'productId' : this.product.productId, 'quantity' : new_quantity});

      alert("changed amount of products");
    }
    localStorage.setItem('products', JSON.stringify(products));
    alert("saved to cart");
  }

  addProductToWishlist() {}
}
