import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { ProductEditComponent} from "./components/product-edit/product-edit.component";
import {ViewProductComponent} from "./components/view-product/view-product.component";
import {ShoppingCartComponent} from "./components/shopping-cart/shopping-cart.component";
import {CreateOrderComponent} from "./components/create-order/create-order.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LogInComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'product/edit/:id', component: ProductEditComponent },
  {path: 'viewproduct/:id', component: ViewProductComponent},
  {path: 'cart', component: ShoppingCartComponent},
  {path: 'order', component: CreateOrderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})

export class AppRoutingModule { }
