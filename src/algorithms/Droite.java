package algorithms;

/**
 * Created by nizar on 25/03/15.
 */
public class Droite {
    double coeff;
    double b;
    Vecteur vecteur_directeur;
    Vecteur vecteur_normal;

    public Droite(double a, double b) {
        this.coeff = a;
        this.b = b;
        this.setVecteur_directeur();
        this.setVecteur_normal();
    }

    public double getCoeff() {
        return this.coeff;
    }

    public double getB() {
        return this.b;
    }

    public Vecteur getVecteurNormal() {
        return this.vecteur_normal;
    }

    public Vecteur getVecteur_directeur() {
        return this.vecteur_directeur;
    }

    public void setVecteur_directeur() {
        this.vecteur_directeur = new Vecteur((int) -(this.b), (int) this.coeff);
    }

    public void setVecteur_normal() {
        this.vecteur_normal = new Vecteur((int) this.coeff, (int) this.b);
    }

    public boolean isParrato(Droite d) {
        if ((int) this.coeff == (int) d.coeff)
            return true;
        else return false;
    }


}
