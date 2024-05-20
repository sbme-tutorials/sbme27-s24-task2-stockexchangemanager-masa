package App;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Candlestick {
    double xPos; // Supposed to be the Date
    double canvasHeight = 500;
    double canvasWidth = 500;

    double maxPrice;
    double minPrice;
    double OpeningPrice;
    double ClosingPrice;

    double modifiedMinPrice;
    double modifiedMaxPrice;
    double modifiedClosingPrice;
    double modifiedOpeningPrice;

    double bodyBottomYPos;
    double bodyTopYPos;
    double rectangleHeight;
    static int rectangleWidth = 10;

    public Candlestick(double xPos, double minPrice, double maxPrice, double OpeningPrice, double ClosingPrice) {
        this.xPos = xPos;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.OpeningPrice = OpeningPrice;
        this.ClosingPrice = ClosingPrice;

        modifiedMinPrice = canvasHeight - minPrice;
        modifiedMaxPrice = canvasHeight - maxPrice;
        modifiedClosingPrice = canvasHeight - ClosingPrice;
        modifiedOpeningPrice = canvasHeight - OpeningPrice;

        bodyBottomYPos = (ClosingPrice < OpeningPrice) ? modifiedClosingPrice : modifiedOpeningPrice;
        bodyTopYPos = (ClosingPrice < OpeningPrice) ? modifiedOpeningPrice : modifiedClosingPrice;
        rectangleHeight = Math.abs(ClosingPrice - OpeningPrice);
    }

    public Group show() {
        Line line = new Line(
                xPos,
                modifiedMinPrice,
                xPos,
                bodyBottomYPos);
        Rectangle rectangle = new Rectangle(
                xPos - (rectangleWidth / 2),
                bodyTopYPos,
                rectangleWidth,
                rectangleHeight);
        Line line2 = new Line(
                xPos,
                bodyTopYPos,
                xPos,
                modifiedMaxPrice);

        if (ClosingPrice < OpeningPrice) {
            rectangle.setFill(Color.RED);
        } else {
            rectangle.setFill(Color.GREEN);
        }

        Group root = new Group();
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(line2);

        return root;
    }

}
