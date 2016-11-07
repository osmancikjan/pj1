/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.util.ArrayList;


/**
 *
 * @author Dro0076
 */
public class Pawn extends ModelObject{
    private final Model model;
    private ArrayList<ModelObject> objects = new ArrayList<>();

     public enum Color {
        Black,White;
    }
     private final Color color;
     
      public Pawn(Point position, Model model, Color color) {
        super(position);
        this.model = model;
        this.color = color;
    }
    
   
      
    public void Choosen(Point position){
       Point point = position;
        /*for (ModelObject object : model.getObjects()) {
         if(((Pawn) object).getPosition() == position){
            
            
         }
        
        }*/
         System.out.println(point);
    }  

    public void Move(Point position,Point direction){
      Point point = new Point(getX() + direction.x, getY() + direction.y);
      ModelObject object = model.getObjectAt(point);
      move(direction.x, direction.y); 
}
   
    
    public Pawn.Color getColor() {
        return color;
    }
    @Override
    public void process() {
       
    }
    
}
