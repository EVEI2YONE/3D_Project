package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        Button rotate = new Button("rotate");
        rotate.setOnAction(e -> {
            controller.rotate(1, 0, 0);
        });
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./sample.fxml"));
        Parent canvas = loader.load();
        controller = loader.getController();
        controller.init();

        root.getChildren().addAll(rotate, canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
