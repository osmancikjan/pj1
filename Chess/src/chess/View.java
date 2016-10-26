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
    private Image white,black;

    private Model model;
    
    View(GraphicsContext context, Model model) {
        this.context = context;
        black = new Image("file:src/images/BlackBack.jpg");
        white = new Image("file:src/images/WhiteBack.jpg");
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
                Image image;
                if (object instanceof Square) {
                    if (((Square) object).getType() == Square.Type.WHITE) {
                        image = white;
                    } else {
                        image = black;
                    }
                drawSquare(object.getX(), object.getY(), image);

            }
        }
    }
}
}

