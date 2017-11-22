package cz.fi.muni.pa165.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.fi.muni.pa165.library.persistance.config.PersistenceApplicationContext;
/**
 *
 * @author tchomo
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan({ "cz.muni.fi.pa165.service", "cz.muni.fi.pa165.facade" })
public class ServiceConfiguration {
    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }
}