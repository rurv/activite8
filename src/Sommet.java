import java.util.*;

public class Sommet {
    /* attributs à compléter */
    private int id;
    private String nom;
    private List<Sommet> l_successeurs;

    /* constructeur à compléter */
    public Sommet(int id, String nom) {
        this.id = id;
        this.nom = nom;
        l_successeurs = new ArrayList<Sommet>();
    }

    /* méthode d'ajout d'un successeur à compléter */
    public void ajouterSuccesseur(Sommet s){
        l_successeurs.add(s);
    }



    /*accesseurs à compléter */
    public ArrayList<Sommet> getSuccesseurs(){
        return (ArrayList<Sommet>) l_successeurs;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return nom;
    }
}
