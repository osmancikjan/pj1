/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Dro0076
 */
public class Chess extends Application {
    private Model model;
    private View view;
    private Controller controller;

    public Chess() {
        model = new Model();
    }
    
    
    @Override
    public void start(Stage primaryStage) {
       AnchorPane basePane = new AnchorPane();
        Button btnStart = new Button();
        btnStart.setText("Start game");
        btnStart.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (controller.isRunning()) {
                    //action.stop(); 
                    controller.stop();
                    btnStart.setText("Start game");
                } else {
                    model.initGame();
                    controller.start();
                    btnStart.setText("Stop game");
                }
            }
        });
        
        basePane.getChildren().add(btnStart);
        AnchorPane.setTopAnchor(btnStart, 0.0);
        AnchorPane.setLeftAnchor(btnStart, 0.0);
        AnchorPane.setRightAnchor(btnStart, 0.0);

        Pane root = new Pane();
        Canvas canvas = new Canvas(View.width, View.height);
        root.getChildren().add(canvas);
        canvas.scaleXProperty().bind(root.widthProperty().divide(View.width));
        canvas.scaleYProperty().bind(root.heightProperty().multiply(1.0 / View.height));
        canvas.translateXProperty().bind(root.widthProperty().subtract(View.width).divide(2));
        canvas.translateYProperty().bind(root.heightProperty().subtract(View.height).divide(2));

        view = new View(canvas.getGraphicsContext2D(), model);
        controller = new Controller(view, model);
        
        basePane.getChildren().add(root);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 30.0);

        Scene scene = new Scene(basePane, 800, 800);
        
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
