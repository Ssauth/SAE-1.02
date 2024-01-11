public class Main{

    public static void trierEtu(TNP pfTNPEtu) {
        int nbMembres = pfTNPEtu.nbElt;
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






    public static void main(String[] args){

    }

}
