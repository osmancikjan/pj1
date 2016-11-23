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
    Point direction = new Point();
    Point pozice = new Point();
    boolean sideToMove = true;
    boolean chooseAndPlay = true;
   

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
                     
                     if(chooseAndPlay==false)
                     {     
                     boolean rightToMove = false;
                     boolean rightToMovePawn = false; 
                     int xosa = (int)event.getSceneX();
                     int yosa = (int)event.getSceneY();
                     xosa = xosa/100;
                     yosa = yosa/100;    
                     
                     direction = new Point(xosa,yosa);
                     for (ModelObject object : model.getObjects()) {
                      if( object.position.equals(pozice) && sideToMove==true)
                          {  
                         if(object instanceof Figures)  
                         {
                          if (((Figures) object).getType() == Figures.Type.Pawn )
                             {
                              if (((Figures) object).getColor() == Figures.Color.White){ 
                                  Point vpred =  new Point(model.getOccupied(pozice, direction));            
                                    if(direction.x == pozice.x && direction.y == pozice.y-1 && model.isFree(vpred)){
                                        rightToMovePawn=true;
                                        sideToMove=false;
                                      }
                                    else if((direction.x == pozice.x+1 && direction.y == pozice.y-1 
                                           ||direction.x == pozice.x-1 && direction.y == pozice.y-1) 
                                           && !model.isFree(vpred)){
                                         rightToMove = true;
                                         sideToMove=false;
                                        }
                                }                  
                             }
                        if (((Figures) object).getType() == Figures.Type.Rook )
                             {
                              if (((Figures) object).getColor() == Figures.Color.White){
                                    if((direction.x == pozice.x && direction.y >= pozice.y-1) 
                                       || (direction.x == pozice.x && direction.y <= pozice.y+1)
                                       || (direction.x <= pozice.x-1 && direction.y == pozice.y) 
                                       || (direction.x >= pozice.x+1 && direction.y == pozice.y)){
                                        rightToMove=true;
                                        sideToMove=false;
                                      }    
                                }                 
                             }
                        if (((Figures) object).getType() == Figures.Type.Bishop )
                             {
                              if (((Figures) object).getColor() == Figures.Color.White){
                                    if(((direction.x >= pozice.x+1 && direction.y >= pozice.y-1) 
                                       || (direction.x <= pozice.x-1 && direction.y >= pozice.y-1)
                                       || (direction.x <= pozice.x-1 && direction.y <= pozice.y+1) 
                                       || (direction.x >= pozice.x+1 && direction.y <= pozice.y+1))
                                       && (Math.abs(pozice.x-direction.x) == Math.abs(pozice.y-direction.y))){
                                        rightToMove=true;
                                        sideToMove=false;
                                      }    
                                   }                  
                             }
                        if (((Figures) object).getType() == Figures.Type.Knight )
                             {
                              if (((Figures) object).getColor() == Figures.Color.White){
                                    if((direction.x == pozice.x+2 && direction.y == pozice.y-1)|| (direction.x == pozice.x-2 && direction.y == pozice.y-1)
                                       || (direction.x == pozice.x-2 && direction.y == pozice.y+1) 
                                       || (direction.x == pozice.x+2 && direction.y == pozice.y+1)|| (direction.x == pozice.x+1 && direction.y == pozice.y+2)
                                       || (direction.x == pozice.x+1 && direction.y == pozice.y-2)
                                       || (direction.x == pozice.x-1 && direction.y == pozice.y-2)|| (direction.x == pozice.x-1 && direction.y == pozice.y+2)){
                                        rightToMove=true;
                                        sideToMove=false;
                                      }    
                                 }                  
                             }  
                        if (((Figures) object).getType() == Figures.Type.King )
                             {
                              if (((Figures) object).getColor() == Figures.Color.White){
                                    if(direction.x == pozice.x && direction.y == pozice.y-1 ||direction.x == pozice.x-1 && direction.y == pozice.y-1
                                       ||direction.x == pozice.x && direction.y == pozice.y+1 ||direction.x == pozice.x-1 && direction.y == pozice.y+1
                                       ||direction.x == pozice.x+1 && direction.y == pozice.y ||direction.x == pozice.x+1 && direction.y == pozice.y-1
                                       ||direction.x == pozice.x-1 && direction.y == pozice.y ||direction.x == pozice.x+1 && direction.y == pozice.y+1){
                                        rightToMove=true;
                                        sideToMove=false;
                                      }    
                                }                 
                             }
      /*--Dodelat!!*/     if (((Figures) object).getType() == Figures.Type.Queen )
                                {
                            if (((Figures) object).getColor() == Figures.Color.White){
                                if(((direction.x >= pozice.x+1 && direction.y >= pozice.y-1) 
                                  || (direction.x <= pozice.x-1 && direction.y >= pozice.y-1)
                                  || (direction.x <= pozice.x-1 && direction.y <= pozice.y+1) 
                                  || (direction.x >= pozice.x+1 && direction.y <= pozice.y+1))
                                  && (Math.abs(pozice.x-direction.x) == Math.abs(pozice.y-direction.y)))
                                   {
                                        rightToMove=true;
                                        sideToMove=false;                                      
                                   }
                                if((direction.x == pozice.x && direction.y >= pozice.y-1) 
                                   || (direction.x == pozice.x && direction.y <= pozice.y+1)
                                   || (direction.x <= pozice.x-1 && direction.y == pozice.y) 
                                   || (direction.x >= pozice.x+1 && direction.y == pozice.y)){
                                        rightToMove=true;
                                        sideToMove=false;
                                      }   
                                }
                              }
                           }
                          }
                      else if( object.position.equals(pozice) && sideToMove==false){ 
                       if(object instanceof Figures)  
                         {
                          if (((Figures) object).getType() == Figures.Type.Pawn )
                             {
                              if (((Figures) object).getColor() == Figures.Color.Black){
                                 Point vpred =  new Point(model.getOccupied(pozice, direction));            
                                    if(direction.x == pozice.x && direction.y == pozice.y+1 && model.isFree(vpred)){
                                        rightToMovePawn=true;
                                        sideToMove=true;
                                      }
                                    else if((direction.x == pozice.x+1 && direction.y == pozice.y+1 
                                           ||direction.x == pozice.x-1 && direction.y == pozice.y+1) 
                                           && !model.isFree(vpred)){
                                         rightToMove = true;
                                         sideToMove=true;
                                        }
                                }                  
                             }
                        if (((Figures) object).getType() == Figures.Type.Rook )
                             {
                              if (((Figures) object).getColor() == Figures.Color.Black){
                                    if((direction.x == pozice.x && direction.y >= pozice.y-1) 
                                       || (direction.x == pozice.x && direction.y <= pozice.y+1)
                                       || (direction.x <= pozice.x-1 && direction.y == pozice.y) 
                                       || (direction.x >= pozice.x+1 && direction.y == pozice.y)){
                                        rightToMove=true;
                                        sideToMove=true;
                                      }    
                                }                 
                             }
                        if (((Figures) object).getType() == Figures.Type.Bishop )
                             {
                              if (((Figures) object).getColor() == Figures.Color.Black){
                                    if(((direction.x >= pozice.x+1 && direction.y >= pozice.y-1) 
                                       || (direction.x <= pozice.x-1 && direction.y >= pozice.y-1)
                                       || (direction.x <= pozice.x-1 && direction.y <= pozice.y+1) 
                                       || (direction.x >= pozice.x+1 && direction.y <= pozice.y+1))
                                       && (Math.abs(pozice.x-direction.x) == Math.abs(pozice.y-direction.y))){
                                        
                                        rightToMove=true;
                                        sideToMove=true;
                                     }    
                                   }                  
                             }
                        if (((Figures) object).getType() == Figures.Type.Knight )
                             {
                              if (((Figures) object).getColor() == Figures.Color.Black){
                                    if((direction.x == pozice.x+2 && direction.y == pozice.y-1)|| (direction.x == pozice.x-2 && direction.y == pozice.y-1)|| (direction.x == pozice.x-2 && direction.y == pozice.y+1) 
                                       || (direction.x == pozice.x+2 && direction.y == pozice.y+1)|| (direction.x == pozice.x+1 && direction.y == pozice.y+2)|| (direction.x == pozice.x+1 && direction.y == pozice.y-2)
                                       || (direction.x == pozice.x-1 && direction.y == pozice.y-2)|| (direction.x == pozice.x-1 && direction.y == pozice.y+2)){
                                        rightToMove=true;
                                        sideToMove=true;
                                      }    
                                 }                  
                             }  
                        if (((Figures) object).getType() == Figures.Type.King )
                             {
                              if (((Figures) object).getColor() == Figures.Color.Black){
                                    if(direction.x == pozice.x && direction.y == pozice.y-1 ||direction.x == pozice.x-1 && direction.y == pozice.y-1
                                       ||direction.x == pozice.x && direction.y == pozice.y+1 ||direction.x == pozice.x-1 && direction.y == pozice.y+1
                                       ||direction.x == pozice.x+1 && direction.y == pozice.y ||direction.x == pozice.x+1 && direction.y == pozice.y-1
                                       ||direction.x == pozice.x-1 && direction.y == pozice.y ||direction.x == pozice.x+1 && direction.y == pozice.y+1){
                                        rightToMove=true;
                                        sideToMove=true;
                                      }    
                                }                 
                             }
                          if (((Figures) object).getType() == Figures.Type.Queen )
                                {
                            if (((Figures) object).getColor() == Figures.Color.Black){
                               if(((direction.x >= pozice.x+1 && direction.y >= pozice.y-1) 
                                  || (direction.x <= pozice.x-1 && direction.y >= pozice.y-1)
                                  || (direction.x <= pozice.x-1 && direction.y <= pozice.y+1) 
                                  || (direction.x >= pozice.x+1 && direction.y <= pozice.y+1))
                                  && (Math.abs(pozice.x-direction.x) == Math.abs(pozice.y-direction.y)))
                                   {
                                        rightToMove=true;
                                        sideToMove=false;                                      
                                   }
                                if((direction.x == pozice.x && direction.y >= pozice.y-1) 
                                   || (direction.x == pozice.x && direction.y <= pozice.y+1)
                                   || (direction.x <= pozice.x-1 && direction.y == pozice.y) 
                                   || (direction.x >= pozice.x+1 && direction.y == pozice.y)){
                                        rightToMove=true;
                                        sideToMove=false;
                                      }  
                              }
                            }
                          }                      
                       }
                     }
                     if(chooseAndPlay==false){
                     Point vpred =  new Point(model.getOccupied(pozice, direction));  
                     if(vpred!=null){
                     if(model.isFree(vpred) && rightToMovePawn==true){  
                     model.getObjectAt1(pozice,direction);      
                     }      
                     else if(rightToMove==false){
                     System.out.println("Nepovoleny tah, nebo je na tahu protivnik");
                     } 
                     
                     
                     if(rightToMove==true){
                         if(model.isFree(vpred)){
                           model.getObjectAt1(pozice,direction);
                         }
                         else{
                          model.remove(vpred);
                          model.getObjectAt1(pozice,direction);
                         }
                     }
                     }
                     }
                     chooseAndPlay = true;
                     }else{
                         int xosa = (int)event.getSceneX();
                         int yosa = (int)event.getSceneY();
                         xosa = xosa/100;
                         yosa = yosa/100;
                         
                         pozice = new Point(xosa,yosa);
                            for (ModelObject object : model.getObjects()) {
                                if( object.position.equals(pozice))
                                    { 
                                     if(object instanceof Figures){
                                   
                                     if (((Figures) object).getType() == Figures.Type.Pawn )
                                     {
                                       System.out.println("Vybral jsi pincla na pozici "+pozice);  
                                     }
                                     if (((Figures) object).getType() == Figures.Type.Rook )
                                     {
                                       System.out.println("Vybral jsi vez na pozici "+pozice);            
                                     }
                                      if (((Figures) object).getType() == Figures.Type.Bishop )
                                     {
                                       System.out.println("Vybral jsi strelce na pozici "+pozice);            
                                     }
                                     if (((Figures) object).getType() == Figures.Type.Knight )
                                     {
                                       System.out.println("Vybral jsi kone na pozici "+pozice);            
                                     }
                                     if (((Figures) object).getType() == Figures.Type.King )
                                     {
                                       System.out.println("Vybral jsi krale na pozici "+pozice);            
                                     }
                                     if (((Figures) object).getType() == Figures.Type.Queen )
                                     {
                                       System.out.println("Vybral jsi kralovnu na pozici "+pozice);            
                                     }
                                      chooseAndPlay= false;
                                    }
                                 } 
                            }
                     }
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
