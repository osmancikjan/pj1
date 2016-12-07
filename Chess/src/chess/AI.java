/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author OSM0014
 */
public class AI {

    private Movement movement;
    private Model model;
    private Move FinalMove;
    public Point FinalFrom;
    public Point FinalTo;
    Point to = new Point();
    Point to2 = new Point();
    ArrayList<Move> moves = new ArrayList<Move>();
    ArrayList<ModelObject> blacks = new ArrayList<ModelObject>();
    ArrayList<ModelObject> whites = new ArrayList<ModelObject>();
    ArrayList<Move> catches = new ArrayList<Move>();

    public class Move {

        public Point from;
        public Point to;
        public Figures.Type type;

        public Move() {
        }

        public Move(Point from, Point to, Figures.Type type) {
            this.from = from;
            this.to = to;
            this.type = type;

        }

        public int GetEvalOf(Figures.Type type) {
            int res = 0;
            switch (type) {
                case King:
                    res = 1000;
                    break;
                case Queen:
                    res = 10;
                    break;
                case Knight:
                    res = 4;
                    break;
                case Bishop:
                    res = 3;
                    break;
                case Rook:
                    res = 5;
                    break;
                case Pawn:
                    res = 1;
                    break;
            }
            return res;
        }
    }
    //deklarace objektu nejlepsiho tahu

    public AI() {
        model = new Model();
        movement = new Movement();
        //Move possibleMove = new Move();
    }

    private boolean TryCatch(ArrayList<ModelObject> blacks, ArrayList<ModelObject> whites, ArrayList<ModelObject> UpdatedObjects) {
        catches.clear();
        for (ModelObject black : blacks) {
            //System.out.println("Projizdim cerne");
            for (ModelObject white : whites) {
                // System.out.println("Projizdim bile");
                if (movement.MoveThatFigure(Figures.Color.Black, ((Figures) black).getType(), black.getPosition(), white.getPosition(), UpdatedObjects)) {
                    catches.add(new Move(black.getPosition(), white.getPosition(), ((Figures) white).getType()));
                    System.out.println("mel bych pridavat / C");
                }
            }
        }
        if (catches.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void GenerateMoves() {
        //vsechny mozne tahy v 1 kroku 
        moves.clear();
        for (int i = 0; i <= 7; i++) {
            for (int j = 2; j <= 7; j++) {
                to = new Point(i, j);
                for (Object fig : blacks) {
                    if (movement.MoveThatFigure(Figures.Color.Black, ((Figures) fig).getType(), ((Figures) fig).getPosition(), to, blacks)) {
                        Move tmp = new Move(((Figures) fig).getPosition(), to, ((Figures) fig).getType());
                        moves.add(tmp);

                    }
                } //vsechny tahu v 2 kroku

            }
        }

    }

    public void MakeMove(ArrayList<ModelObject> objectsOnBoard) {
        ArrayList<ModelObject> objectsForMovement = new ArrayList<ModelObject>(objectsOnBoard.size());
        
        for (Object obj : objectsOnBoard) {
            objectsForMovement.add((ModelObject) obj);
        }
        //rozdeleni na bile / cerne
        blacks.clear();
        whites.clear();
        for (ModelObject object : objectsForMovement) {
            if (object instanceof Figures) {
                if (((Figures) object).getColor() == Figures.Color.Black) {
                    blacks.add(object);
                } else if (((Figures) object).getColor() == Figures.Color.White) {
                    whites.add(object);
                }
            }
        }
        //here

        //Move finalMove = new Move();
        if (TryCatch(blacks, whites, objectsForMovement)) {
            Move bestMove = new Move();
            //int index = 0;
            
            int max = 0;
            for (Move cat : catches) {
                if(!catches.isEmpty()) {
                if (cat.GetEvalOf(cat.type) > max) {
                    max = cat.GetEvalOf(cat.type);
                    //index = moves.indexOf(cat);
                    bestMove.from = cat.from;
                    bestMove.to = cat.to;
                    bestMove.type = cat.type;
                    catches.remove(cat);
                } else {
                    catches.remove(cat);
                }
                } else break;
            }
            this.FinalFrom = new Point(bestMove.from);
            this.FinalTo = new Point(bestMove.to);
        } else {
            GenerateMoves();
            for (int i = 0; i < moves.size(); i++) {
                System.out.print("from x" + moves.get(i).from + " to " + moves.get(i).to + moves.get(i).type + "\n");
            }

            Random r = new Random();
            int maximum = moves.size()-1;
            int minimum = 0;
            int ind = r.nextInt(maximum-minimum)+minimum;

            //   FinalMove = new Move(moves.get(index).from,moves.get(index).to,moves.get(index).type);
            FinalFrom = new Point(moves.get(ind).from);
            FinalTo = new Point(moves.get(ind).to);
        }
        catches.clear();
        moves.clear();
        blacks.clear();
        whites.clear();
        objectsForMovement.clear();
    }

    public Point GetFrom() {
        return FinalFrom;
    }

    public Point GetTo() {
        return FinalTo;
    }
}
