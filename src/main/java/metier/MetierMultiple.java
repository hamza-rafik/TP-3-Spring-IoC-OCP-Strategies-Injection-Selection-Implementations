package metier;

import dao.IDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Extension avancée : Injection d'une collection et d'une Map de tous les beans IDao.
 * Démontre l'agrégation de multiples implémentations sans couplage.
 */
@Service
public class MetierMultiple implements IMetier {

    private final List<IDao> daos;
    private final Map<String, IDao> daoMap;

    public MetierMultiple(List<IDao> daos, Map<String, IDao> daoMap) {
        this.daos = daos;
        this.daoMap = daoMap;
    }

    @Override
    public double calcul() {
        // Somme de toutes les valeurs issues des différentes sources
        double somme = daos.stream().mapToDouble(IDao::getValue).sum();
        System.out.println("[METIER-MULTIPLE] Agrégation de " + daos.size() + " sources de données. Somme = " + somme);
        return somme;
    }

    public List<IDao> getDaos() {
        return daos;
    }

    public Map<String, IDao> getDaoMap() {
        return daoMap;
    }
}
