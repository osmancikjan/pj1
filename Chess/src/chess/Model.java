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
public class Model {

    /**
     *
     */
    public int Heigth;
    public int Width;
    
    private ArrayList<ModelObject> objects = new ArrayList<>();
    private Pawn pawn;
    private Knight knight;
    private Rook rook;
    private Bishop bishop;
    private Queen queen;
    private King king;
    
    
    public ArrayList<ModelObject> getObjects() {
        return this.objects;
    }
    
    public Model() {

    }
    
    public synchronized ModelObject getObjectAt(Point position) {
        for(ModelObject object:objects) {
            if (object.getPosition().equals(position)) {
                return object;
            }
        }
        return null;
    }
    
      public synchronized void remove(Point position) {
        for(ModelObject object:objects) {
            if (object.getPosition().equals(position)) {
                objects.remove(object);
                return;
            }
        }
    }
    public synchronized void Choosen(Point position) {
        pawn.Choosen(position);
    }    
      
    public synchronized void Move(Point position ,Point direction) {
        pawn.Move(position,direction);
    }  
      
    public synchronized void initGame() {
        objects.clear();
        String[] init = {
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB"
};
        /*background*/
        Heigth = init.length;
        int i=0;
        for (String s : init) {
            Width = s.length();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'W') {
                    objects.add(new Square(new Point(j, i), Square.Type.White_back));
                } 
                else if (s.charAt(j) == 'B')
                {
                    objects.add(new Square(new Point(j, i), Square.Type.Black_back));
                }
            }
            i++;
        }
        /*pawns*/
        for (int k=0;k<8;k++){
        Point possition = new Point(k,6);
        pawn = new Pawn(possition, this,Pawn.Color.White);
        objects.add(pawn);
        possition = new Point(k,1);
        pawn = new Pawn(possition, this,Pawn.Color.Black);
        objects.add(pawn);
        }
        /*knights*/
        for (int k=1;k<7;k=k+5){
        Point possition = new Point(k,7);
        knight = new Knight(possition, this,Knight.Color.White);
        objects.add(knight);
        possition = new Point(k,0);
        knight = new Knight(possition, this,Knight.Color.Black);
        objects.add(knight);
        }
        /*rooks*/
        for (int k=0;k<8;k=k+7){
        Point possition = new Point(k,7);
        rook = new Rook(possition, this,Rook.Color.White);
        objects.add(rook);
        possition = new Point(k,0);
        rook = new Rook(possition, this,Rook.Color.Black);
        objects.add(rook);
        }
        /*Bishops*/
        for (int k=2;k<6;k=k+3){
        Point possition = new Point(k,7);
        bishop = new Bishop(possition, this,Bishop.Color.White);
        objects.add(bishop);
        possition = new Point(k,0);
        bishop = new Bishop(possition, this,Bishop.Color.Black);
        objects.add(bishop);
        }
        /*Queens*/ 
        Point possition = new Point(4,7);
        queen = new Queen(possition, this,Queen.Color.White);
        objects.add(queen);
        possition = new Point(4,0);
        queen = new Queen(possition, this,Queen.Color.Black);
        objects.add(queen);    
        /*Kings*/
        possition = new Point(3,7);
        king = new King(possition, this,King.Color.White);
        objects.add(king);
        possition = new Point(3,0);
        king = new King(possition, this,King.Color.Black);
        objects.add(king);
        System.out.println(possition); //pozice černého krále
    }
}