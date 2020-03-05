import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Random;

public class DisplayThreeCards extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // adds cards to scene
        Scene scene = new Scene(getCards());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Display Three Cards");
        primaryStage.show();
    }

    // Function: finds three random cards
    private HBox getCards(){
        HBox cards = new HBox(15);
        cards.setPadding(new Insets(15,15,15,15));
        Random randNum = new Random();

        // makes an array of 3 random numbers
        int[] n = new int[3];
        for (int i = 0; i < 3; i++){
            n[i] = randNum.nextInt(54) + 1;

            // changes any numbers in the array that are the same
            while (n[1] == n[0] || n[2] == n[0]){
                n[i] = randNum.nextInt(54) + 1;
            }

            // adds card to HBox
            cards.getChildren().add(new ImageView(new Image("cards/" + n[i] + ".png")));
        }
        return cards;
    }
}
