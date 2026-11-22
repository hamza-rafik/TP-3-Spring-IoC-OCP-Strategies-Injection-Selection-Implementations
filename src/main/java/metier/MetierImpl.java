package metier;

import dao.IDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Implémentation de la logique métier respectant strictement le principe OCP.
 * Cette classe ne doit JAMAIS être modifiée pour changer de source de données.
 */
@Primary
@Service
public class MetierImpl implements IMetier {

    private final IDao dao;

    /**
     * Injection par constructeur (recommandée par Spring pour l'immuabilité).
     * @param dao La dépendance IDao résolue par le conteneur IoC.
     */
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @PostConstruct
    public void init() {
        System.out.println("[TRACE] Implémentation DAO injectée : " + dao.getClass().getSimpleName());
    }

    @Override
    public double calcul() {
        double valeur = dao.getValue();
        // Logique métier : double la valeur
        double resultat = valeur * 2.0;
        System.out.println("[METIER] Calcul effectué (" + valeur + " * 2.0) = " + resultat);
        return resultat;
    }

    public IDao getDao() {
        return dao;
    }
}
