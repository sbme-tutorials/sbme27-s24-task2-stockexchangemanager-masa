package App;

import java.util.ArrayList;
    public class Stock {
        private ArrayList <Integer> price_history= new ArrayList<>();
        private int available_stocks;
        private float price;
        public Company total_price = new Company();
        private float Calc_value = total_price.req_value();
        public float  get_price (){
            return price;
        }
        public float  set_price (float price){
            this.price=price;
            return price;
        }
        public void  display_price_history (){
            System.out.println(price_history);
        }
        public float calc_value (){
            if (available_stocks > get_Available_stocks() ){
                System.out.println(" the number of stocks is larger than the offered");
                return 1;
            }else {
                return Calc_value;
            }

        }
        public int get_Available_stocks (){
            return available_stocks ;
        }

    }
