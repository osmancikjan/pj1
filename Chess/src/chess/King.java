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
public class King extends ModelObject {
     private final Model model;

     public enum Color {
        Black,White;
    }
     
    private final Color color;

    public King(Point position, Model model, Color color) {
        super(position);
        this.model = model;
        this.color = color;
    }
    
    public King.Color getColor() {
        return color;
    }
    @Override
    public void process() {
       
    }
}
