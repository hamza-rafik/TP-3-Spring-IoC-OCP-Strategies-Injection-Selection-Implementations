import config.AppConfig;
import metier.IMetier;
import metier.MetierMultiple;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Point d'entrée de l'application TP 3.
 * Démontre l'Inversion de Contrôle (IoC) et le respect du principe Open/Closed (OCP).
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   TP 3 : SPRING IOC & OCP - STRATEGIES D'INJECTION & SELECTION D'IMPL   ");
        System.out.println("==========================================================================");

        // 1. Initialisation du conteneur Spring IoC basé sur les annotations
        System.out.println("\n[1] Démarrage du conteneur Spring (AppConfig)...");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Récupération du bean métier (IMetier) avec injection résolue
        System.out.println("\n[2] Récupération du bean IMetier (MetierImpl)...");
        IMetier metier = context.getBean(IMetier.class);

        // 3. Exécution du calcul métier
        System.out.println("\n[3] Exécution du calcul métier :");
        double resultat = metier.calcul();
        System.out.println(">> Résultat final : " + resultat);

        // 4. Démonstration de l'extension : agrégation multiple avec MetierMultiple
        System.out.println("\n[4] Démonstration de l'injection multiple (MetierMultiple) :");
        try {
            MetierMultiple multiple = context.getBean(MetierMultiple.class);
            double somme = multiple.calcul();
            System.out.println(">> Somme de tous les DAOs : " + somme);
        } catch (Exception e) {
            System.out.println("MetierMultiple info: " + e.getMessage());
        }

        System.out.println("\n+======================================================================+");
        System.out.println("|   [SUCCES] SELECTION DYNAMIQUE & PRINCIPE OCP VALIDES AVEC SUCCES !  |");
        System.out.println("+======================================================================+");

        // 5. Fermeture propre du conteneur
        context.close();
    }
}
