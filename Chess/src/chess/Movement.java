/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;

/**
 *
 * @author Dro0076
 */
public class Movement {
    private Model model;
    private boolean move=false;
    private boolean side=true;
    
    public Movement(){
       model = new Model();
    }
   
    public synchronized boolean MoveThatFigure(int value, Point pozice,Point direction){
        switch(value){
            case 1:  Point vpred =  new Point(model.getOccupied(pozice, direction));            
                          if(direction.x == pozice.x && direction.y == pozice.y-1 && model.isFree(vpred)){
                             //this.move = true;
                             //this.side = false;
                             return true;
                             }
                           else if((direction.x == pozice.x+1 && direction.y == pozice.y-1 
                                   ||direction.x == pozice.x-1 && direction.y == pozice.y-1) 
                                    && !model.isFree(vpred)){
                                       // this.move = true;
                                        //this.side = false;
                                        return true;
                                   }
                          
           default: return false;
        }
    }
    
    public boolean GetMoveBool(){
        return this.move;
    }
    public boolean GetSideBool(){
        return this.side;
    }
}
