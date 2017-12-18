package cz.fi.muni.pa165;

import cz.fi.muni.pa165.library.persistance.config.PersistenceApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mcada
 */
@Configuration
@ComponentScan({"cz.fi.muni.pa165.service", "cz.fi.muni.pa165.facade"})
@Import(PersistenceApplicationContext.class)
@EnableTransactionManagement
public class ServiceConfig {
}
