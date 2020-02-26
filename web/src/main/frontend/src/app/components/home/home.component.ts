import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from '../../_models';
import { AuthenticationService } from '../../_services';
import { ProductService } from '../../_services/product.service';
import { Product } from '../../_models/product';
import {PageEvent} from "@angular/material/paginator";
import {Category} from "../../_models/category";
import {CategoryService} from "../../_services/category.service";
import { IDropdownSettings } from "ng-multiselect-dropdown";
import { Options, LabelType } from "ng5-slider";
export const DEFAULT_PRODUCTS_PER_PAGE = 15;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css', './custom-slider.css']
})
export class HomeComponent implements OnInit {
  isFilterUsed:boolean;
  categories: Category[];
  currentUser: User;
  currentUserSubscription: Subscription;
  selectedCategoriesFromFilter: Category[];
  dropdownSettings: IDropdownSettings;
  page: number;
  pages: number;
  isLoading: boolean;
  totalNumber: number;
  pageEvent: PageEvent;
  lastRow = 0;
  productsChunkSize = DEFAULT_PRODUCTS_PER_PAGE;
  products: Product[];
  private sub: Subscription;

  min = 0;
  max = 10000;
  optionsPriceSlider: Options = {
    floor: 0,
    ceil: 10000,
    translate: (value: number, label: LabelType): string => {
      switch (label) {
        case LabelType.Low:
          return '<b>Min price:</b> ₴' + value;
        case LabelType.High:
          return '<b>Max price:</b> ₴' + value;
        default:
          return '₴' + value;
      }
    }
  };

  constructor(
    private authenticationService: AuthenticationService,
    private productService: ProductService,
    private  categoryService: CategoryService
  ) {  }

  ngOnInit() {
    this.isFilterUsed=false;
    this.isLoading = true;
    this.products = [];
    this.categories = [];
    this.page = 0;
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
    this.categoryService.getAll().subscribe(data => {
       this.categories = data;
    });
    this.productService.getAmount()
      .subscribe(amount => {
        this.isLoading = false;
        this.totalNumber = amount;
        this.pages = Math.floor(amount / DEFAULT_PRODUCTS_PER_PAGE);
      });
    this.loadProductsChunk();
    //to create dropdown list with categories in filter
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'categoryId',
      textField: 'categoryName',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }
  /*
  onScrollDown() {
    this.loadProductsChunk();
  }*/
  public handlePage(e: any) {
    this.page = e.pageIndex;
    this.loadProductsChunk();
  }
  private getSelectedCategoriesIds(){
    if(this.selectedCategoriesFromFilter )
     return this.selectedCategoriesFromFilter.map( a =>  a.categoryId );
    else return [];
  }
  loadProductsChunk() {
      this.isLoading = true;
  this.productService.getFromToByCategories(this.min, this.max,DEFAULT_PRODUCTS_PER_PAGE*(this.page), DEFAULT_PRODUCTS_PER_PAGE, this.getSelectedCategoriesIds(), this.isFilterUsed).subscribe(
  //  this.productService.getFromTo(this.min, this.max,DEFAULT_PRODUCTS_PER_PAGE*(this.page), DEFAULT_PRODUCTS_PER_PAGE ).subscribe(

      data => {
          this.isLoading = false;
          if (data) {
            this.lastRow += data.length;
            console.log(data);
            this.products = [];
            this.products.push(...data);
          }
        }, error => {
          alert("error loading page "+this.page+" : "+error);
        })
  }

  applyFilters() {
    this.isFilterUsed = true;
this.products=[];
this.page=0;
    this.loadProductsChunk();
  }

  clearFilters() {
    this.isFilterUsed = false;
  this.selectedCategoriesFromFilter = [];
  this.max = 10000;
  this.min=0;
  this.page = 0;
  this.loadProductsChunk();
  }
}
