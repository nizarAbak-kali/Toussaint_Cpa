package algorithms;

import java.awt.*;

/**
 * Created by nizar on 25/03/15.
 */
public class GeoUtils {
    public GeoUtils() {
    }


//contient tout les fonctions geometrique importante

    int produitScalaire(Vecteur v1, Vecteur v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    public boolean droiteParr(Droite a, Droite b) {
        int tmp1, tmp2;
        tmp1 = (int) a.coeff;
        tmp2 = (int) b.coeff;
        if (tmp1 == tmp2)
            return true;
        else return false;
    }

    public Point getOrtho(Point p) {


        return new Point();
    }

}
