/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;


/**
 *
 * @author Honza
 */
public class AI {
    private Movement movement;
    private static float Pawn_eval = 1; 
    private static float Rook_eval = 5; 
    private static float Bishop_eval = 3;
    private static float Knight_eval = (float) 3.5;
    private static float Queen_eval = 10;
    private static float King_eval=100;
    
    public class Move {
        public Point position;
        public Point direction;
        public int evaluation;
        public Move() {
            position = new Point();
            direction = new Point();
        }
        public Move(Point pos, Point dir, int eval){
            position = new Point(pos);
            direction = new Point(dir);
            evaluation = eval;
        }
    }
    //deklarace objektu nejlepsiho tahu
    
    public AI() {
        Move possibleMove = new Move();
    }
    public Movement bestMove() {
        
        return movement;
    }
}
