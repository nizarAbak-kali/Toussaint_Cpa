package algorithms;

import supportGUI.Circle;
import supportGUI.Line;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class DefaultTeam {

    //TRI PAR PIXEL POUR GRAHAM
    // on trie les point
    public ArrayList<Point> filtreParPixel1(ArrayList<Point> points) {

        ArrayList<Point> res = new ArrayList<Point>();
        ArrayList<Point> copie = new ArrayList<Point>();
        copie = (ArrayList<Point>) points.clone();
        int ordmax, ordmin, ordmoy;
        ordmax = 0;
        ordmin = points.get(0).y;


        for (Point p : points) {
            if (ordmax < p.y)
                ordmax = p.y;
            if (ordmin > p.y)
                ordmin = p.y;
        }
        ordmoy = (ordmax + ordmin) / 2;

        ArrayList<Point> petit = new ArrayList<Point>();
        ArrayList<Point> grand = new ArrayList<Point>();

        for (Point p : points) {
            if (p.y <= ordmoy)
                petit.add(p);
            else {
                grand.add(p);
            }
        }
        // on trie les deux sous listes


        return res;
    }

    //TRI PAR PIXEL POUR GRAHAM
    // on trie les point
    public ArrayList<Point> filtreParPixel2(ArrayList<Point> points) {
        ArrayList<Point> res = new ArrayList<Point>();
        Point ymin[];
        Point ymax[];

        ymax = new Point[1024];
        ymin = new Point[1024];
        System.out.println("on est dans la le filtre");
        // on met les tableaux a null
        for (int i = 0; i < ymin.length; i++) {
            ymax[i] = null;
            ymin[i] = null;
        }
        System.out.println("boucle 1 fini");
        for (Point p : points) {
            if ((ymin[p.x] == null) || (ymin[p.x].y < p.y))
                ymin[p.x] = p;
        }
        System.out.println("boucle 2 fini");
        for (Point p : points) {
            if ((ymax[p.x] == null) || (ymax[p.x].y < p.y))
                ymax[p.x] = p;
        }
        System.out.println("boucle 3 fini");
        for (int i = 0; i < ymin.length; i++) {
            if (ymax[i] != null)
                res.add(ymax[i]);
            if (ymin[i] != null)
                res.add(ymin[i]);
        }
        System.out.println("boucle 4 fini");
        return res;
    }


    //SHAMOS VRAI
    // calculDiametreOptimise: ArrayList<Point> --> Line
    //   renvoie une pair de points de la liste, de distance maximum.
    public Line calculDiametreOptimise(ArrayList<Point> points) {
        if (points.size() < 3) {
            return null;
        }
        Line line;
        Point pp, pq, pp0, pq0;
        pp = new Point();
        pq = new Point();
        pp0 = new Point();
        pq0 = new Point();
        //calculer le point p de  dist max de la droite (ab)
        int p = 0;
        int p0 = points.size() - 1;
        int q = p + 1;
        ArrayList<Point> airetmp1, airetmp2;
        airetmp1 = new ArrayList<Point>();
        airetmp2 = new ArrayList<Point>();
        //Area p next p ,next q
        airetmp1.add(points.get(p));
        airetmp1.add(points.get(p + 1));
        airetmp1.add(points.get(q + 1));
        //Area p next p , q
        airetmp1.add(points.get(p));
        airetmp1.add(points.get(p + 1));
        airetmp1.add(points.get(q));
        int q0;
        while (airePolygone(airetmp1) > airePolygone(airetmp2)) {
            q += 1;
            q0 = q;
            while (q != p0) {
                p += 1;
                System.out.println(" p " + p + " || " + q + "  q ");

                airetmp1.clear();
                airetmp2.clear();
                //Area p next p ,next q
                airetmp1.add(points.get(p));
                airetmp1.add(points.get(p + 1));
                airetmp1.add(points.get(q + 1));
                //Area p next p , q
                airetmp1.add(points.get(p));
                airetmp1.add(points.get(p + 1));
                airetmp1.add(points.get(q));
                while (airePolygone(airetmp1) > airePolygone(airetmp2)) {
                    q += 1;

                    if (p != q0 && q != p0) {
                        System.out.println(" p " + p + " || " + q + "  q ");
                        line = new Line(points.get(p), points.get(q));
                    } else {
                        return new Line(points.get(p), points.get(q));
                    }
                }
                airetmp1.clear();
                airetmp2.clear();
                //Area p next p ,next q
                airetmp1.add(points.get(p));
                airetmp1.add(points.get(p + 1));
                airetmp1.add(points.get(q + 1));
                //Area p next p , q
                airetmp1.add(points.get(p));
                airetmp1.add(points.get(p + 1));
                airetmp1.add(points.get(q));

                if (airePolygone(airetmp1) == airePolygone(airetmp2)) {
                    if (p != q0 && q != p0) {
                        System.out.println(" p " + p + " || " + q + "  q ");
                    } else {
                        System.out.println(" p " + p + " || " + q + "  q ");
                    }
                }
            }

        }
        return new Line(points.get(p), points.get(q));
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
        ArrayList<Point> minY = new ArrayList<Point>();
        ArrayList<Point> sortPoints = new ArrayList<Point>();


        //extraire ceux d'ordonnee minimum de points
        int min = 100000;
        int indiceMin = 0;
        int moitie = points.size() / 2;
        for (int i = 0; i < moitie; i++) {
            min = 100000;
            for (int j = 0; j < points.size(); j++) {
                if (points.get(j).y < min) {
                    min = points.get(j).y;
                    indiceMin = j;
                }
            }
            minY.add(points.get(indiceMin));
            points.remove(indiceMin);
        }

        //il reste dans points ceux dordonnee maximum

        //tri croissant sur les x dans les ordonnee minimum
        Collections.sort(minY, new Comparator<Point>() {

            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.x, o2.x);
            }
        });
        //tri croissant sur les x dans les ordonnee maximum (seront inverser lors de la fusion)
        Collections.sort(points, new Comparator<Point>() {

            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.x, o2.x);
            }
        });
        //fusion
        for (int k = 0; k < minY.size(); k++) sortPoints.add(minY.get(k));
        for (int l = points.size() - 1; l >= 0; l--) sortPoints.add(points.get(l));
        minY.clear();
        points.clear();
        //fin du tri par pixel
        //supprimer les points au milieu de segment qui tourne a droite
        enveloppe.add(sortPoints.get(0));
        enveloppe.add(sortPoints.get(1));
        for (int s = 2; s < sortPoints.size(); s++) {
            while (enveloppe.size() >= 2 ? (produitVectoriel(enveloppe.get(enveloppe.size() - 2), enveloppe.get(enveloppe.size() - 1), sortPoints.get(s)) <= 0) : enveloppe.get(enveloppe.size() - 1) == sortPoints.get(s)) {
                enveloppe.remove(enveloppe.size() - 1);
            }
            enveloppe.add(sortPoints.get(s));
        }
        //points.addAll(sortPoints);
        sortPoints.clear();
        //fin
        return enveloppe;
    }

    public double produitVectoriel(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
    }
    /**
     * *****************************     PROJET CPA    ******************************************
     */
    //RITTER pour le cercle min       FAIT et sa TOURNE
    public Circle calculCercleMin(ArrayList<Point> points) {
        if (points.isEmpty()) {
            return null;
        }
        System.out.println("nb point avant le filtre : " + points.size());
        //points= filtreParPixel(points);

        System.out.println("nb point avant le filtre : " + points.size());
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

    public Point westPoint(ArrayList<Point> env) {
        Point res = env.get(0);
        for (Point p : env) {
            if (p.getX() < res.getX()) {
                res = p;
            }
        }
        return res;
    }

    public Point estPoint(ArrayList<Point> env) {
        Point res = env.get(0);
        for (Point p : env) {
            if (p.getX() > res.getX()) {
                res = p;
            }
        }
        return res;
    }
    /***************************************************************************/
    public ArrayList<Point> toussaint(ArrayList<Point> points) {
        ArrayList<Point> box, env;
        Point west, est;
        Droite left, right;


        env = enveloppeConvexe(points);

        // les point ouest et est
        west = westPoint(env);
        est = estPoint(env);

        // left la droite verticale  qui passe par west
        left = new Droite(0.0, west.getX());

        // right la droite parralle a left qui passe par est
        right = new Droite(left.coeff, est.getX());


        return new ArrayList<Point>();
    }

    public int airePolygone(ArrayList<Point> points) {
        double aire, tmp1, tmp2;
        int x, y;
        int j;
        tmp1 = 0.0;
        tmp2 = 0.0;
        aire = 0.0;
        for (int i = 0; i < points.size(); i++) {
            j = i + 1;
            if (i == points.size() - 1)
                j = 1;
            tmp1 += points.get(i).getX() * points.get(j).getY();

        }
        for (int i = 0; i < points.size(); i++) {
            j = i + 1;
            if (i == points.size() - 1)
                j = 1;
            tmp2 += points.get(i).getY() * points.get(j).getX();
        }
        aire = (tmp1 - tmp2) / 2;
        return (int) aire;
    }

    public int aireCercle(Circle c) {
        double aire = 0;
        aire = 2 * StrictMath.PI * (c.getRadius()) * (c.getRadius());
        return (int) aire;
    }

    public void testQualite(ArrayList<Point> points) {
        // aire du rectangle /aire du convex -100%
    }



}
