import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../_models/product';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input() product: Product;

  ngOnInit() {}

  addProductToCart() {}

  addProductToWishlist() {}
}
