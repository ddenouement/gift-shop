export class User {
  id!: number;
  email!: string;
  password!: string;
  firstname!: string;
  surname!: string;
  patronymic?: string | null;
  // tslint:disable-next-line:variable-name
  birth_date!: Date;
  // tslint:disable-next-line:variable-name
  phone_number?: string | null;
  token!: string;
}
