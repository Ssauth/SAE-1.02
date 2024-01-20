import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRecherches {

    private TNP liste1 = new TNP(250);
    private TNP liste2 = new TNP(250);
    private TNP liste3 = new TNP(10000);
    private TNP liste4 = new TNP(100000);

    @BeforeAll
    public void setUp(){
        try {
            Main.getListe("listenomssansaccent.csv", liste1);
            Main.trierEtu(liste1);
            Main.getListe("listenomsavecaccent.csv", liste2);
            Main.trierEtu(liste2);
            Main.getListe("listenomsXL_10k.csv", liste3);
            Main.trierEtu(liste3);
            Main.getListe("listenomsXXL_100k.csv", liste4);
            Main.trierEtu(liste4);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }




    @Test
    public void testRechercheSansRupture(){
        for (Etudiant e : liste1.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheSansRupture(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste2.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheSansRupture(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste3.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheSansRupture(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste4.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheSansRupture(liste1,e.prenom,e.nom).etudiant,e);

    }

    @Test
    public void testRechercheAvecRupture(){
        for (Etudiant e : liste1.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheAvecRupture(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste2.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheAvecRupture(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste3.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheAvecRupture(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste4.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheAvecRupture(liste1,e.prenom,e.nom).etudiant,e);
    }

    @Test
    public void testRechercheDichotomie(){
        for (Etudiant e : liste1.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheDichotomie(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste2.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheDichotomie(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste3.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheDichotomie(liste1,e.prenom,e.nom).etudiant,e);
        for (Etudiant e : liste4.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheDichotomie(liste1,e.prenom,e.nom).etudiant,e);
    }

    @Test
    public void testEtuNotExist(){
        for (Etudiant e : liste4.lesElements)
            if (e!=null) {
                assertEquals(Main.rechercheSansRupture(liste1, "Jean", "Michealle").etudiant, null);
                assertEquals(Main.rechercheSansRupture(liste1, null, null).etudiant, null);
                assertEquals(Main.rechercheAvecRupture(liste1,"Jean", "Michealle").etudiant,null);
                assertEquals(Main.rechercheAvecRupture(liste1,null, null).etudiant,null);
                assertEquals(Main.rechercheDichotomie(liste1,"Jean", "Michealle").etudiant,null);
                assertEquals(Main.rechercheDichotomie(liste1,null, null).etudiant,null);
            }
    }



}
