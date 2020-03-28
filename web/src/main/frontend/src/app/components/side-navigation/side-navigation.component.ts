import { Component, OnInit } from '@angular/core';
import {SidenavService} from "../../_services/sidenav.service";
import {animateText, onSideNavChange} from "../../_helpers/animations";
import {AuthenticationService, UserService} from "../../_services";



interface Page {
  routerLink: string;
  name: string;
  icon: string;
  alt: string;
}

@Component({
  selector: 'app-side-navigation',
  templateUrl: './side-navigation.component.html',
  styleUrls: ['./side-navigation.component.css'],
animations: [onSideNavChange, animateText]
})
export class SideNavigationComponent implements OnInit {

  public sideNavState: boolean = false;
  public linkText: boolean = false;
  role: string;

  public pages: Page[] = [
    {name: 'Cart', routerLink:'/cart', icon: 'shopping-cart',  alt: "Cart" }
  ]
  constructor(private auth : AuthenticationService , private _sidenavService: SidenavService) { }

  ngOnInit() {
    this.auth.currentUser.subscribe(
      data => {

         let r  = this.auth.getRole();

        if (r == 'USER') {
          SidenavService.pages = [];
          SidenavService.pages.push({
            name: 'Cart',
            routerLink: '/cart',
            icon: 'shopping-cart',
            alt: "Cart"
          });
          SidenavService.pages.push({
            name: 'Wishlist',
            routerLink: '/wishlist',
            icon: 'star',
            alt: "My Wishlist"
          });
        }
        if (r == 'ADMIN') {
          SidenavService.pages = [];
          SidenavService.pages.push({
            name: 'All Orders',
            routerLink: '/orders',
            icon: 'star',
            alt: "All Orders"
          });

        }
      }
       ,
      error => {
        SidenavService.pages = [];
        SidenavService.pages.push({
          name: 'Cart',
          routerLink: '/cart',
          icon: 'shopping-cart',
          alt: "Cart"
        });
      }
  ); }


  public getPages(){
    return SidenavService.pages;
  }

}
