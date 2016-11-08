/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Dro0076
 */
public class Chess extends Application {
    private Model model;
    private View view;
    private Controller controller;
    private ArrayList<ModelObject> objects = new ArrayList<>();
   

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
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                     int xosa = (int)event.getSceneX();
                     int yosa = (int)event.getSceneY();
                     xosa = xosa/100;
                     yosa = yosa/100;
                     
                     Point direction = new Point();
                     Point pozice = new Point(xosa,yosa);
                     
                     for (ModelObject object : model.getObjects()) {
                     if( object.position.equals(pozice))
                     {               
                         if (object instanceof Pawn)
                         {
                            if (((Pawn) object).getColor() == Pawn.Color.White){
                                direction.x = 0;
                                direction.y = -1;  
                                }else{
                                direction.x = 0;
                                direction.y = 1; 
                                }       
                         model.Choosen(pozice);   
                         model.getObjectAt1(pozice,direction);
                         System.out.println("Kliknul jsi na pincla");
                         }
                     }
                     }             
                     model.Choosen(pozice);
                     view.update();               
            }
        });
        
        
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