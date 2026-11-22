package test;

import config.AppConfig;
import dao.*;
import metier.IMetier;
import metier.MetierImpl;
import metier.MetierMultiple;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OcpSelectionTest {

    @Test
    public void testDaoImplParDefaut() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        IMetier metier = context.getBean(IMetier.class);

        // Si dao.target=dao (100.0), le résultat doit être 200.0 (100.0 * 2.0)
        assertEquals(200.0, metier.calcul(), 0.001);

        context.close();
    }

    @Test
    public void testRespectPrincipeOCPAvecDifferentsDAOs() {
        // Validation que MetierImpl fonctionne avec n'importe quelle implémentation IDao sans modification
        IMetier metierDao2 = new MetierImpl(new DaoImpl2());
        assertEquals(300.0, metierDao2.calcul(), 0.001); // 150.0 * 2.0

        IMetier metierDaoFile = new MetierImpl(new DaoFile());
        assertEquals(360.0, metierDaoFile.calcul(), 0.001); // 180.0 * 2.0

        IMetier metierDaoApi = new MetierImpl(new DaoApi());
        assertEquals(440.0, metierDaoApi.calcul(), 0.001); // 220.0 * 2.0
    }

    @Test
    public void testMetierMultipleAgregation() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        MetierMultiple metierMultiple = context.getBean(MetierMultiple.class);
        assertNotNull(metierMultiple);

        // Somme des DAOs présents dans le contexte
        double somme = metierMultiple.calcul();
        // dao(100) + dao2(150) + daoFile(180) + daoApi(220) + dynamicDao(100) = 750
        assertEquals(750.0, somme, 0.001);

        context.close();
    }
}
