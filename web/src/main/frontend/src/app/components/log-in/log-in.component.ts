import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import {AuthenticationService, UserService} from '../../_services';
import {SidenavService} from "../../_services/sidenav.service";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error: boolean;

  constructor(
    private userService:UserService,
    private sideNav: SidenavService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    // redirect to home if already logged in
     this.authenticationService.isLogged().subscribe(data=>{
        this.router.navigate(['/']);
      },
       error=>{

       });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.error = false;
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/';
  }

  get f() { return this.loginForm.controls; }
  close(){
    this.router.navigate(['/']);
  }
  onSubmit() {
    this.error= false;
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.email.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);

          this.userService.getRole().subscribe(data=>{
              if(data['role']=='USER'){
                SidenavService.pages=[];
                SidenavService.pages.push({name: 'Wishlist', routerLink:'/wishlist', icon: 'star', alt: "My Wishlist" });
              }
              if(data['role']=='ADMIN'){
                SidenavService.pages = [];
                SidenavService.pages.push({name: 'All Orders', routerLink:'/orders', icon: 'star', alt: "All Orders" });

              }
            },
            error =>{
              //means not registered
            })

        },
        error => {
          this.loading = false;
          this.error = true;
        });
  }

}
