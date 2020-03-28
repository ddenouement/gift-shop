import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

import { User } from '../_models';
import {SidenavService} from "./sidenav.service";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

  public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }
    public getRole() : string{
      return JSON.parse(localStorage.getItem('role'));
    }

    login(email: string, password: string) {
        return this.http.post<any>(`${environment.apiUrl}/user/login`, { email, password })
            .pipe(map(user => {
                // login successful if there's a jwt token in the response
                if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    localStorage.setItem('role', JSON.stringify(user.role));
                    this.currentUserSubject.next(user);
                }

                return user;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
        localStorage.removeItem('role');

        this.http.get<any>(`${environment.apiUrl}/user/logout`);
      document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      SidenavService.pages = [];
      SidenavService.pages.push({
        name: 'Cart',
        routerLink: '/cart',
        icon: 'shopping-cart',
        alt: "Cart"
      });
    }
}
