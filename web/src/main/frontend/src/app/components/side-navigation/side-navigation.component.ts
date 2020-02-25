import { Component, OnInit } from '@angular/core';
import {SidenavService} from "../../_services/sidenav.service";
import {animateText, onSideNavChange} from "../../_helpers/animations";



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

  public pages: Page[] = [
    {name: 'Cart', routerLink:'/cart', icon: 'shopping-cart',  alt: "Cart" },
    {name: 'Wishlist', routerLink:'some-link', icon: 'star', alt: "My Wishlist" }
  ]

  constructor(private _sidenavService: SidenavService) { }

  ngOnInit() {
  }

  onSinenavToggle() {
    this.sideNavState = !this.sideNavState;

    setTimeout(() => {
      this.linkText = this.sideNavState;
    }, 200)
    this._sidenavService.sideNavState$.next(this.sideNavState)
  }

}
