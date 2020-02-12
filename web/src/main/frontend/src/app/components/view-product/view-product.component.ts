import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ViewProductService} from "../../_services/view-product.service";
import {Product} from "../../_models/product";

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.css']
})
export class ViewProductComponent implements OnInit {
  id: number;
  private sub: any;
  current_product: Product;
  isLoading: boolean;
  amount_added_to_cart: number;

  constructor(private route: ActivatedRoute, private productviewService: ViewProductService) {}

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

        });

    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  decrease(){
    if(this.amount_added_to_cart >0){
      this.amount_added_to_cart -=1;;
    }
  }
  increase() {
    this.amount_added_to_cart+=1;
  }
}
