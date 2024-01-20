/**
 * Indiquer le ou les numeros de TP et d'exercice.
 *
 * @author (AUSSENAC Thomas, Loic)
 */
public class Etudiant
{
    String nom;
    String prenom;
    int groupe;
    String groupeTP;

    /**
     * Initialise un objet Etudiant avec des valeurs par défaut.
     * Le nom et le prénom sont définis comme "inconnu", le groupe est 0
     * et le groupe de TP est défini comme "inconnu".
     */
    Etudiant(){
        this.nom = "inconnu";
        this.prenom = "inconnu";
        this.groupe = 0;
        this.groupeTP= "inconnu";
    }

    /**
     * Initialise un objet Etudiant avec les valeurs spécifiées.
     *
     * @param pfNom IN Nom de l'étudiant.
     * @param pfPrenom IN Prénom de l'étudiant.
     * @param pfGroupe IN Numéro du groupe auquel l'étudiant appartient.
     * @param pfGroupeTP IN Nom du groupe de TP de l'étudiant.
     */
    Etudiant(String pfNom,String pfPrenom,int pfGroupe, String pfGroupeTP){
        this.nom = pfNom;
        this.prenom = pfPrenom;
        this.groupe = pfGroupe;
        this.groupeTP= pfGroupeTP;
    }
}