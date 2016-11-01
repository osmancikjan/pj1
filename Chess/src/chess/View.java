/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

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
    private final Image white_pawn,black_pawn,white_king,black_king,
            black_queen,white_queen,black_horse,white_horse,black_bishop,
            white_bishop, white_rook,black_rook;

    private Model model;
    
    View(GraphicsContext context, Model model) {
        this.context = context;
        black_pawn = new Image("file:src/images/Black_Pawn.png");
        white_pawn = new Image("file:src/images/White_Pawn.png");
        white_king = new Image("file:src/images/White_King.png");
        black_king = new Image("file:src/images/Black_King.png");
        white_queen = new Image("file:src/images/White_Queen.png");
        black_queen = new Image("file:src/images/Black_Queen.png");
        white_horse = new Image("file:src/images/White_Horse.png");
        black_horse = new Image("file:src/images/Black_Horse.png");
        white_bishop = new Image("file:src/images/White_Bishop.png");
        black_bishop = new Image("file:src/images/Black_Bishop.png");
        white_rook = new Image("file:src/images/White_Rook.png");
        black_rook = new Image("file:src/images/Black_Rook.png");
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
                    if (((Square) object).getType() == Square.Type.White_Pawn) {
                        image = white_pawn;
                    } 
                    else if(((Square) object).getType() == Square.Type.Black_Pawn)
                    {
                        image = black_pawn;
                    }
                    else if(((Square) object).getType() == Square.Type.Black_King)
                    {
                        image = black_king;
                    }
                    else if(((Square) object).getType() == Square.Type.White_King)
                    {
                        image = white_king;
                    }
                    else if(((Square) object).getType() == Square.Type.Black_Queen)
                    {
                        image = black_queen;
                    }
                    else if(((Square) object).getType() == Square.Type.White_Queen)
                    {
                        image = white_queen;
                    }
                    else if(((Square) object).getType() == Square.Type.Black_Horse)
                    {
                        image = black_horse;
                    }
                    else if(((Square) object).getType() == Square.Type.White_Horse)
                    {
                        image = white_horse;
                    }
                    else if(((Square) object).getType() == Square.Type.Black_Bishop)
                    {
                        image = black_bishop;
                    }
                    else if(((Square) object).getType() == Square.Type.White_Bishop)
                    {
                        image = white_bishop;
                    }
                    else if(((Square) object).getType() == Square.Type.Black_Rook)
                    {
                        image = black_rook;
                    }
                    else if(((Square) object).getType() == Square.Type.White_Rook)
                    {
                        image = white_rook;
                    }
                drawSquare(object.getX(), object.getY(), image);
            }
        }
    }
}
}

