import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from '../../_models';
import { AuthenticationService } from '../../_services';
import { ProductService } from '../../_services/product.service';
import { Product } from '../../_models/product';

export const DEFAULT_PRODUCTS_PER_PAGE = 12;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currentUser: User;
  currentUserSubscription: Subscription;
  products: Product[];
  productsToShow: Product[];
  page: number;
  pages: number;
  isLoading: boolean;

  constructor(
    private authenticationService: AuthenticationService,
    private productService: ProductService
  ) {
    this.isLoading = true;
    this.products = [];
    this.page = 1;
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
    this.productService.getAll()
      .subscribe(products => {
        this.isLoading = false;
        this.products = products;
        this.productsToShow = products.slice(0, DEFAULT_PRODUCTS_PER_PAGE);
        this.pages = Math.floor(products.length / DEFAULT_PRODUCTS_PER_PAGE);
      });
  }

  ngOnInit() {}

  updateProductsDisplayedInPage(event) {}
}
