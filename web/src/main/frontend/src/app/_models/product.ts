export class Product {
  id!: number;
  name!: string;
  description!: string;
  price!: number;
  photo?: string | null;
  is_available!: boolean;
}
