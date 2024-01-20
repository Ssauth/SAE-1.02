public class Experimentation {

    Etudiant etudiant;
    int passageBoucle;
    int comparaisons;
    int indice;

    /**
     * Constructeur de la classe Experimentation.
     *
     * @param etudiant IN L'étudiant trouvé lors de la recherche.
     * @param passageBoucle IN Le nombre de passages dans la boucle lors de la recherche.
     * @param comparaisons IN Le nombre total de comparaisons effectuées lors de la recherche.
     * @param indice IN L'indice de l'étudiant trouvé dans le TNP.
     */
    Experimentation(Etudiant etudiant, int passageBoucle, int comparaisons, int indice){
        this.etudiant=etudiant;
        this.passageBoucle=passageBoucle;
        this.comparaisons=comparaisons;
        this.indice=indice;
    }


    /**
     * Retourne une chaine de cractere de l'objet Experimentation.
     *
     * @return Une chaîne de caractères indiquant le nombre de passages dans la boucle,
     *         le nombre total de comparaisons, l'étudiant trouvé et son indice dans le TNP.
     */
    public String toString(){
        return passageBoucle+" passage dans la boucle et "+comparaisons+" comparaisons" + ", l'étudiant "+etudiant.prenom + " " + etudiant.nom +" est à l'indice : "+indice + " du TNP";
    }








}
