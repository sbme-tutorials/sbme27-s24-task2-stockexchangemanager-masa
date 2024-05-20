package App;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LineGraph implements Initializable {
    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private Button plot;
    Stock mystock ;
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TimeZone.setDefault(TimeZone.getTimeZone("EET"));
        SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy"+"\n "+" hh:mm:ss");
        Date date = new Date();
        String current_date = date_format.format(date);
        System.out.println(current_date);
        XYChart.Series series = new XYChart.Series();
        Stock mystock=new Stock();
        series.getData().add(new XYChart.Data(current_date,mystock.get_price()));
        series.getData().add(new XYChart.Data(current_date,35));

        chart.getData().addAll(series);

    }



    public LineGraph(Stock stock ){
        this.mystock=stock;
    }
}
