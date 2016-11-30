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
    private Figures figure;
    private boolean side=true;
    private boolean pawnTaking = false;
    private boolean pom;
    
    public Movement(){
       model = new Model();
    }
   
public synchronized boolean MoveThatFigure(Figures.Color color, Figures.Type typ,Point pozice,Point direction){
    if(color == Figures.Color.White && side==true){    
     
        if(typ == Figures.Type.Pawn){     
                        if(direction.x == pozice.x && direction.y == pozice.y-1){
                            side = false; 
                            return true;
                        }
                        else if(direction.x == pozice.x-1 && direction.y == pozice.y-1 
                                 ||direction.x == pozice.x+1 && direction.y == pozice.y-1){
                                 side = false; 
                                 this.pawnTaking= true;
                                 return true;
                                 } 
        }                    
 
        if (typ == Figures.Type.Rook){
            if((direction.x == pozice.x && direction.y >= pozice.y-1) 
            || (direction.x == pozice.x && direction.y <= pozice.y+1)
            || (direction.x <= pozice.x-1 && direction.y == pozice.y) 
            || (direction.x >= pozice.x+1 && direction.y == pozice.y)){
                 side = false;
                 return true;
            }
        }
        
        if (typ == Figures.Type.Knight){
            if((direction.x == pozice.x+2 && direction.y == pozice.y-1)
               || (direction.x == pozice.x-2 && direction.y == pozice.y-1)
               || (direction.x == pozice.x-2 && direction.y == pozice.y+1) 
               || (direction.x == pozice.x+2 && direction.y == pozice.y+1)
               || (direction.x == pozice.x+1 && direction.y == pozice.y+2)
               || (direction.x == pozice.x+1 && direction.y == pozice.y-2)
               || (direction.x == pozice.x-1 && direction.y == pozice.y-2)
               || (direction.x == pozice.x-1 && direction.y == pozice.y+2)){
                    side = false;
                    return true;
            }              
        }
        
        if (typ == Figures.Type.Bishop){
            if(((direction.x >= pozice.x+1 && direction.y >= pozice.y-1) 
             || (direction.x <= pozice.x-1 && direction.y >= pozice.y-1)
             || (direction.x <= pozice.x-1 && direction.y <= pozice.y+1) 
             || (direction.x >= pozice.x+1 && direction.y <= pozice.y+1))
             && (Math.abs(pozice.x-direction.x) == Math.abs(pozice.y-direction.y))){
                    side = false;
                    return true;
            }
        }
        
        if (typ == Figures.Type.Queen){
           if(((direction.x >= pozice.x+1 && direction.y >= pozice.y-1) 
           || (direction.x <= pozice.x-1 && direction.y >= pozice.y-1)
           || (direction.x <= pozice.x-1 && direction.y <= pozice.y+1) 
           || (direction.x >= pozice.x+1 && direction.y <= pozice.y+1))
           && (Math.abs(pozice.x-direction.x) == Math.abs(pozice.y-direction.y))){
                    side = false;
                    return true;
           }
           if((direction.x == pozice.x && direction.y >= pozice.y-1) 
           || (direction.x == pozice.x && direction.y <= pozice.y+1)
           || (direction.x <= pozice.x-1 && direction.y == pozice.y) 
           || (direction.x >= pozice.x+1 && direction.y == pozice.y)){
                    side = false;
                    return true;
           }  
         }
         
        if (typ == Figures.Type.King){ 
             if(direction.x == pozice.x && direction.y == pozice.y-1 ||direction.x == pozice.x-1 && direction.y == pozice.y-1
             ||direction.x == pozice.x && direction.y == pozice.y+1 ||direction.x == pozice.x-1 && direction.y == pozice.y+1
             ||direction.x == pozice.x+1 && direction.y == pozice.y ||direction.x == pozice.x+1 && direction.y == pozice.y-1
             ||direction.x == pozice.x-1 && direction.y == pozice.y ||direction.x == pozice.x+1 && direction.y == pozice.y+1){
                     side = false;
                     return true;                 
        }
        }
       
    }else if(color == Figures.Color.Black && side==false){
        
        if(typ == Figures.Type.Pawn){           
           if(direction.x == pozice.x && direction.y == pozice.y+1 && model.isFree(direction)){
                    side = true;
                    return true;
            }
            else if((direction.x == pozice.x+1 && direction.y == pozice.y+1 
            ||direction.x == pozice.x-1 && direction.y == pozice.y+1) 
            && !model.isFree(direction)){
                    side = true;
                    return true;                    
            }
        }  
        
        if (typ == Figures.Type.Rook){
          if((direction.x == pozice.x && direction.y >= pozice.y-1) 
            || (direction.x == pozice.x && direction.y <= pozice.y+1)
            || (direction.x <= pozice.x-1 && direction.y == pozice.y) 
            || (direction.x >= pozice.x+1 && direction.y == pozice.y)){
                    side = true;
                    return true;
          }    
         }
         
        if (typ == Figures.Type.Knight){
           if((direction.x == pozice.x+2 && direction.y == pozice.y-1)
              || (direction.x == pozice.x-2 && direction.y == pozice.y-1)
              || (direction.x == pozice.x-2 && direction.y == pozice.y+1) 
              || (direction.x == pozice.x+2 && direction.y == pozice.y+1)
              || (direction.x == pozice.x+1 && direction.y == pozice.y+2)
              || (direction.x == pozice.x+1 && direction.y == pozice.y-2)
              || (direction.x == pozice.x-1 && direction.y == pozice.y-2)
              || (direction.x == pozice.x-1 && direction.y == pozice.y+2)){
                    side = true;
                    return true;  
           }
        }
         
        if (typ == Figures.Type.Bishop){
           if(((direction.x >= pozice.x+1 && direction.y >= pozice.y-1) 
              || (direction.x <= pozice.x-1 && direction.y >= pozice.y-1)
              || (direction.x <= pozice.x-1 && direction.y <= pozice.y+1) 
              || (direction.x >= pozice.x+1 && direction.y <= pozice.y+1))
              && (Math.abs(pozice.x-direction.x) == Math.abs(pozice.y-direction.y))){
                      side = true;
                      return true;
             }
        }
        
         if (typ == Figures.Type.Queen){
             if(((direction.x >= pozice.x+1 && direction.y >= pozice.y-1) 
             || (direction.x <= pozice.x-1 && direction.y >= pozice.y-1)
             || (direction.x <= pozice.x-1 && direction.y <= pozice.y+1) 
             || (direction.x >= pozice.x+1 && direction.y <= pozice.y+1))
             && (Math.abs(pozice.x-direction.x) == Math.abs(pozice.y-direction.y)))
               {
                        side = true;
                        return true;                                     
               }
             if((direction.x == pozice.x && direction.y >= pozice.y-1) 
                || (direction.x == pozice.x && direction.y <= pozice.y+1)
                || (direction.x <= pozice.x-1 && direction.y == pozice.y) 
                || (direction.x >= pozice.x+1 && direction.y == pozice.y)){
                        side = true;
                        return true;
                }  
         }
         
         
         if (typ == Figures.Type.King){ 
              if(direction.x == pozice.x && direction.y == pozice.y-1 
                ||direction.x == pozice.x-1 && direction.y == pozice.y-1
                ||direction.x == pozice.x && direction.y == pozice.y+1 
                ||direction.x == pozice.x-1 && direction.y == pozice.y+1
                ||direction.x == pozice.x+1 && direction.y == pozice.y 
                ||direction.x == pozice.x+1 && direction.y == pozice.y-1
                ||direction.x == pozice.x-1 && direction.y == pozice.y 
                ||direction.x == pozice.x+1 && direction.y == pozice.y+1){
                        side = true;
                        return true;
              }                                
         }
      }   
 return false;          
}

public boolean PawnTaking(){
     return this.pawnTaking;
 }

}