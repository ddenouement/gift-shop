import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ViewProductService} from "../../_services/view-product.service";
import {Product} from "../../_models/product";
import {first} from "rxjs/operators";
import {LocalStorageService} from "../../_services/localstorage.service";

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.css']
})
export class ViewProductComponent implements OnInit, OnDestroy {
  id: number;
  private sub: any;
  current_product: Product;
  isLoading: boolean;
  amount_added_to_cart: number;

  constructor(private route: ActivatedRoute, private productviewService: ViewProductService, private local: LocalStorageService) {}

  ngOnInit() {

    this.amount_added_to_cart = 1;
    this.sub = this.route.params.subscribe(params => {
      this.isLoading = true;
      this.id = +params['id']; // (+) converts string 'id' from path to a number
      //when ID loaded, we can access DB to get Product Object
      this.productviewService.getById(+this.id)
        .subscribe(productData =>{
          this.isLoading = false;
          this.current_product = productData;
//todo load categories
        });

    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
    this.amount_added_to_cart=1;
  }

  decrease(){
    if(this.amount_added_to_cart >0){
      this.amount_added_to_cart -=1;
    }
  }
  increase() {
    this.amount_added_to_cart+=1;
  }

  addToCart() {
    let products = [];
    if(this.local.existsCartInMemory()){
      products =this.local.getOrderLines();
    }
    const  index =  products.findIndex(k => k.productId===this.id);
    if( index === -1){
      products.push({'productId' : this.id, 'quantity' : this.amount_added_to_cart});

    }
    //якщо вже такий продукт є, додаємо кількість до існючого (замінюємо на нове значення)
    else {
      const first_quantity = products[index].amount;
      const new_quantity = first_quantity + this.amount_added_to_cart;
      products.splice(index,1);
      products.push({'productId' : this.id, 'quantity' : new_quantity});

      alert("changed amount of products");
    }
    localStorage.setItem('products', JSON.stringify(products));
    alert("saved to cart");
  }
}
