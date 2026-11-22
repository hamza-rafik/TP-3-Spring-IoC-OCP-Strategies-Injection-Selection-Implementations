package dao;

/**
 * Contrat fondamental d'accès aux données.
 * Point de couplage faible entre le métier et les sources de données (Principe DIP & OCP).
 */
public interface IDao {

    /**
     * Récupère une valeur numérique depuis la source de données.
     * @return La valeur récupérée sous forme de double.
     */
    double getValue();
}
