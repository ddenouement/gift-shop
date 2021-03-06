import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../_models";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../_services";
import {MatSidenav} from "@angular/material/sidenav";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() sidenav: MatSidenav;

    currentUser: User;
    isLogged: boolean;
    url:string;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.isLogged = false;
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
   this.url =  this.router.url;
     // this.authenticationService.isLogged().subscribe(x=>this.isLogged = true);
   //   alert("check logged");
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  ngOnInit(): void {
  }

}
