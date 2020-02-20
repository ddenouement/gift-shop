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
  productsToShow: Product[];
  page: number;
  pages: number;
  isLoading: boolean;

  lastRow = 0;
  productsChunkSize = DEFAULT_PRODUCTS_PER_PAGE;
  products: Product[];
  private sub: Subscription;


  constructor(
    private authenticationService: AuthenticationService,
    private productService: ProductService
  ) {  }

  ngOnInit() {
    this.isLoading = true;
    this.products = [];
    this.page = 1;
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });

    this.productService.getAmount()
      .subscribe(amount => {
        this.isLoading = false;
        this.pages = Math.floor(amount / DEFAULT_PRODUCTS_PER_PAGE);
      });

    this.loadProductsChunk();
  }

  onScrollDown() {
    this.loadProductsChunk();
  }

  loadProductsChunk() {
      this.isLoading = true;

      /*this.productService.getFromTo(this.lastRow, this.productsChunkSize).toPromise().then(data =>
      {
        this.isLoading = false;
        if (data) {
          this.lastRow += data.length;
          console.log(data);
          this.products.push(...data);
        }
      }, error => {
        console.log(error);
      })*/
      console.log(this.products);

      this.productService.getFromTo(DEFAULT_PRODUCTS_PER_PAGE*(this.page-1), DEFAULT_PRODUCTS_PER_PAGE).subscribe(
        data => {
          this.isLoading = false;
          if (data) {
            this.lastRow += data.length;
            console.log(data);
            this.products.push(...data);
          }
        }, error => {
          console.log(error);
        })
  }
}
