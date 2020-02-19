import DateTimeFormat = Intl.DateTimeFormat;
import {IHash} from "./IHash";

export  class OrderDTO {

  public orderId: number;
  public userId: number;
  public  orderItems:{} [] ;
  public orderState:number;
  public address : string;
  public cashPayment: boolean;
  public postDelivery: boolean;
  public   orderDate: Date;
  public totalSum: number;
}
