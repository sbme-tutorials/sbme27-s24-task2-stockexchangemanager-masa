package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class CandlestickGraph extends Controller{
    int canvasHeight = 600;
    int canvasWidth = 1300;

    int topBottomPadding = 30;
    int sidesPadding = 30;

    int xAxisGap;

    String symbol;
    String directoryPath;


    public CandlestickGraph(){}
    public CandlestickGraph(String symbol, String directoryPath, int canvasWidth, int canvasHeight) {
        this.symbol = symbol;
        this.directoryPath = directoryPath;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public VBox graph() {
        ArrayList<Candlestick> candlesticks = new ArrayList<>();

        HashMap<String, double[]> candlestickData = populateHashMapFromCSV(symbol, directoryPath);
        xAxisGap = (canvasWidth - (2 * sidesPadding)) / candlestickData.size();

        // Printing the candlestickData
        for (HashMap.Entry<String, double[]> entry : candlestickData.entrySet()) {
            double[] values = entry.getValue();
            System.out.println(entry.getKey() + " -> " + java.util.Arrays.toString(values));
        }

        // Create Candlestick objects and add them to the ArrayList
        int i = xAxisGap;
        for (double[] data : candlestickData.values()) {
            Candlestick candlestick = new Candlestick(i, data[0], data[1], data[2], data[3]);
            candlesticks.add(candlestick);
            i += xAxisGap;
        }

        // Create a Group to hold all the Candlestick nodes
        Group root = new Group();

        // Draw each Candlestick object and add it to the Group
        addAxes(root, candlesticks.size(), candlestickData.keySet());
//        Button button = new Button(1);
//        button.set

        for (Candlestick candlestick : candlesticks) {
            root.getChildren().add(candlestick.show());
        }

        // Create the scene with the root Group
//        return root;

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            try {
//                Normal_User_Scene.getNormalScene();
                display("NormalUserScene.fxml");

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox vbox = new VBox(backButton, root);

        // Create the scene with the VBox containing the button and the root Group
        return vbox;
    }

    public HashMap<String, double[]> populateHashMapFromCSV( String symbol, String directoryPath) {
        String filePath = directoryPath + "\\" + symbol + ".csv";
        HashMap<String, double[]> dataMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                int columnsLength = columns.length;

                // Assuming first column is the key and the last 4 columns are the values
                String key = columns[0];
                double[] values = new double[4];
                values[0] = Double.parseDouble(columns[columnsLength - 4]);
                values[1] = Double.parseDouble(columns[columnsLength - 3]);
                values[2] = Double.parseDouble(columns[columnsLength - 2]);
                values[3] = Double.parseDouble(columns[columnsLength - 1]);

                dataMap.put(key, values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataMap;
    }

    private void addAxes(Group root, int candlesticksNumber, Set<String> dates) {

        // Draw X-axis
        Line xAxis = new Line(sidesPadding, canvasHeight - topBottomPadding, canvasWidth - sidesPadding,
                canvasHeight - topBottomPadding);
        root.getChildren().add(xAxis);

        // Draw Y-axis
        Line yAxis = new Line(sidesPadding, topBottomPadding, sidesPadding, canvasHeight - topBottomPadding);
        root.getChildren().add(yAxis);

        // Add Y-axis labels (price)
        for (int i = 0; i <= 10; i++) {
            int y = canvasHeight - i * (canvasHeight / 10);
            Text text = new Text(5, y - 5, String.valueOf(i * 50));
            root.getChildren().add(text);
        }

        // Add X-axis labels (dates)
        int i = 0;
        int k = xAxisGap;
        for (String date : dates) {
            Text text = new Text(k-9, canvasHeight - 7, changeDateFormat(date));
            root.getChildren().add(text);
            i++;
            k += xAxisGap;
        }
    }

    public static String changeDateFormat(String inputDate) {
        // Define the input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM dd");

        String outputDate = "";
        try {
            // Parse the input date
            Date date = inputFormat.parse(inputDate);
            // Format the date to the desired output format
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }

    // private void addGrid(Group root, int canvasWidth, int canvasHeight) {
    // // Draw horizontal grid lines
    // for (int i = 0; i <= 10; i++) {
    // int y = i * (canvasHeight / 10);
    // Line line = new Line(0, y, canvasWidth, y);
    // line.setStroke(Color.LIGHTGRAY);
    // root.getChildren().add(line);
    // }

    // // Draw vertical grid lines
    // for (int i = 0; i <= 10; i++) {
    // int x = i * (canvasWidth / 10);
    // Line line = new Line(x, 0, x, canvasHeight);
    // line.setStroke(Color.LIGHTGRAY);
    // root.getChildren().add(line);
    // }
    // }

}
