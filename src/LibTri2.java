public class LibTri2 {




    /** affiche un TNP
     * @param pfTNP  IN la liste des éléments
     */
    public static void afficherTNP(TNP pfTNP)  {
        System.out.print("[") ;
        for (int i = 0; i < pfTNP.nbElt ; i = i + 1){
            System.out.print(pfTNP.lesElements[i]) ;
            if (i < (pfTNP.nbElt - 1)) { System.out.print(",") ; }
        }
        System.out.print("]") ;
    }

    /** tri dans l'ordre croissant
     * @param pfTNP  IN/OUT la liste des éléments
     * @param pfPrintSP  IN vrai la trace complète avec toutes les it du for j faux que le résumé de la  trace
     * @return le message de trace
     */
    public static String triSimple(TNP pfTNP, boolean pfPrintSP)    {

        String trace ="";

        // *** Trace d'exécution ***
        // Création des variables pour évaluer la complexité de trimax
        int iefori = 0; // compteur du nombre d'itérations de la boucle i
        int ieforjTotal=0;// compteur du nombre total d'itérations de la boucle j (pour toutes les it de la boucle i
        int ieforj  ; // compteur du nombre d'itérations de la boucle j

        for (int i = 0; i < pfTNP.nbElt -1 ; i = i + 1) {
            // *** Trace d'exécution ***
            iefori ++;
            ieforj =0 ; // compteur du nombre d'itérations de la boucle j
            for (int j = i+1; j < pfTNP.nbElt ; j = j + 1){
                ieforj++;
                if( pfTNP.lesElements[i]> pfTNP.lesElements[j]){
                    double echange = pfTNP.lesElements[i] ;
                    pfTNP.lesElements[i] = pfTNP.lesElements[j] ;
                    pfTNP.lesElements[j] = echange ;
                }

            }
            // *** Trace d'exécution ***
            // Eval de la complexité de la boucle interne :
            ieforjTotal =ieforjTotal+ieforj;
            if (pfPrintSP) trace = trace +"\t\tnombre d'itérations dans la boucle j " +ieforj+"\n";

        }


        // *** Trace d'exécution ***
        // Affichage des résultats
        trace = trace + "nombre de passage dans la boucle de tri i : "+iefori+"\n";
        trace = trace + "nombre total de passage dans la boucle de j : "+ieforjTotal;
        return (trace);

    }

    /** créer un TNP
     * @return le TNP crée
     */
    public static TNP creerTNPTest() {
        TNP tnpTest = new TNP(7) ;
        tnpTest.lesElements[0] = 1.0 ;
        tnpTest.lesElements[1] = 2.0 ;
        tnpTest.lesElements[2] = 3.0 ;
        tnpTest.lesElements[3] = 4.0 ;
        tnpTest.lesElements[4] = 5.0 ;
        tnpTest.lesElements[5] = 7.3 ;
        tnpTest.lesElements[6] = 5.2 ;
        tnpTest.nbElt = 7 ;
        return tnpTest;
    }

    /** créer un TNP
     * @return le TNP crée
     */
    public static TNP creerTNPTestB() {
        TNP tnpTest = new TNP(7) ;
        tnpTest.lesElements[0] = 7.0 ;
        tnpTest.lesElements[1] = 8.0 ;
        tnpTest.lesElements[2] = 3.0 ;
        tnpTest.lesElements[3] = 9.0 ;
        tnpTest.lesElements[4] = 5.0 ;
        tnpTest.lesElements[5] = 7.3 ;
        tnpTest.lesElements[6] = 5.2 ;
        tnpTest.nbElt = 7 ;
        return tnpTest;
    }

    /** créer un TNP
     * @return le TNP crée
     */
    public static TNP creerTNPTest2() {
        TNP tnpTest = new TNP(50) ;
        double a= 5;
        double b = 10.1;
        for (int i = 0; i<50;i++){
            tnpTest.lesElements[i] = b*i-a ;
        }
        tnpTest.nbElt = 50 ;
        return tnpTest;
    }

    public static void main(String arguments[])   {
        TNP tnp = creerTNPTest();
        boolean detail = false; // trace détaillée false résumé


        System.out.println ("tri de : " + tnp.nbElt + " elements");
        String trace1 = triSimple(tnp,detail);
        System.out.println("Trace 1\n"+trace1);
        //  nombre total de passage dans la boucle de j   :  21  = (7*6/2)

        // Dans tous les cas, pour trier n éléments, le tri  effectue n(n-1)/2 comparaisons. Sa complexité est donc Θ(n2).
        TNP tnpB = creerTNPTestB();


        System.out.println ("tri de : " + tnpB.nbElt + " elements");
        String traceB = triSimple(tnpB,detail);
        System.out.println("Trace B\n"+traceB);
        //  nombre total de passage dans la boucle de j   :  21  = (7*6/2)

        // Dans tous les cas, pour trier n éléments, le tri   effectue n(n-1)/2 comparaisons. Sa complexité est donc Θ(n2).

        TNP tnp2 = creerTNPTest2();

        System.out.println ("tri de : " + tnp2.nbElt + " elements");
        String trace2 = triSimple(tnp2,detail);
        System.out.println("Trace 2\n"+trace2);

        //nombre de comparaisons : nombre total de passage dans la boucle de j   :  1225  = (50*49/2)
        // Dans tous les cas, pour trier n éléments, le tri  effectue n(n-1)/2 comparaisons. Sa complexité est donc Θ(n2).


    }

}