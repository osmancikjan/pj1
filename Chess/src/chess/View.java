/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author Dro0076
 */
public class View {
    public final static int height = 700;
    public final static int width = 700;

    private GraphicsContext context;
    private final Image black_back,white_back, white_pawn,black_pawn, 
                        white_knight, black_knight, white_rook,black_rook,
                        white_bishop, black_bishop, white_queen, black_queen,
                        white_king, black_king;

   
    private Model model;
    
    View(GraphicsContext context, Model model) {
        this.context = context;
        
        black_back = new Image("file:src/images/GreyBack.jpg");
        white_back = new Image("file:src/images/WhiteBack.jpg"); 
        white_pawn = new Image("file:src/images/White_Pawn.png"); 
        black_pawn = new Image("file:src/images/Black_Pawn.png");
        white_knight = new Image("file:src/images/White_Horse.png");
        black_knight = new Image("file:src/images/Black_Horse.png");
        white_rook = new Image("file:src/images/White_Rook.png");
        black_rook = new Image("file:src/images/Black_Rook.png");
        white_bishop = new Image("file:src/images/White_Bishop.png");
        black_bishop = new Image("file:src/images/Black_Bishop.png");
        white_queen = new Image("file:src/images/White_Queen.png");
        black_queen = new Image("file:src/images/Black_Queen.png");
        white_king =new Image("file:src/images/White_King.png");
        black_king =new Image("file:src/images/Black_King.png");
        this.model = model;
        update();
    }
    
     private void drawImage(Image image, Point2D point) {
        context.drawImage(image, point.getX() - image.getWidth() / 2, point.getY() - image.getHeight() / 2);
    }
     
     private void drawSquare(double x, double y, Image image) {
        int squareX = View.width / model.Width;
        int squareY = View.height / model.Heigth;

        context.drawImage(image, x * squareX, y * squareY, squareX, squareY);
    }
     
        public void update() {
        context.setFill(Color.GRAY);
        context.fillRect(0, 0,
                context.getCanvas().getWidth(),
                context.getCanvas().getHeight());
        
        synchronized (model) {
            for (ModelObject object : model.getObjects()) {
                Image image = null;
                if (object instanceof Square) {
                    if (((Square) object).getType() == Square.Type.White_back) {
                        image = white_back;
                    } 
                    else{
                        image = black_back;
                    }
                }
                if (object instanceof Pawn)
                {
                    if (((Pawn) object).getColor() == Pawn.Color.White){
                    image = white_pawn;
                    }
                    else {
                    image = black_pawn;
                    }
                }
                if (object instanceof Knight)
                {
                    if (((Knight) object).getColor() == Knight.Color.White){
                    image = white_knight;
                    }
                    else {
                    image = black_knight;
                    }
                }
                if (object instanceof Rook)
                {
                    if (((Rook) object).getColor() == Rook.Color.White){
                    image = white_rook;
                    }
                    else {
                    image = black_rook;
                    }
                }
                if (object instanceof Bishop)
                {
                    if (((Bishop) object).getColor() == Bishop.Color.White){
                    image = white_bishop;
                    }
                    else {
                    image = black_bishop;
                    }
                }
                if (object instanceof Queen)
                {
                    if (((Queen) object).getColor() == Queen.Color.White){
                    image = white_queen;
                    }
                    else {
                    image = black_queen;
                    }
                }
                if (object instanceof King)
                {
                    if (((King) object).getColor() == King.Color.White){
                    image = white_king;
                    }
                    else {
                    image = black_king;
                    }
                }
                drawSquare(object.getX(), object.getY(), image);
            }
        }
    }
}


