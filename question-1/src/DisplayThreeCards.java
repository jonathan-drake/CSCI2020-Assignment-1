import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;

public class DisplayThreeCards extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        pane.setTop(getHBox());

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Display Three Cards");
        primaryStage.show();
    }
    private HBox getHBox(){
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(15,15,15,15));
        Random randNum = new Random();
        for (int i = 0; i < 3; i++){
            int n = randNum.nextInt(54) + 1;
            hBox.getChildren().add(new ImageView(new Image("cards/" + n + ".png")));
        }

        return hBox;
    }
}
