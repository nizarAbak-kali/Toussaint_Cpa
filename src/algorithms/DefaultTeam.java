package algorithms;

import java.awt.Point;
import java.util.ArrayList;

/***************************************************************
 * TME 1: calcul de diamètre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés d'un ensemble de  *
 *     points donné en entrée.                                 *
 *   - Couvrir l'ensemble de poitns donné en entrée par un     *
 *     cercle de rayon minimum.                                *
 *                                                             *
 * class Circle:                                               *
 *   - Circle(Point c, int r) constructs a new circle          *
 *     centered at c with radius r.                            *
 *   - Point getCenter() returns the center point.             *
 *   - int getRadius() returns the circle radius.              *
 *                                                             *
 * class Line:                                                 *
 *   - Line(Point p, Point q) constructs a new line            *
 *     starting at p ending at q.                              *
 *   - Point getP() returns one of the two end points.         *
 *   - Point getQ() returns the other end point.               *
 ***************************************************************/

import supportGUI.Circle;
import supportGUI.Line;

public class DefaultTeam {
	//TRI PAR PIXEL POUR GRAHAM
	// on trie les point 
	public ArrayList<Point> filtrealacon(ArrayList<Point> points) {
		//contient les points dans l'orde croissant de leurs abscisses
		ArrayList<Point> bucket_abc = new ArrayList<Point>();
		//contient les points dans l'orde croissant de leurs abscisses
		ArrayList<Point> bucket_ord = new ArrayList<Point>();
		ArrayList<Point> inter = new ArrayList<Point>();
		
		
		//on remplit les array avant de les trier
		for(Point i : points){
			bucket_abc.add(i);
			bucket_ord.add(i);
			
		}
		
		boolean echange = true;
		//tri a bulle sur les abscisses
		Point tmp , a , b ;
		b = a = tmp = null;
		int j = bucket_abc.size();
		while((j>0) && echange){
			echange = false ;
			//peut etre -1
			for (int i = 1 ;i < bucket_abc.size(); i++){
				a = bucket_abc.get(i);
				b = bucket_abc.get(i+1);
				if(a.x > b.x){
					tmp = a;
					a = b ;
					b = tmp;
					echange = true;
				}//fin si 		
			}//fin for 
			j = j-1;
		}//fin while
		
		j = bucket_ord.size();
		//tri pour les ordonnées 
		while((j>0) && echange){
			echange = false ;
			//peut etre -1
			for (int i = 1 ;i < bucket_ord.size(); i++){
				a = bucket_ord.get(i);
				b = bucket_ord.get(i+1);
				if(a.y > b.y){
					tmp = a;
					a = b ;
					b = tmp;
					echange = true;
				}//fin si 		
			}//fin for 
			j = j-1;
		}//fin while
		
		//on retire les point de meme abscisses
		for (int i = 1 ;i < bucket_abc.size()-1; i++){
			a = bucket_abc.get(i-1);
			tmp = bucket_abc.get(i);
			b = bucket_abc.get(i+1);
			
			if ((a.x==tmp.x) && (b.x==tmp.x)){
				bucket_abc.remove(i);
				i--;
			}
		}
		for (int i = 1 ;i < bucket_ord.size()-1; i++){
			a = bucket_ord.get(i-1);
			tmp = bucket_ord.get(i);
			b = bucket_ord.get(i+1);
			
			if ((a.x==tmp.x) && (b.x==tmp.x)){
				bucket_ord.remove(i);
				i--;
			}
		}
		 for(Point p :bucket_abc)
			 for(Point q:bucket_ord)
				 if(p.equals(q))
					 inter.add(p);
		
		return 	inter;
	}
	
	public ArrayList<Point> filtreParPixel(ArrayList<Point> points){
		ArrayList<Point> res = new ArrayList<Point>();
		ArrayList<Point> copie = new ArrayList<Point>();
		copie = (ArrayList<Point>) points.clone();
		 int ordmax , ordmin ,ordmoy;
		 ordmax = 0 ;
		 ordmin = points.get(0).y;
		 
		 
		 for(Point p :points){
			 if(ordmax < p.y)
				 ordmax = p.y;
			 if(ordmin > p.y)
				 ordmin = p.y;
		 }
		ordmoy = (ordmax+ordmin)/2 ;
		
		ArrayList<Point> petit = new ArrayList<Point>();
		ArrayList<Point> grand = new ArrayList<Point>();
		
		for(Point p : points){
			if(p.y<= ordmoy)
				petit.add(p);
			else {
				grand.add(p);
			}	
		}
		// on trie les deux sous listes 
		  
		
		return res ;
	}

	// calculDiametre: ArrayList<Point> --> Line
	//   renvoie une pair de points de la liste, de distance maximum.
	public Line calculDiametre(ArrayList<Point> points) {
		if (points.size()<3) {
			return null;
		}
		  Point ptmp1 = new Point();
	        Point ptmp2 = new Point();
	        int distmax, tmp;
	        distmax = 0;
	        tmp = 0;

	        for (Point p : points) {
	            for (Point q : points) {
	                if(p.equals(q)) continue;
	                tmp = (int) (p.distance(q) + 0.5) ;
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
			if (points.size()<3) {
				return null;
			}
			Point a = points.get(0);
			Point b = points.get(1);
			
			//calculer le point p de  dist max de la droite (ab)
			
			Point p = points.get(0);
			for(Point tmp : points){
				
			}
			
			/*******************
			 * PARTIE A ECRIRE *
			 *******************/
			return new Line(a,b);
		}
	
	// calculCercleMin: ArrayList<Point> --> Circle
	//   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
	public Circle calculCercleMin(ArrayList<Point> points) {
		if (points.isEmpty()) {
			return null;
		}

		Point center=points.get(0);
		int radius=100;
		
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
                if(p.equals(q)) continue;
                tmp = (int) (p.distance(q) + 0.5) ;
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


		return new Circle(center,radius);
	}
	
	// enveloppeConvexe: ArrayList<Point> --> ArrayList<Point>
	//   renvoie l'enveloppe convexe de la liste.
	//GRAHAM
	public ArrayList<Point> enveloppeConvexe(ArrayList<Point> points){
		if (points.size()<3) {
			return null;
		}
		
		ArrayList<Point> enveloppe = new ArrayList<Point>();
		
		enveloppe.add(points.get(0));
		enveloppe.add(points.get(1));
		enveloppe.add(points.get(2));
		
		
		/*******************
		 * PARTIE A ECRIRE *
		 *******************/
		
		
		return points;
	}
	

}
