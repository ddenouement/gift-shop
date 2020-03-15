import { Component, OnInit } from '@angular/core';
import {SidenavService} from "../../_services/sidenav.service";
import {animateText, onSideNavChange} from "../../_helpers/animations";
import {UserService} from "../../_services";



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
  constructor(private userService : UserService , private _sidenavService: SidenavService) { }

  ngOnInit() {
    this.userService.getRole().subscribe(data=>{
        if(data['role']=='USER'){
          SidenavService.pages=[];
          SidenavService.pages.push({name: 'Cart', routerLink:'/cart', icon: 'shopping-cart',  alt: "Cart" });
          SidenavService.pages.push({name: 'Wishlist', routerLink:'/wishlist', icon: 'star', alt: "My Wishlist" });
        }
        if(data['role']=='ADMIN'){
          SidenavService.pages = [];
          SidenavService.pages.push({name: 'All Orders', routerLink:'/', icon: 'briefcase', alt: "All Orders" });

        }
      },
      error =>{
        //means not registered
      })

  }
  public getPages(){
    return SidenavService.pages;
  }

}
