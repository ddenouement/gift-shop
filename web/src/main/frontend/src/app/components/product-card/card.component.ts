import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../_models/product';
import {LocalStorageService} from "../../_services/localstorage.service";
import {Router} from "@angular/router";
import {AuthenticationService, UserService} from "../../_services";
import {WishlistService} from "../../_services/wishlist.service";
import {SidenavService} from "../../_services/sidenav.service";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input() product: Product;
  role: string;

  constructor( private wishService: WishlistService, private local: LocalStorageService, private router: Router, private authService : AuthenticationService){}
  ngOnInit() {
    this.authService.currentUser.subscribe( data => {
      this.role = this.authService.getRole();
    });
  }

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
      alert("changed amount of products: existing amount of "+ first_quantity+" plus "+ new_quantity);

    }
    localStorage.setItem('products', JSON.stringify(products));
    alert("saved to cart");
  }

  addProductToWishlist() {

      this.wishService.addWishedProduct(this.product.productId + "").subscribe(data => {
          alert("succesfully added");
        },
        error => {
          alert("you have it already in wishlist");
        });

  }

  navigateToItemPage() {
    if(this.role == 'ADMIN'){   this.router.navigate(["/product/edit/"+this.product.productId]);}
    else  this.router.navigate(["/product/"+this.product.productId]);
  }
}
