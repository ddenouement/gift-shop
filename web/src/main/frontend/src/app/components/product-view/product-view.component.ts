import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../_models/product";
import {LocalStorageService} from "../../_services/localstorage.service";
import {ProductService} from "../../_services/product.service";

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit, OnDestroy {
  id: number;
  private sub: any;
  current_product: Product;
  isLoading: boolean;
  amount_added_to_cart: number;

  constructor(private route: ActivatedRoute, private productService: ProductService, private local: LocalStorageService) {}

  ngOnInit() {

    this.amount_added_to_cart = 1;
    this.sub = this.route.params.subscribe(params => {
      this.isLoading = true;
      this.id = +params['id']; // (+) converts string 'id' from path to a number
      //when ID loaded, we can access DB to get Product Object
      this.productService.getById(+this.id)
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
