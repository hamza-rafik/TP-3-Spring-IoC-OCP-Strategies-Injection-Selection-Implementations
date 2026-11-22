package metier;

/**
 * Contrat de la couche métier.
 */
public interface IMetier {

    /**
     * Effectue le traitement métier basé sur la source de données injectée.
     * @return Le résultat du calcul.
     */
    double calcul();
}
