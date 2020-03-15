import { Component, OnInit } from '@angular/core';
import {Product} from "../../_models/product";
import {WishlistService} from "../../_services/wishlist.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-wishlist-other',
  templateUrl: './wishlist-other.component.html',
  styleUrls: ['./wishlist-other.component.css']
})
export class WishlistOtherComponent implements OnInit {

  private id:number;
  products: Product[];
  constructor( private route: ActivatedRoute,private _wishService: WishlistService) { }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.route.params.subscribe(data => {

        this.id = +data['id'];
        this._wishService.getOthersWishedProducts(this.id + "").subscribe(data => {
            this.products = data;
          },
          error => {
            alert(error.message);
          })
      }
    );
  }
}
