import java.io.*;
import java.util.Scanner;

public class Main{

    /** Donne le nombre d’étudiants de la liste pfListe
     *  @param pfListe IN TNP contenant la liste d'étudiants nom, prenom
     *  @return le nombre d’étudiants de la liste
     **/
    public static int nbEtudiant(TNP pfListe){
        return pfListe.nbElt; //Nombre d'éléments effectif du TNP
    }

    /**
     * Charge la liste des étudiants depuis un fichier spécifié dans un TNP donné.
     *
     * @param pfFileName IN Le chemin du fichier contenant la liste des étudiants.
     * @param pfListe OUT Le TNP dans lequel charger la liste des étudiants.
     * @throws FileNotFoundException Si le fichier spécifié n'est pas trouvé.
     */
    public static void getListe(String pfFileName,TNP pfListe) //change return value
            throws FileNotFoundException{

        // Ouvre un fichier et compte le nombre  de lignes du fichier.
        //   Ce nombre de lignes correspond au nombre d'étudiants
        BufferedReader read = new BufferedReader(new FileReader(pfFileName));

        // le try catch est la pour recuperer des erreurs eventuelles de lecture
        // dans le fichier. Si une erreur se produit, ce sont les instructions
        // du catch qui seront executees (sera vu en semaine 46).
        try {
            while (read.readLine() != null) {
                pfListe.nbElt++;
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("nombre de lignes/etudiants : " + pfListe.nbElt);

        // lecture du fichier pour récupérer les noms et prénoms
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pfFileName));
            int cpt = 0; // numero de l'etudiant en lecture
            System.out.println("nombre de lignes : " + pfListe.nbElt);

            // Boucle qui parcourt toute les lignes jusqu'à trouver une ligne vide
            while ((line = reader.readLine()) != null) {

                // Separer tout les token avec un virgule
                String[] token = line.split(",");

                //Ajout des token dans le TNP token0 = nom, token1 = prénom, token2 = groupe, token3 = groupeTP
                pfListe.lesElements[cpt] = new Etudiant(token[0],token[1],Integer.parseInt(token[2]),token[3]);

                cpt ++;
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Affiche les informations d'un étudiant.
     *
     * @param pfEtudiant IN L'objet Etudiant dont les informations doivent être affichées.
     */
    public static void AfficheEtu(Etudiant pfEtudiant){
        System.out.println("Nom : "+pfEtudiant.nom + "\nPrénom : "+pfEtudiant.prenom + "\nGroupe : "+ pfEtudiant.groupe + "\nGroupeTP : "+ pfEtudiant.groupeTP+"\n");

    }

    /**
     * Trie un tableau d'étudiants par ordre croissant des noms et, en cas d'égalité, par ordre croissant des prénoms.
     * Utilise le tri par sélection.
     *
     * @param pfTNPEtu IN/OUT Le TNP d'étudiants à trier.
     */
    public static void trierEtu(TNP pfTNPEtu) {
        int nbMembres = nbEtudiant(pfTNPEtu);
        for (int i = 0; i < nbMembres - 1; i++) {
            int indiceMin = i;
            for (int j = i + 1; j < nbMembres; j++)
                if (pfTNPEtu.lesElements[j].nom.compareTo(pfTNPEtu.lesElements[indiceMin].nom) < 0) {
                    indiceMin = j;
                } else if (pfTNPEtu.lesElements[j].nom.compareTo(pfTNPEtu.lesElements[indiceMin].nom) == 0)
                    if (pfTNPEtu.lesElements[j].prenom.compareTo(pfTNPEtu.lesElements[indiceMin].prenom) < 0)
                        indiceMin = j;
            Etudiant temp = pfTNPEtu.lesElements[i];
            pfTNPEtu.lesElements[i] = pfTNPEtu.lesElements[indiceMin];
            pfTNPEtu.lesElements[indiceMin] = temp;
        }
    }

    /**
     * Recherche un étudiant dans un tableau par son prénom et nom sans interruption de boucle.
     * Affiche le nombre d'éléments parcourus.
     *
     * @param pfTNPEtu IN Le tableau TNP d'étudiants dans lequel effectuer la recherche.
     * @param pfPrenom IN Le prénom de l'étudiant recherché.
     * @param pfNom IN Le nom de l'étudiant recherché.
     * @return L'indice de l'étudiant dans le tableau s'il est trouvé, sinon retourne -1.
     */
    public static Experimentation rechercheSansRupture(TNP pfTNPEtu, String pfPrenom, String pfNom){
        int passageBoucle=0;
        int comparaisons=0;
        Experimentation retour=null;
        for(int i=0;i<pfTNPEtu.nbElt;i++){
            /*Si la première condition est fausse, le programme ne testera pas le seconde. Cela permet de ne pas augmenter la complexité, tout en réduisant la taille de la fonction.
            C'est donc la meilleure solution, mais elle ne permet pas de bien comptabiliser le nombre de comparaisons.
            if((pfTNPEtu.lesElements[i].nom.equals(pfNom)) && (pfTNPEtu.lesElements[i].prenom.equals(pfPrenom))){
                passageBoucle++;
                return new Experimentation(pfTNPEtu.lesElements[indice],passageBoucle,comparaisons);
            }
             */
            if(pfTNPEtu.lesElements[i].nom.equals(pfNom)){
                if (pfTNPEtu.lesElements[i].prenom.equals(pfPrenom)) {
                    comparaisons+=2;
                    passageBoucle++;
                    retour= new Experimentation(pfTNPEtu.lesElements[i], passageBoucle, comparaisons);
                }
            }
            comparaisons++;
            passageBoucle++;
        }
        return retour;
    }

    /**
     * Recherche un étudiant dans un tableau par son prénom et nom avec une rupture de boucle dès que l'étudiant a été trouvé.
     * Affiche le nombre d'éléments parcourus.
     *
     * @param pfTNPEtu IN Le TNP d'étudiants dans lequel effectuer la recherche.
     * @param pfPrenom IN Le prénom de l'étudiant recherché.
     * @param pfNom IN Le nom de l'étudiant recherché.
     * @return L'indice de l'étudiant dans le tableau s'il est trouvé, sinon retourne -1.
     */
    public static Experimentation rechercheAvecRupture(TNP pfTNPEtu, String pfPrenom, String pfNom){
        int passageBoucle=0;
        int comparaisons=0;
        for(int i=0;i<pfTNPEtu.nbElt;i++){
            /*Si la première condition est fausse, le programme ne testera pas le seconde. Cela permet de ne pas augmenter la complexité, tout en réduisant la taille de la fonction.
            C'est donc la meilleure solution, mais elle ne permet pas de bien comptabiliser le nombre de comparaisons.
            if((pfTNPEtu.lesElements[i].nom.equals(pfNom)) && (pfTNPEtu.lesElements[i].prenom.equals(pfPrenom))){
                passageBoucle++;
                return new Experimentation(pfTNPEtu.lesElements[indice],passageBoucle,comparaisons);
            }
             */
            if(pfTNPEtu.lesElements[i].nom.equals(pfNom)){
                if (pfTNPEtu.lesElements[i].prenom.equals(pfPrenom)) {
                    comparaisons+=2;
                    passageBoucle++;
                    return new Experimentation(pfTNPEtu.lesElements[i], passageBoucle, comparaisons);
                }
            }
            comparaisons++;
            passageBoucle++;
        }
        return null;
    }

    /**
     * Recherche un étudiant dans un tableau trié par ordre alphabétique des noms et, en cas d'égalité, des prénoms,
     * en utilisant l'algorithme de recherche par dichotomie.
     *
     * @param pfTNPEtu IN Le TNP d'étudiants trié dans lequel effectuer la recherche.
     * @param pfPrenom IN Le prénom de l'étudiant recherché.
     * @param pfNom IN Le nom de l'étudiant recherché.
     * @return L'indice de l'étudiant dans le tableau s'il est trouvé, sinon retourne -1.
     */
    public static Experimentation rechercheDichotomie(TNP pfTNPEtu, String pfPrenom, String pfNom){
        int passageBoucle=0;
        int comparaisons=0;
        int indice= pfTNPEtu.nbElt/2;
        int borneInf= 0;
        int borneSup= pfTNPEtu.nbElt;

        while (borneSup!=borneInf){
            if (pfNom.compareTo(pfTNPEtu.lesElements[indice].nom)==0){
                if (pfPrenom.compareTo(pfTNPEtu.lesElements[indice].prenom)==0){
                    passageBoucle++;
                    comparaisons++;
                    return new Experimentation(pfTNPEtu.lesElements[indice],passageBoucle,comparaisons);
                } else if (pfPrenom.compareTo(pfTNPEtu.lesElements[indice].prenom)>0) {
                    comparaisons+=2;
                    borneInf=indice;
                } else {
                    comparaisons+=2;
                    borneSup=indice;
                }
            } else if (pfNom.compareTo(pfTNPEtu.lesElements[indice].nom)>0) {
                comparaisons++;
                borneInf=indice;
            } else {
                comparaisons++;
                borneSup=indice;
            }
            indice=(borneInf+borneSup)/2;
            passageBoucle++;
        }
        return null;
    }

    public static void main(String[] args){
        TNP liste = new TNP(250);
        try {
            // appel de la fonction de lecture du fichier avec le nom du fichier et le TNP
            getListe("listenomssansaccent.csv", liste);

            // appel de la fonction trier avec le TNP pour trier le TNP
            trierEtu(liste);

            //Afficher les informations de tout les étudiants du TNP
            for(int i=0;i<nbEtudiant(liste);i++){
                AfficheEtu(liste.lesElements[i]);

            }

            System.out.println("Il y a : " + nbEtudiant(liste) + " personnes.");
            System.out.println(rechercheSansRupture(liste,"Camille","Zole"));
            System.out.println(rechercheAvecRupture(liste,"Barack","Afritt"));
            System.out.println(rechercheDichotomie(liste,"Camille","Zole"));
            System.out.println(rechercheDichotomie(liste,"Barack","Afritt"));

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}

