package App;
import java.util.HashMap;
public class Company {
    private static HashMap<String,Stock> company_map = new HashMap<>();
    private int stock_price;
    int size;
    private float value = size * stock_price;
    public static void set_company(String company_name,int price,int available_stocks){
        company_map.put(company_name,new Stock());
    }
    public float  req_value(){
        //  return value;
        return value;
    }
    public HashMap<String,Stock> get_company_map() {
        return company_map;
    }
    public int get_price() {
        // getting stock price
        return stock_price;
    }
    public void set_price(int price){
        //setting stock price
        this.stock_price = price;
    }


}
