/**
 * Indiquer le ou les numeros de TP et d'exercice.
 *
 * @author (votre nom)
 */
/**
 * Indiquer le ou les numeros de TP et d'exercice.
 *
 * @author (votre nom)
 */
public class Etudiant{
    String nom;
    String prenom;
    int groupe;
    String groupeTP;

    Etudiant(){
        this.nom = "inconnu";
        this.prenom = "inconnu";
        this.groupe = 0;
        this.groupeTP= "inconnu";
    }

    Etudiant(String pfNom,String pfPrenom,int pfGroupe, String pfGroupeTP){
        this.nom = pfNom;
        this.prenom = pfPrenom;
        this.groupe = pfGroupe;
        this.groupeTP= pfGroupeTP;
    }
}