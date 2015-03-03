package algorithms;

import supportGUI.Circle;
import supportGUI.Line;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class DefaultTeam {
    //TRI PAR PIXEL POUR GRAHAM
    // on trie les point
    public Line filtrealacon(ArrayList<Point> points) {
        //contient les points dans l'orde croissant de leurs abscisses
        ArrayList<Point> bucket_abc = new ArrayList<Point>();
        //contient les points dans l'orde croissant de leurs abscisses
        ArrayList<Point> bucket_ord = new ArrayList<Point>();
        ArrayList<Point> inter = new ArrayList<Point>();


        //on remplit les array avant de les trier
        for (Point i : points) {
            bucket_abc.add(i);
            bucket_ord.add(i);

        }

        Point center = points.get(0);
        int radius = 100;

        /*******************
         * PARTIE A ECRIRE *
         *******************/
        Point ptmp1 = new Point();
        Point ptmp2 = new Point();
        int distmax, tmp;
        distmax = 0;
        tmp = 0;

        for (Point p : points) {
            for (Point q : points) {
                if (p.equals(q)) continue;
                tmp = (int) (p.distance(q) + 0.5);
                if (distmax < tmp) {
                    distmax = tmp;
                    ptmp1.x = p.x;
                    ptmp1.y = p.y;
                    ptmp2.x = q.x;
                    ptmp2.y = q.y;

                }
            }
        }


        return new Line(ptmp1, ptmp2);

    }


    //SHAMOS VRAI
    // calculDiametreOptimise: ArrayList<Point> --> Line
    //   renvoie une pair de points de la liste, de distance maximum.
    public Line calculDiametreOptimise(ArrayList<Point> points) {
        if (points.size() < 3) {
            return null;
        }
        Point a = points.get(0);
        Point b = points.get(1);

        //calculer le point p de  dist max de la droite (ab)

        Point p = points.get(0);
        for (Point tmp : points) {

        }

        /*******************
         * PARTIE A ECRIRE *
         *******************/
        return new Line(a, b);
    }

    // calculCercleMin: ArrayList<Point> --> Circle
    //   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
    //verision perso et naive
    public Circle calculCercleMinNaif(ArrayList<Point> points) {
        if (points.isEmpty()) {
            return null;
        }

        Point center = points.get(0);
        int radius = 100;

        /*******************
         * PARTIE A ECRIRE *
         *******************/
        Point ptmp1 = new Point();
        Point ptmp2 = new Point();
        int distmax, tmp;
        distmax = 0;
        tmp = 0;

        for (Point p : points) {
            for (Point q : points) {
                if (p.equals(q)) continue;
                tmp = (int) (p.distance(q) + 0.5);
                if (distmax < tmp) {
                    distmax = tmp;
                    ptmp1.x = p.x;
                    ptmp1.y = p.y;
                    ptmp2.x = q.x;
                    ptmp2.y = q.y;

                }
            }
        }

        center.x = (ptmp1.x + ptmp2.x) / 2;
        center.y = (ptmp1.y + ptmp2.y) / 2;
        radius = (int) (ptmp1.distance(ptmp2)) / 2;


        return new Circle(center, radius);
    }

    // enveloppeConvexe: ArrayList<Point> --> ArrayList<Point>
    //   renvoie l'enveloppe convexe de la liste.
    public ArrayList<Point> enveloppeConvexe(ArrayList<Point> points) {
        if (points.size() < 3) {
            return null;
        }

        ArrayList<Point> enveloppe = new ArrayList<Point>();

        enveloppe.add(points.get(0));
        enveloppe.add(points.get(1));
        enveloppe.add(points.get(2));


        /*******************
         * PARTIE A ECRIRE *
         *******************/


        //return points;
        return enveloppe;
    }


    /**
     * *****************************     PROJET CPA    ******************************************
     */
    //RITTER pour le cercle min       FAIT et sa TOURNE
    public Circle calculCercleMin(ArrayList<Point> points) {
        if (points.isEmpty()) {
            return null;
        }
        Point P = null, Q = null;
        // 1- Choisir un point dummy appartenant a Points
        Point dummy = points.get(0);
        double max = Integer.MIN_VALUE;
        double distancePQ = Integer.MIN_VALUE;
        // 2- Trouver P le point le plus eloigne du point dummy
        for (Point i : points) {
            if (dummy.distance(i) >= max) {
                max = dummy.distance(i);
                P = i;
            }
        }
        // 3- Trouver Q le point le plus eloigne du point P
        for (Point i : points) {
            if (P.distance(i) >= distancePQ) {
                distancePQ = P.distance(i);
                Q = i;
            }
        }
        // 4- Trouver le point centrePQ
        Point centrePQ = new Point((P.x + Q.x) / 2, (P.y + Q.y) / 2);
        // 5- Le cercle ayant le point centre pour centre, et passant par P et Q
        double rayon_c = P.distance(Q) / 2;
        Circle c = new Circle(centrePQ, (int) rayon_c);
        // 6- Enlever les points couverts par le cercle c
        Circle cp = null;
        Circle ctemp = c;
        Point temp = centrePQ;
        double rtemp = rayon_c;
        ArrayList<Point> pointsOut = new ArrayList<Point>();
        do {
            pointsOut = null;
            pointsOut = new ArrayList<Point>();
            // La liste pointsOut contient tous les points qui ne sont pas couvert par c.
            for (Point i : points) {
                if (temp.distance(i) > rtemp) {
                    pointsOut.add(i);
                }
            }

            System.out.println(pointsOut.size());
            // A la fin de cette boucle si pointsOut est vide => c est le cercle minimum !
            if (pointsOut.isEmpty()) return ctemp;
            // 7- s un point parmi ceux qui ne sont pas couverts par le cercle c.
            Point s = pointsOut.get((new Random()).nextInt(pointsOut.size()));
            // 8-

            double centrePQ_s = temp.distance(s); // cs
            double cps = (rtemp + centrePQ_s) / 2; // le nouveau rayon !
            double ccp = centrePQ_s - cps;

            // 9- Le point centre est le centre du segment ST
            Point centre = new Point((int) ((cps / centrePQ_s) * temp.x + (ccp / centrePQ_s) * s.x),
                    (int) ((cps / centrePQ_s) * temp.y + (ccp / centrePQ_s) * s.y));

            // 10- cp est le cercle ayant centre pour centre est cps pour rayon.
            cp = new Circle(centre, (int) centre.distance(s));
            temp = centre;
            ctemp = cp;

            rtemp = centre.distance(s);


        } while (!pointsOut.isEmpty());


        return ctemp;
    }


    /***************************************************************************/


}
