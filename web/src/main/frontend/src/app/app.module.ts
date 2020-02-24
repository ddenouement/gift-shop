import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { Ng5SliderModule } from 'ng5-slider';

/* Routing */
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';

/* Angular Material */
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './angular-material.module';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

/* FormsModule */
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

/* Angular Flex Layout */
import { FlexLayoutModule } from '@angular/flex-layout';

/* Components */
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';

import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { HomeComponent } from './components/home/home.component';
import { MatCardModule } from '@angular/material/card';
import { SideNavigationComponent } from './components/side-navigation/side-navigation.component';
import { HeaderComponent } from './components/header/header.component';
import { SidenavService } from './_services/sidenav.service';
import { ProductEditComponent } from './components/product-edit/product-edit.component';
import { ProductViewComponent } from './components/product-view/product-view.component';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { MatCheckboxModule } from '@angular/material';
import { CardComponent } from './components/product-card/card.component';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';
import { OrderCreateComponent } from './components/order-create/order-create.component';

@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    RegisterComponent,
    RegisterComponent,
    LogInComponent,
    HomeComponent,
    SideNavigationComponent,
    HeaderComponent,
    ProductEditComponent,
    ProductViewComponent,
    SpinnerComponent,
    CardComponent,
    ShoppingCartComponent,
    OrderCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule,
    FlexLayoutModule,
    HttpClientModule,
    MatCardModule,
    MatCheckboxModule,
    NgMultiSelectDropDownModule,
    Ng5SliderModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    SidenavService  ,
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class AppModule { }
