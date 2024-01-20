public class Experimentation {

    Etudiant etudiant;
    int passageBoucle;
    int comparaisons;

    Experimentation(Etudiant etudiant, int passageBoucle, int comparaisons){
        this.etudiant=etudiant;
        this.passageBoucle=passageBoucle;
        this.comparaisons=comparaisons;
    }

    public String toString(){
        return passageBoucle+" passage dans la boucle et "+comparaisons+" comparaisons";
    }








}
