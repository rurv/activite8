import java.io.IOException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Graphe g=new Graphe();
        g.charger("activite6_wiki.txt");
        g.afficherListeSucc();
        System.out.println("Exploration en largeur (BFS) :");
        System.out.println("Sommets découverts dans l'ordre :");
        afficher_ordre_decouverte(g.explorerLargeur(g.l_sommets.getFirst()));

        System.out.println("Exploration en profondeur itératif avec pile (DFS) :");
        System.out.println("Sommets découverts dans l'ordre :");
        System.out.println("Sommets explorés dans l'ordre :");

        System.out.println("Exploration en profondeur récursif (DFS récursif) :");
        System.out.println("Sommets découverts dans l'ordre :");
        afficher_ordre_decouverte(g.explorerProfondeur_rec(g.l_sommets.getFirst()));
        System.out.println("\n--- Recherche de chemins ---");

        // PARTIE II : Recherche de chemins
        Sommet depart = g.getSommet(0); // Modifie les id selon les sommets à tester
        Sommet arrivee = g.getSommet(g.l_sommets.size() - 1);

        // 1. Calcul des tableaux de parents[span_4](start_span)[span_4](end_span)
        ArrayList<Sommet> parentsBFS = g.BFS_Parents(depart);
        ArrayList<Sommet> parentsDFS = g.DFS_parents(depart);

        // 2. Reconstruction des chemins[span_5](start_span)[span_5](end_span)
        ArrayList<Sommet> cheminBFS = g.retrouverChemin(depart, arrivee, parentsBFS);
        ArrayList<Sommet> cheminDFS = g.retrouverChemin(depart, arrivee, parentsDFS);

        // 3. Affichage et comparaison des longueurs[span_6](start_span)[span_6](end_span)
        System.out.print("Chemin BFS de " + depart.getName() + " à " + arrivee.getName() + " : ");
        afficherChemin(cheminBFS);

        System.out.print("Chemin DFS de " + depart.getName() + " à " + arrivee.getName() + " : ");
        afficherChemin(cheminDFS);
    }

    public static void afficher_ordre_decouverte(ArrayList<Sommet> l) {
        for (Sommet s : l) {
            System.out.println(s.getName());
        }
    }

    public static void afficherChemin(ArrayList<Sommet> chemin) {
        if (chemin.isEmpty()) {
            System.out.println("Aucun chemin trouvé (sommet non atteignable).");
            return;
        }
        for (int i = 0; i < chemin.size(); i++) {
            System.out.print(chemin.get(i).getName());
            if (i < chemin.size() - 1) {
                System.out.print(" -> ");
            }
        }
        // La longueur d'un chemin est son nombre d'arcs (nombre de sommets - 1)[span_7](start_span)[span_7](end_span)
        System.out.println(" | Longueur : " + (chemin.size() - 1) + " arc(s)");
    }
}