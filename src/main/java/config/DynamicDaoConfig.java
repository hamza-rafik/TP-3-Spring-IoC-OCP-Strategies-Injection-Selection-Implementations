package config;

import dao.DaoApi;
import dao.DaoFile;
import dao.DaoImpl;
import dao.DaoImpl2;
import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Stratégie C : Sélection dynamique de l'implémentation IDao par propriété externe.
 * Respecte le principe OCP car le changement d'implémentation s'opère dans app.properties
 * sans modifier aucune classe métier ni DAO.
 */
@Configuration
public class DynamicDaoConfig {

    @Value("${dao.target:dao}")
    private String daoTarget;

    @Bean
    @Primary
    public IDao dynamicDao() {
        System.out.println("[CONFIG] Sélection dynamique du DAO cible : '" + daoTarget + "'");
        switch (daoTarget.trim()) {
            case "dao":
                return new DaoImpl();
            case "dao2":
                return new DaoImpl2();
            case "daoFile":
                return new DaoFile();
            case "daoApi":
                return new DaoApi();
            default:
                throw new IllegalArgumentException("Cible DAO inconnue spécifiée dans app.properties : " + daoTarget);
        }
    }
}
