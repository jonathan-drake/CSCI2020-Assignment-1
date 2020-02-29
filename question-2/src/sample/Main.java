package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);

        pane.add(new Label("Investment Amount:"),0,0);
        TextField InvestmentAmount = new TextField();
        InvestmentAmount.setPromptText("Investment Amount");
        pane.add(InvestmentAmount, 1,0);

        pane.add(new Label("Years:"), 0,1);
        TextField Years = new TextField();
        Years.setPromptText("# of Years");
        pane.add(Years, 1,1);

        pane.add(new Label("Annual Interest Rate:"), 0 , 2);
        TextField AnnualInterestRate = new TextField();
        AnnualInterestRate.setPromptText("Annual Interest Rate");
        pane.add(AnnualInterestRate, 1 ,2);

        pane.add(new Label("Future Value:"), 0 , 3);
        TextField FutureValue = new TextField();
        FutureValue.setPromptText("Future Value");
        FutureValue.setEditable(false);
        pane.add(FutureValue, 1 ,3);

        Button btAdd = new Button("Calculate");
        btAdd.setDefaultButton(true);
        btAdd.setOnAction(actionEvent -> {
            float investmentValue = Float.valueOf(InvestmentAmount.getText());
            float annualInterestRate = Float.valueOf(AnnualInterestRate.getText());
            int years = Integer.valueOf(Years.getText());
            double futureValue = investmentValue * Math.pow(1 + ((annualInterestRate / 100) / 12), years * 12);

            DecimalFormat df = new DecimalFormat("#.00");
            FutureValue.setText(df.format(futureValue));
        });
        pane.add(btAdd, 1,4);
        GridPane.setHalignment(btAdd, HPos.LEFT);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Investment Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
