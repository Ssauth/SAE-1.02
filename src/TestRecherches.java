import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRecherches {

    private TNP liste = new TNP(250);

    @BeforeAll
    public void setUp(){
        try {
            Main.getListe("listenomssansaccent.csv", liste);
            Main.trierEtu(liste);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }




    @Test
    public void testRechercheSansRupture(){
        for (Etudiant e : liste.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheSansRupture(liste,e.prenom,e.nom).etudiant,e);
    }

    @Test
    public void testRechercheAvecRupture(){
        for (Etudiant e : liste.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheSansRupture(liste,e.prenom,e.nom).etudiant,e);
    }

    @Test
    public void testRechercheDichotomie(){
        for (Etudiant e : liste.lesElements)
            if (e!=null)
                assertEquals(Main.rechercheSansRupture(liste,e.prenom,e.nom).etudiant,e);
    }



}
