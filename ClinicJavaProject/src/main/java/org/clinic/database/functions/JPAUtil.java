package org.clinic.database.functions;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.ProviderUtil;
import java.util.Map;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory emf;
    private static PersistenceProvider provider ;
    private static Map properties;
/// POLE BITWY
    public static EntityManagerFactory getEntityManagerFactory() {

        if (emf == null) {
            emf = provider.createEntityManagerFactory("default",null);
        }
        return emf;
    }

    public static void shutDown() {
        if (emf != null) {
            emf.close();
        }
    }
}
