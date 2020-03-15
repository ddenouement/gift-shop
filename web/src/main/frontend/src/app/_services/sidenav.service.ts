import { Injectable } from '@angular/core';
import { Subject } from 'rxjs'



interface Page {
  routerLink: string;
  name: string;
  icon: string;
  alt: string;
}

@Injectable()
export   class SidenavService {

  // With this subject you can save the sidenav state and consumed later into other pages.
  public sideNavState$: Subject<boolean> = new Subject();

  public static pages: Page[] = [
    {name: 'Cart', routerLink:'/cart', icon: 'shopping-cart',  alt: "Cart" }
  ];
  constructor() { }

}
