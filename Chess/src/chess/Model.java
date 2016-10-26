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
    
    public synchronized void initGame() {
        objects.clear();
        String[] init = {
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW"
};
        Heigth = init.length;
        int i=0;
        for (String s : init) {
            Width = s.length();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'W') {
                    objects.add(new Square(new Point(j, i), Square.Type.WHITE));
                }else{
                    objects.add(new Square(new Point(j, i), Square.Type.BLACK));
                }
            }
            i++;
        }
    }
}
