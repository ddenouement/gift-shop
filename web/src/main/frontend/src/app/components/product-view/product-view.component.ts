import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../_models/product";
import {LocalStorageService} from "../../_services/localstorage.service";
import {ProductService} from "../../_services/product.service";
import {WishlistService} from "../../_services/wishlist.service";
import {AuthenticationService, UserService} from "../../_services";
import {error} from "util";
import {CategoryService} from "../../_services/category.service";
import {User} from "../../_models";

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
  categories: string[] = [];
  amount_added_to_cart: number;
  currentUser: User;

  constructor(private route: ActivatedRoute, private productService: ProductService,
              private wishService: WishlistService, private local: LocalStorageService,
              private  auth: AuthenticationService,
              private categoryService: CategoryService
  ) {
  }

  ngOnInit() {
    this.auth.currentUser.subscribe(res => {
      this.currentUser = res;
    });

    this.amount_added_to_cart = 1;
    this.sub = this.route.params.subscribe(params => {
      this.isLoading = true;
      this.id = +params['id']; // (+) converts string 'id' from path to a number
      //when ID loaded, we can access DB to get Product Object
      this.productService.getById(+this.id)
        .subscribe(productData => {
            this.isLoading = false;
            this.current_product = productData;
            for (let cat of this.current_product.categories) {
              this.getNameOfCategory(cat).subscribe(data => {
                  this.categories[cat] = data.categoryName;
                },
                error => {
                  this.categories[cat] = "";
                });
            }

          }
        );

    });
  }


  ngOnDestroy() {
    this.sub.unsubscribe();
    this.amount_added_to_cart = 1;
  }

  decrease() {
    if (this.amount_added_to_cart > 0) {
      this.amount_added_to_cart -= 1;
    }
  }

  increase() {
    this.amount_added_to_cart += 1;
  }

  addToCart() {
    let products = [];
    if (this.local.existsCartInMemory()) {
      products = this.local.getOrderLines();
    }
    const index = products.findIndex(k => k.productId === this.id);
    if (index === -1) {
      products.push({
        'productId': this.id,
        'quantity': this.amount_added_to_cart
      });

    }
    //якщо вже такий продукт є, додаємо кількість до існючого (замінюємо на нове значення)
    else {
      const first_quantity = products[index].quantity;
      const new_quantity = first_quantity + this.amount_added_to_cart;
      products.splice(index, 1);
      products.push({'productId': this.id, 'quantity': new_quantity});

    }
    localStorage.setItem('products', JSON.stringify(products));
    alert("saved to cart");
  }

  addToWishlist() {
    if (this.currentUser) {
      let r = localStorage.getItem('role').toString();
      if (r == 'USER') {
        this.doAddToWishList();
      } else {
        alert("please login as a user to use wishlist");
      }
    }
  }

  private doAddToWishList() {
    this.wishService.addWishedProduct(this.current_product.productId + "").subscribe(data => {
        alert("succesfully added");
      },
      error => {
        alert("you have it already in wishlist");
      });

  }

  getNameOfCategory(categoryId: number) {
    return this.categoryService.getById(categoryId);
  }
}

