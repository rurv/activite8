import java.util.*;

public class Sommet {
    /* attributs à compléter */
    int id;
    String nom;
    List<Sommet> l_successeurs;

    /* constructeur à compléter */
    public Sommet(int id, String nom) {
        this.id = id;
        this.nom = nom;
        l_successeurs = new ArrayList<Sommet>();
    }

    /* méthode d'ajout d'un successeur à compléter */
    public void ajouterSuccesseur(Sommet s){

    }



    /*accesseurs à compléter */
    public ArrayList<Sommet> getSuccesseurs(){

    }
    public int getId(){

    }
    public String getName(){

    }
}
