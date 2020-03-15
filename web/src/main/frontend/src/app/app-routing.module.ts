import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { ProductEditComponent} from "./components/product-edit/product-edit.component";
import {ProductViewComponent} from "./components/product-view/product-view.component";
import {ShoppingCartComponent} from "./components/shopping-cart/shopping-cart.component";
import {OrderCreateComponent} from "./components/order-create/order-create.component";
import {WishlistOtherComponent} from "./components/wishlist-other/wishlist-other.component";
import {WishlistMyComponent} from "./components/wishlist-my/wishlist-my.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LogInComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'product/edit/:id', component: ProductEditComponent },
  {path: 'product/:id', component: ProductViewComponent},
  {path: 'cart', component: ShoppingCartComponent},
  {path: 'order', component: OrderCreateComponent},
  {path: 'wishlist', component: WishlistMyComponent},
  {path: 'wishlist/:id', component: WishlistOtherComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})

export class AppRoutingModule { }
