import javafx.scene.canvas.GraphicsContext;
import javafx.application.Application;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class Histogram extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox Main = new VBox(2);

        // creates canvas for the graph
        Canvas barGraph = new Canvas(491,211);
        GraphicsContext barGc = barGraph.getGraphicsContext2D();
        barGc.strokeLine(15, 210, 483, 210);

        // adds all three HBox's to main VBox
        Main.getChildren().addAll(barGraph, letterSpacing(), fileLocation(barGc));
        var scene = new Scene(Main);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Histogram");
        primaryStage.show();
    }

    // Function: creates the x-axis label for histogram
    private HBox letterSpacing() {
        HBox graphLabel = new HBox(10);
        graphLabel.setPadding(new Insets(0, 15, 0, 15));

        // adds alphabet labels from A-Z in HBox
        for (int i = 0; i < 26; i++) {
            graphLabel.getChildren().add(new Label(Character.toString(i + 65)));
        }
        return graphLabel;
    }

    // Function: adds textField and button to search for a file
    private HBox fileLocation(GraphicsContext barGc) {
        HBox fileAccess = new HBox(15);
        fileAccess.setPadding(new Insets(5, 1, 3, 15));

        // creates "filename" TextField
        TextField filename = new TextField();
        filename.setPromptText("Filename");
        fileAccess.getChildren().add(new Label("Filename:"));
        fileAccess.getChildren().add(filename);

        // creates "view" button
        Button viewBtn = new Button("View");
        viewBtn.setDefaultButton(true);

        // on button press, activates the getHistogram function
        viewBtn.setOnAction(actionEvent -> {

            //getHistogram(getMap(filename.getText()));
            getHistogram(getMap(filename.getText()), barGc);
        });

        // adds button to HBox
        fileAccess.getChildren().add(viewBtn);
        return fileAccess;
    }

    // Function: creates a HashMap to store every character and its amount found
    private HashMap<Character, Integer> getMap(String filename) {
        File file = new File(filename);

        // if file is found
        if (file.exists()) {

            // creates HashMap
            HashMap<Character, Integer> letters = new HashMap<>();
            Scanner scanFile = null;
            try {
                scanFile = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (scanFile.hasNext()) {

                // adds every word found into String words
                String words = scanFile.next();

                // converts each letter to lowerCase
                words = words.toLowerCase();

                // adds 1 count for every time a character is found
                for (int i = 0; i < words.length(); i++) {
                    char letter = words.charAt(i);
                    if (letters.get(letter) != null){
                        letters.put(letter, letters.get(letter) + 1);
                    } else {

                        //adds any new characters
                        letters.put(letter, 1);
                    }
                }
            }
            scanFile.close();
            return letters;

          // displays if file DNE
        } else {
            System.out.println("No file found at " + filename);
            return null;
        }
    }

    // Function: finds character with the largest count
    private int findMax(HashMap<Character, Integer> letters) {
        char max = 'a';

        // loops through each character and compares the count
        for (int i = 0; i < 26; i++){
            for (char letter : Character.toChars(i + 97)) {

                // makes max equal to the character with the largest count
                if (letters.get(max) < letters.get(letter)) {
                    max = letter;
                }
            }
        }
        return letters.get(max);
    }

    // Function: creates histogram
    private void getHistogram(HashMap<Character, Integer> letters, GraphicsContext barGc){
        // clears old Histogram
        barGc.clearRect(0, 0, 491,211);
        barGc.strokeLine(15, 210, 483, 210);

        int max = findMax(letters);

        // adds a bar to the histogram for each letter
        for (int i = 0; i < 26; i++) {

            // calculates height using count from all lowerCase letters
            double height = 200 * ((double)(letters.get(Character.toChars(i + 97)[0])) / max);

            barGc.setFill(Color.WHITE);
            barGc.setStroke(Color.BLACK);
            barGc.strokeRect(16 + (18 * i), 10 + (200 - height), 7, height);
        }
    }
}