import {Injectable} from '@angular/core';
import {Product} from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  products: Product[] = [{
    id: 1,
    name: 'IPhone 12',
    price: 2400000,
    description: 'New'
  }, {
    id: 2,
    name: 'IPhone 11',
    price: 1560000,
    description: 'Like new'
  }, {
    id: 3,
    name: 'IPhone X',
    price: 968000,
    description: '97%'
  }, {
    id: 4,
    name: 'IPhone 8',
    price: 7540000,
    description: '98%'
  }, {
    id: 5,
    name: 'IPhone 11 Pro',
    price: 1895000,
    description: 'Like new'
  }];

  constructor() {
  }

  findAll(): Product[] {
    return this.products;
  }

  findById(id: number): Product {
    return this.products.find(item => item.id === id);
  }

  save(product: Product) {
    this.products.push({...product, id: this.products.length + 1});
  }

  update(id: number, value) {
    // for (let i = 0; i < this.products.length; i++) {
    //   if (id === this.products[i].id) {
    //     this.products[i] = value;
    //     break;
    //   }
    // }
    this.products = this.products.map((item) => {
      return item.id === id ? value : item;
    });
  }

  delete(id: number) {
    this.products = this.products.filter((item) => item.id !== id);
  }
}
