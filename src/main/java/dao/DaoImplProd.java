package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Implémentation conditionnée par le profil d'environnement Spring "prod".
 */
@Component("daoProd")
@Profile("prod")
public class DaoImplProd implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO] Lecture spécifique Profil PROD");
        return 100.0;
    }
}
