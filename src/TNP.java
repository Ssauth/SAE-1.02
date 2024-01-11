public class TNP {
    int nbElt ;
    Etudiant[] lesElements ;

    /** Crée un TNP de taille pfnbMax
     * @param pfnbMax IN taille max
     */
    TNP(int pfnbMax) {
        this.nbElt = 0 ;
        this.lesElements = new Etudiant[pfnbMax] ;
    }
}