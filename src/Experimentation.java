public class Experimentation {

    Etudiant etudiant;
    int passageBoucle;
    int comparaisons;
    int indice;

    Experimentation(Etudiant etudiant, int passageBoucle, int comparaisons, int indice){
        this.etudiant=etudiant;
        this.passageBoucle=passageBoucle;
        this.comparaisons=comparaisons;
        this.indice=indice;
    }

    public String toString(){
        return "Trouvé à l'indice "+indice+" avec "+passageBoucle+" passage dans la boucle et "+comparaisons+" comparaisons.";
    }








}
