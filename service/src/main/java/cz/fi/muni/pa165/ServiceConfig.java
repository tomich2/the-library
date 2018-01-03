package cz.fi.muni.pa165;

import cz.fi.muni.pa165.facade.LoanFacadeImpl;
import cz.fi.muni.pa165.library.persistance.config.PersistenceApplicationContext;
import cz.fi.muni.pa165.service.LoanServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mcada
 */
@Configuration
@ComponentScan(basePackageClasses = {LoanServiceImpl.class, LoanFacadeImpl.class},
        basePackages = "cz.fi.muni.pa165.service")
@Import(PersistenceApplicationContext.class)
public class ServiceConfig {
    @Bean
    public Mapper dozer() {
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozerJdk8Converters.xml");

        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }
}
