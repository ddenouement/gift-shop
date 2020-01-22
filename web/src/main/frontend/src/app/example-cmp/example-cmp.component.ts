import { Component, OnInit } from '@angular/core';
import {Exampleservice} from "./service-exmple";

@Component({
  selector: 'app-example-cmp',
  templateUrl: './example-cmp.component.html',
  styleUrls: ['./example-cmp.component.scss']
})
export class ExampleCmpComponent implements OnInit {
  name: string;

  constructor(private exampleService:Exampleservice) { }

  ngOnInit() {
    this.exampleService.getAuthorName().subscribe(res => {

      this.name = res;
    });

  }

}
