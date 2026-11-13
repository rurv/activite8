import java.io.File;
import java.io.IOException;
import java.util.*;

public class Graphe {
    /* attributs à compléter */
    int orient;
    List<Sommet> l_sommets;

    /* chargement du graphe*/
    public void charger(String ficName)throws IOException {
        //ouverture du fichier en lecture
        Scanner sc=new Scanner(new File(ficName));
        //lecture de l'orientation
        orient=sc.nextInt();
        sc.nextLine();
        //lecture de l'ordre
        int n=sc.nextInt();
        //création de l'ArrayList de sommets
        l_sommets=new ArrayList<Sommet>();
        sc.nextLine();
        int id;
        String nom;
        //lecture, création et ajout des sommets dans l'ARrayList
        for(int i=0;i<n;i++){
            id=sc.nextInt();
            nom=sc.next();
            sc.nextLine();
            l_sommets.add(new Sommet(id,nom));
        }
        //lecture des liens
        int id1,id2;
        while(sc.hasNextLine()){
            //lecture des extrémités
            id1=sc.nextInt();
            id2=sc.nextInt();
            //ajout de id2 aux successeurs de id1
            l_sommets.get(id1).ajouterSuccesseur(l_sommets.get(id2));
            //si le graphe n'est pas orienté : ajout de id1 aux successeurs de id2
            if(orient==0) l_sommets.get(id2).ajouterSuccesseur(l_sommets.get(id1));
            sc.nextLine();
        }
        sc.close();
    }
    // Chercher et retourner un sommet d'après son id
    public Sommet getSommet(int id){
        for(Sommet s:l_sommets){
            if(s.getId()==id) return s;
        }
        return null;
    }
    /*exploration en largeur à partir d'un sommet s
    retour : liste des sommets explorés*/
    public ArrayList<Sommet> explorerLargeur(Sommet s){
        ArrayList<Sommet> s_decouverts = new ArrayList<Sommet>();
        ArrayDeque<Sommet> queue = new ArrayDeque<Sommet>();
        s_decouverts.add(s);
        queue.add(s);
        while (!queue.isEmpty()) {
            for (Sommet si : queue.removeFirst().getSuccesseurs()) {
                if (!s_decouverts.contains(si)) {
                    s_decouverts.add(si);
                    queue.add(si);
                }
            }
        }
        return s_decouverts;
    }
    /*exploration en profondeur à partir d'un sommet s
    version itérative avec pile
   retour : liste des sommets explorés*/
    public ArrayList<Sommet> explorerProfondeur_it(Sommet s){
        ArrayList<Sommet> l_decouverts=new ArrayList<Sommet>();
        Stack<Sommet> pile=new Stack<Sommet>();
        ArrayList<Boolean> marque=new ArrayList<Boolean>(l_sommets.size());
        for(int i=0;i<l_sommets.size();i++){
            marque.add(i,false);
        }
        pile.push(s);
        marque.set(s.getId(),true);
        l_decouverts.add(s);
        while(!pile.isEmpty()){
            s=pile.pop();
            for(Sommet succ:s.getSuccesseurs()){
                if(!(marque.get(succ.getId()))){
                    marque.set(succ.getId(),true);
                    pile.push(succ);
                    l_decouverts.add(succ);
                }
            }
        }
        return l_decouverts;
    }
    /*exploration en profondeur à partir d'un sommet s
    version récursive
   retour : liste des sommets explorés*/
    public ArrayList<Sommet> explorerProfondeur_rec(Sommet s){
        ArrayList<Boolean> marque = new ArrayList<Boolean>();
        for (int i = 0; i < l_sommets.size(); i++) marque.add(false);
        ArrayList<Sommet> l_decouverts = new ArrayList<Sommet>();
        parcoursProfondeur_rec(s, l_decouverts, marque);
        return l_decouverts;
    }
    public void parcoursProfondeur_rec(Sommet s,ArrayList<Sommet> l_decouverts,ArrayList<Boolean> marque){
        marque.set(s.getId(), true);
        l_decouverts.add(s);
        for (Sommet si : s.getSuccesseurs()) {
            if (!marque.get(si.getId())) parcoursProfondeur_rec(si, l_decouverts, marque);
        }
    }
    /* Affichage des listes de successeurs*/
    public void afficherListeSucc(){
        for(Sommet s:l_sommets){
            System.out.print(s.getId()+":");
            for(Sommet succ:s.getSuccesseurs()){
                System.out.print(succ.getId()+" ");
            }
            System.out.println();
        }
    }
    public ArrayList<Sommet> BFS_Parents(Sommet s) {
        ArrayList<Sommet> parents = new ArrayList<Sommet>();
        Queue<Sommet> file = new ArrayDeque<Sommet>();
        ArrayList<Boolean> marque = new ArrayList<Boolean>();

        for (int i = 0; i < l_sommets.size(); i++) {//ON passe a tout les sommets pour les inintiliser a null
            parents.add(i, null);
            marque.add(i, false);
        }
        marque.add(s.getId(), true);
        file.add(s);

        while (!file.isEmpty()) {
            Sommet courant = file.poll();
            for (Sommet succ : courant.getSuccesseurs()) {
                if (!marque.get(succ.getId())) {
                    marque.set(succ.getId(), true);
                    file.add(succ);
                    parents.set(succ.getId(), courant); // Mémorisation du parent


                }
            }
        }
        return parents;
    }
    public ArrayList<Sommet> DFS_parents(Sommet s) {
        ArrayList<Sommet> parents = new ArrayList<Sommet>();
        ArrayList<Boolean> marque = new ArrayList<Boolean>();

        // 1. Initialisation (identique au BFS)
        for (int i = 0; i < l_sommets.size(); i++) {
            parents.add(i, null);
            marque.add(i, false);
        }

        // 2. Marquage du départ et lancement de la récursivité
        marque.set(s.getId(), true);
        parcoursDFS_parents(s, parents, marque);

        return parents;
    }

    private void parcoursDFS_parents(Sommet courant, ArrayList<Sommet> parents, ArrayList<Boolean> marque) {
        for (Sommet succ : courant.getSuccesseurs()) {
            if (!marque.get(succ.getId())) {
                marque.set(succ.getId(), true);
                parents.set(succ.getId(), courant);
                parcoursDFS_parents(succ, parents, marque); // On plonge sur le successeur
            }
        }
    }
    public ArrayList<Sommet> retrouverChemin(Sommet s1, Sommet s2, ArrayList<Sommet> l_parents) {
        ArrayList<Sommet> chemin = new ArrayList<Sommet>();

        // Si s2 n'est pas le départ et n'a pas de parent, il n'est pas atteignable
        if (s1.getId() != s2.getId() && l_parents.get(s2.getId()) == null) {
            return chemin; // retourne la liste vide
        }

        // On remonte de s2 jusqu'à s1 en suivant les parents
        Sommet courant = s2;
        while (courant != null) {
            chemin.add(courant);
            courant = l_parents.get(courant.getId());
        }

        // On remet le chemin dans l'ordre (de s1 vers s2)
        Collections.reverse(chemin);
        return chemin;
    }


}
