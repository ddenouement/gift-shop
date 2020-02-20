import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";
import {AlertService} from "../../_services";
import {ProductService} from "../../_services/product.service";
import {Product} from "../../_models/product";
import {Category} from "../../_models/category";
import {CategoryService} from "../../_services/category.service";

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {
  id: number;
  private sub: any;
  current_product: Product;
  editForm: FormGroup;
  loading = false;
  submitted = false;

  categoriesList: Category[];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService,
    private categoryService: CategoryService,
    private alertService: AlertService) {

  }

  close(){
    this.router.navigate(['/']);
  }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
      productId: ['', Validators.required],
      productName: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      photo: [''],
      isAvailable: [false, Validators.required]
    });


    this.sub = this.route.params.subscribe(params => {
      this.loading = true;

      this.categoryService.getAll().subscribe(data =>{
          this.loading = false;
          this.categoriesList = data;

          this.id = +params['id'];

          this.productService.getById(+this.id)
            .subscribe(productData =>{
                this.loading = false;
                this.current_product = productData;

                this.editForm.controls["productId"].setValue(productData.productId);
                this.editForm.controls["productName"].setValue(productData.productName);
                this.editForm.controls["description"].setValue(productData.description);
                this.editForm.controls["price"].setValue(productData.price);
                this.editForm.controls["photo"].setValue(productData.photo);
                this.editForm.controls["isAvailable"].setValue(productData.isAvailable);
                this.editForm.controls["categories"].setValue(productData.categories);
              },
              error => {
                this.alertService.error(error);
                this.loading = false;
              });


        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });

    });
  }

  get f() { return this.editForm.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.editForm.invalid) {
      return;
    }

    this.current_product=this.editForm.value;

    this.loading = true;
    this.productService.update(this.current_product)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Product edited successful', true);
          this.router.navigate(['/']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
