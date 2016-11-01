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
    public int Heigth;
    public int Width;
    
    private ArrayList<ModelObject> objects = new ArrayList<>();
    
    public ArrayList<ModelObject> getObjects() {
        return this.objects;
    }
    
    
    public Model() {

    }
    /*
    ( - white pawn
    ) - black pawn
    
    */
    
    public synchronized void initGame() {
        objects.clear();
        String[] init = {
            "5341/435",
            "))))))))",
            "........",
            "........",
            "........",
            "........",
            "((((((((",
            "%#$!?$#%"
};
        Heigth = init.length;
        int i=0;
        for (String s : init) {
            Width = s.length();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    objects.add(new Square(new Point(j, i), Square.Type.White_Pawn));
                }
                if (s.charAt(j) == ')'){
                    objects.add(new Square(new Point(j, i), Square.Type.Black_Pawn));
                }
                if (s.charAt(j) == '!'){
                    objects.add(new Square(new Point(j, i), Square.Type.White_King));
                }
                if (s.charAt(j) == '1'){
                    objects.add(new Square(new Point(j, i), Square.Type.Black_King));
                }
                 if (s.charAt(j) == '/'){
                    objects.add(new Square(new Point(j, i), Square.Type.Black_Queen));
                }
                if (s.charAt(j) == '?'){
                    objects.add(new Square(new Point(j, i), Square.Type.White_Queen));
                }
                 if (s.charAt(j) == '3'){
                    objects.add(new Square(new Point(j, i), Square.Type.Black_Horse));
                }
                if (s.charAt(j) == '#'){
                    objects.add(new Square(new Point(j, i), Square.Type.White_Horse));
                }
                if (s.charAt(j) == '4'){
                    objects.add(new Square(new Point(j, i), Square.Type.Black_Bishop));
                }
                if (s.charAt(j) == '$'){
                    objects.add(new Square(new Point(j, i), Square.Type.White_Bishop));
                }
                 if (s.charAt(j) == '5'){
                    objects.add(new Square(new Point(j, i), Square.Type.Black_Rook));
                }
                if (s.charAt(j) == '%'){
                    objects.add(new Square(new Point(j, i), Square.Type.White_Rook));
                }
            }
            i++;
        }
    }
}