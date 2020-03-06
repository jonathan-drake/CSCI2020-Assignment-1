import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CircleWithTriangle extends Application {

    Line line1 = new Line();
    Line line2 = new Line();
    Line line3 = new Line();

    Label angle1 = new Label();
    Label angle2 = new Label();
    Label angle3 = new Label();

    // Creates the points to be dragged
    private Circle createPoint(double x, double y) {
        Circle point = new Circle(8, Color.RED);
        point.setStroke(Color.BLACK);
        point.setCenterX(x);
        point.setCenterY(y);

        return point;
    }

    // Draws the points between the lines and the angles
    private void drawLines(Circle[] points) {
        line1.setStartX(points[0].getCenterX());
        line1.setStartY(points[0].getCenterY());

        line1.setEndX(points[1].getCenterX());
        line1.setEndY(points[1].getCenterY());

        line2.setStartX(points[1].getCenterX());
        line2.setStartY(points[1].getCenterY());

        line2.setEndX(points[2].getCenterX());
        line2.setEndY(points[2].getCenterY());

        line3.setStartX(points[2].getCenterX());
        line3.setStartY(points[2].getCenterY());

        line3.setEndX(points[0].getCenterX());
        line3.setEndY(points[0].getCenterY());

        // Calculates the side lengths between the points
        double side01 = Math.sqrt(Math.pow(points[1].getCenterX() - points[0].getCenterX(), 2) + Math.pow(points[1].getCenterY() - points[0].getCenterY(), 2));
        double side12 = Math.sqrt(Math.pow(points[2].getCenterX() - points[1].getCenterX(), 2) + Math.pow(points[2].getCenterY() - points[1].getCenterY(), 2));
        double side20 = Math.sqrt(Math.pow(points[0].getCenterX() - points[2].getCenterX(), 2) + Math.pow(points[0].getCenterY() - points[2].getCenterY(), 2));

        // Calculates the angles between the points
        angle1.setText("" + Math.round(Math.toDegrees(Math.acos((Math.pow(side12, 2) - Math.pow(side01, 2) - Math.pow(side20, 2)) / (-2 * side01 * side20)))));
        angle2.setText("" + Math.round(Math.toDegrees(Math.acos((Math.pow(side20, 2) - Math.pow(side01, 2) - Math.pow(side12, 2)) / (-2 * side01 * side12)))));
        angle3.setText("" + Math.round(Math.toDegrees(Math.acos((Math.pow(side01, 2) - Math.pow(side12, 2) - Math.pow(side20, 2)) / (-2 * side12 * side20)))));

        angle1.setTranslateX(points[0].getCenterX() + 10);
        angle1.setTranslateY(points[0].getCenterY());

        angle2.setTranslateX(points[1].getCenterX() + 10);
        angle2.setTranslateY(points[1].getCenterY());

        angle3.setTranslateX(points[2].getCenterX() + 10);
        angle3.setTranslateY(points[2].getCenterY());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();

        // Creates the main circle
        Circle circle = new Circle(200, 200, 100, Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        root.getChildren().addAll(circle, line1, line2, line3, angle1, angle2, angle3);

        Circle[] points = new Circle[3];
        for(int i=0; i<3; ++i) {
            points[i] = createPoint(100 * Math.cos(30 + i * 42) + 200, 100 * Math.sin(30 + i * 42) + 200);
            root.getChildren().add(points[i]);
        }
        // Updates the lines
        drawLines(points);

        // Sets the mouse event for point 1
        points[0].setOnMouseDragged(mouseEvent -> {
            // Updates the lines and angles
            drawLines(points);

            // Allows the point to be dragged
            Point2D redCenter = new Point2D(200, 200);
            Point2D mouse = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            Point2D centerToMouse = mouse.subtract(redCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(100);
            Point2D newPoint = centerToNewPoint.add(redCenter);
            points[0].setCenterX(newPoint.getX());
            points[0].setCenterY(newPoint.getY());
        });

        // Sets the mouse event for point 2
        points[1].setOnMouseDragged(mouseEvent -> {
            // Updates the lines and angles
            drawLines(points);

            // Allows the point to be dragged
            Point2D redCenter = new Point2D(200, 200);
            Point2D mouse = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            Point2D centerToMouse = mouse.subtract(redCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(100);
            Point2D newPoint = centerToNewPoint.add(redCenter);
            points[1].setCenterX(newPoint.getX());
            points[1].setCenterY(newPoint.getY());
        });

        // Sets the mouse event for point 2
        points[2].setOnMouseDragged(mouseEvent -> {
            // Updates the lines and angles
            drawLines(points);

            // Allows the point to be dragged
            Point2D redCenter = new Point2D(200, 200);
            Point2D mouse = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            Point2D centerToMouse = mouse.subtract(redCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(100);
            Point2D newPoint = centerToNewPoint.add(redCenter);
            points[2].setCenterX(newPoint.getX());
            points[2].setCenterY(newPoint.getY());
        });

        primaryStage.setTitle("Dragging Points on a Circle");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}