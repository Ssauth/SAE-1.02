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

    public static int rechercheSansRupture(TNP pfTNPEtu, Etudiant pfEtudiant){
        int traceI=0;
        int indice=-1;
        for(int i=0;i<pfTNPEtu.nbElt;i++){
            if(pfTNPEtu.lesElements[i].equals(pfEtudiant))
                indice=i;
            traceI++;
        }
        System.out.println("Nous avons parcouru "+traceI+" éléments de la liste pour trouver l'élément recherché.");
        return indice;
    }
    public static int rechercheAvecRupture(TNP pfTNPEtu, Etudiant pfEtudiant){
        int traceI=0;
        int indice=-1;
        for(int i=0;i<pfTNPEtu.nbElt;i++){
            if(pfTNPEtu.lesElements[i].equals(pfEtudiant)){
                indice=i;
                traceI++;
                break;
            }
            traceI++;
        }
        System.out.println("Nous avons parcouru "+traceI+" éléments de la liste pour trouver l'élément recherché.");
        return indice;
    }
    public static int rechercheDichotomie(TNP pfTNPEtu, Etudiant pfEtudiant){

        return 0;
    }






    public static void main(String[] args){

    }

}
