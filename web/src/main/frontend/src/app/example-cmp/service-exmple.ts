import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

import {map} from "rxjs/operators";
// @ts-ignore
import { Response} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class Exampleservice {
  constructor(private http: HttpClient) {
  }

  private nameURL = 'http://localhost:9990/example'; //must correspond to pth in Spring Controller

  getAuthorName(){//: Observable<string> {
    //    // return this.http.get<string>(this.nameURL).pipe(map((response: Response) => response));

    return this.http.get(this.nameURL,{ responseType: 'text' });
  }
}
