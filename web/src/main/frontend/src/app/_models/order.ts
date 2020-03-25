export class Order {

  public orderId: number;
  public userId: number;
  public orderItems:{} [] ;
  public orderState:number;
  public address : string;
  public cashPayment: boolean;
  public postDelivery: boolean;
  public orderDate: Date;
  public totalSum: number;
  get  getPaymentString(): string{
   if( this.cashPayment) return "CASH";
   else return "POST DELIVERY";
  }
}
