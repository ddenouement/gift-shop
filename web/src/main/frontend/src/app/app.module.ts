import {BrowserModule} from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import { HttpClientModule } from '@angular/common/http';

import {
  MatButtonModule,
  MatCardModule,
  MatGridListModule,
  MatInputModule,
  MatMenuModule,
  MatToolbarModule
} from "@angular/material";
import { ExampleCmpComponent } from './example-cmp/example-cmp.component';
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    AppComponent,
    ExampleCmpComponent
  ],
  imports: [
    HttpClientModule,
    RouterModule,
    BrowserModule
  ],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
