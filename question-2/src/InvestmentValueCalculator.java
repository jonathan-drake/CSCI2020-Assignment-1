import javafx.application.Application;
import javafx.scene.layout.GridPane;
import java.text.DecimalFormat;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InvestmentValueCalculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);

        // creates Investment Amount text field
        pane.add(new Label("Investment Amount:"),0,0);
        TextField investmentAmount = new TextField();
        investmentAmount.setPromptText("Investment Amount");
        pane.add(investmentAmount, 1,0);

        // creates Years text field
        pane.add(new Label("Years:"), 0,1);
        TextField years = new TextField();
        years.setPromptText("# of Years");
        pane.add(years, 1,1);

        // creates Annual Interest Rate text field
        pane.add(new Label("Annual Interest Rate:"), 0 , 2);
        TextField annualInterestRate = new TextField();
        annualInterestRate.setPromptText("Annual Interest Rate");
        pane.add(annualInterestRate, 1 ,2);

        // creates Future Value text field
        pane.add(new Label("Future Value:"), 0 , 3);
        TextField futureValue = new TextField();
        futureValue.setPromptText("Future Value");
        futureValue.setStyle("-fx-control-inner-background: lightgrey;");
        futureValue.setFocusTraversable(false);
        futureValue.setMouseTransparent(true);
        futureValue.setEditable(false);
        pane.add(futureValue, 1 ,3);

        // creates Button to calculate values
        Button btAdd = new Button("Calculate");
        btAdd.setDefaultButton(true);
        btAdd.setOnAction(actionEvent -> {
            float investmentValue = Float.valueOf(investmentAmount.getText());
            float monthlyInterestRate = Float.valueOf(annualInterestRate.getText()) / 100 / 12;
            int months = Integer.valueOf(years.getText()) * 12;
            double value = investmentValue * Math.pow(1 + monthlyInterestRate, months);

            DecimalFormat df = new DecimalFormat("#.00");

            futureValue.setText(df.format(value));
        });

        pane.add(btAdd, 1,4);
        GridPane.setHalignment(btAdd, HPos.LEFT);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Investment Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}