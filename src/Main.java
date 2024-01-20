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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pfFileName), "UTF-8"));
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
     * @return Un objet Experimentation qui contient le passage de boucle, le nombre de comparaisons et l'indice si l'étudiant est trouvé.
     *         Retourne null si l'étudiant n'est pas trouvé.
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
            if(pfTNPEtu.lesElements[i].nom.equals(pfNom)){ // Si le nom est = au pfNom
                if (pfTNPEtu.lesElements[i].prenom.equals(pfPrenom)) { //Si le prenom est = au pfprenom
                    comparaisons += 2; // Incrémentation du nombre de comparaisons
                    passageBoucle++;    // Incrémentation du passage de boucle
                    retour = new Experimentation(pfTNPEtu.lesElements[i], passageBoucle, comparaisons, i);
                }
            }
            comparaisons++;
            passageBoucle++;
        }

        // Mise à jour du nombre total de comparaisons et de passages de boucle
        if (retour != null) {
            retour.comparaisons=comparaisons;
            retour.passageBoucle=passageBoucle;
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
     * @return Un objet Experimentation qui contient le passage de boucle, le nombre de comparaisons et l'indice si l'étudiant est trouvé.
     *         Retourne null si l'étudiant n'est pas trouvé.
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
                    return new Experimentation(pfTNPEtu.lesElements[i], passageBoucle, comparaisons,i);
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
     * @return Un objet Experimentation qui contient le passage de boucle, le nombre de comparaisons et l'indice si l'étudiant est trouvé.
     *         Retourne null si l'étudiant n'est pas trouvé.
     */
    public static Experimentation rechercheDichotomie(TNP pfTNPEtu, String pfPrenom, String pfNom){
        int passageBoucle=0;
        int comparaisons=0;
        int indice= pfTNPEtu.nbElt/2;
        int borneInf= 0;
        int borneSup= pfTNPEtu.nbElt;

        while (borneSup!=borneInf && passageBoucle<pfTNPEtu.nbElt/2){
            if (pfNom.compareTo(pfTNPEtu.lesElements[indice].nom)==0){
                if (pfPrenom.compareTo(pfTNPEtu.lesElements[indice].prenom)==0){
                    passageBoucle++;
                    comparaisons++;
                    return new Experimentation(pfTNPEtu.lesElements[indice],passageBoucle,comparaisons,indice);
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

    /** créer un TNP
     * @return le TNP crée
     */
    public static TNP creerTNPTest() {
        TNP tnpTest = new TNP(200);
        try {
            // appel de la fonction de lecture du fichier avec le nom du fichier et le TNP
            getListe("listenomssansaccent.csv", tnpTest);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return tnpTest;
    }

    public static TNP creerTNPTest2() {
        TNP tnpTest = new TNP(1500);
        try {
            // appel de la fonction de lecture du fichier avec le nom du fichier et le TNP
            getListe("listenoms500 .csv", tnpTest);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return tnpTest;
    }

    /** créer un TNP
     * @return le TNP crée
     */
    public static TNP creerTNPTest3() {
        TNP tnpTest = new TNP(10000);
        try {
            // appel de la fonction de lecture du fichier avec le nom du fichier et le TNP
            getListe("listenomsXL_10k.csv", tnpTest);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return tnpTest;
    }

    /** créer un TNP
     * @return le TNP crée
     */
    public static TNP creerTNPTest4() {
        TNP tnpTest = new TNP(100000);
        try {
            // appel de la fonction de lecture du fichier avec le nom du fichier et le TNP
            getListe("listenomsXXL_100k.csv", tnpTest);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return tnpTest;
    }

    public static void main(String[] args){
        TNP liste = creerTNPTest();
        trierEtu(liste);
        System.out.println("\nListe de 199 Etudiants :\n");
        //Recherche Sans Rupture :
        testSansRupture(liste);
        //Recherche Avec Rupture :
        testAvecRupture(liste);
        //Recherche par Dichotomie :
        testDichotomique(liste);

        TNP liste4 = creerTNPTest2();
        trierEtu(liste4);

        System.out.println("\nListe de 1500 Etudiants :\n");
        //Recherche Simple :
        testSansRupture(liste4);
        //Recherche Avec Rupture :
        testAvecRupture(liste4);
        //Recherche par Dichotomie :
        testDichotomique(liste4);

        TNP liste2 = creerTNPTest3();
        trierEtu(liste2);

        System.out.println("\nListe de 10000 Etudiants :\n");
        //Recherche Sans Rupture :
        testSansRupture(liste2);
        //Recherche Avec Rupture :
        testAvecRupture(liste2);
        //Recherche par Dichotomie :
        testDichotomique(liste2);

        TNP liste3 = creerTNPTest4();
        trierEtu(liste3);

        System.out.println("\nListe de 100000 Etudiants :\n");
        //Recherche Sans Rupture :
        testSansRupture(liste3);
        //Recherche Avec Rupture :
        testAvecRupture(liste3);
        //Recherche par Dichotomie :
        testDichotomique(liste3);

    }

    /**
     * Effectue des tests de recherche sans rupture sur une liste d'étudiants.
     *
     * @param pfListe IN La liste d'étudiants sur laquelle effectuer les tests.
     */
    public static void testSansRupture(TNP pfListe){
        int nbEtu = nbEtudiant(pfListe);
        int milieu= nbEtu/2 + 34;
        System.out.println("Recherche Sans Rupture pour une liste de " +nbEtu+" Etudiants :\n");
        System.out.println(rechercheSansRupture(pfListe,pfListe.lesElements[1].prenom,pfListe.lesElements[1].nom)); // Etudiant en début de liste
        System.out.println(rechercheSansRupture(pfListe,pfListe.lesElements[milieu].prenom,pfListe.lesElements[milieu].nom)); // Etudiant vers le milieu de la liste
        System.out.println(rechercheSansRupture(pfListe,pfListe.lesElements[nbEtu-1].prenom,pfListe.lesElements[nbEtu-1].nom)); // Etudiant en fin de liste

    }

    /**
     * Effectue des tests de recherche avec rupture sur une liste d'étudiants.
     *
     * @param pfListe IN La liste d'étudiants sur laquelle effectuer les tests.
     */
    public static void testAvecRupture(TNP pfListe){
        int nbEtu = nbEtudiant(pfListe);
        int milieu= nbEtu/2 + 34;

        System.out.println("\nRecherche Avec Rupture pour une liste de " +nbEtu+" Etudiants :\n");
        System.out.println(rechercheAvecRupture(pfListe,pfListe.lesElements[1].prenom,pfListe.lesElements[1].nom)); // Etudiant en début de liste
        System.out.println(rechercheAvecRupture(pfListe,pfListe.lesElements[milieu].prenom,pfListe.lesElements[milieu].nom)); // Etudiant vers le milieu de la liste
        System.out.println(rechercheAvecRupture(pfListe,pfListe.lesElements[nbEtu-1].prenom,pfListe.lesElements[nbEtu-1].nom)); // Etudiant en fin de liste

    }

    /**
     * Effectue des tests de recherche par dichotomie sur une liste d'étudiants.
     *
     * @param pfListe IN La liste d'étudiants sur laquelle effectuer les tests.
     */
    public static void testDichotomique(TNP pfListe){
        int nbEtu = nbEtudiant(pfListe);
        int milieu= nbEtu/2 + 34; // pour ne pas tomber pile a la moitier pour faire travailler la recherche dichotomique

        System.out.println("\nRecherche par Dichotomie pour une liste de " +nbEtu+"  Etudiants :\n");  // Etudiant en début de liste
        System.out.println(rechercheDichotomie(pfListe,pfListe.lesElements[1].prenom,pfListe.lesElements[1].nom));// Etudiant vers le milieu de la liste
        System.out.println(rechercheDichotomie(pfListe,pfListe.lesElements[milieu].prenom,pfListe.lesElements[milieu].nom));// Etudiant vers le milieu de la liste
        System.out.println(rechercheDichotomie(pfListe,pfListe.lesElements[nbEtu-1].prenom,pfListe.lesElements[nbEtu-1].nom));// Etudiant en fin de liste
    }
}