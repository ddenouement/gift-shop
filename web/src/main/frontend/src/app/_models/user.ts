export class User {
  id!: number;
  email!: string;
  password!: string;
  name!: string;
  surname!: string;
  patronymic?: string | null;
  // tslint:disable-next-line:variable-name
  birth_date!: Date;
  // tslint:disable-next-line:variable-name
  phoneNumber?: string | null;
  token!: string;
}
