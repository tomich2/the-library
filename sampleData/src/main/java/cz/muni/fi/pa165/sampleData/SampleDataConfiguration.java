package cz.muni.fi.pa165.sampleData;

import cz.fi.muni.pa165.ServiceConfig;
import cz.fi.muni.pa165.library.persistance.exceptions.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;

/**
 * @ author mcada
 */
@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackageClasses = SampleDataLoader.class)
public class SampleDataConfiguration {
	final static Logger log = LoggerFactory.getLogger(SampleDataConfiguration.class);

	@Inject
	private SampleDataLoader loader;

	@PostConstruct
	public void dataLoading() throws IOException, ParseException, DataAccessException {
		log.debug("dataLoading()");
		loader.loadData();
	}
}
