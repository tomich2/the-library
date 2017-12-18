package cz.fi.muni.pa165.rest.config;

import cz.fi.muni.pa165.ServiceConfig;
import cz.fi.muni.pa165.sampleData.SampleDataConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring web context configuration.
 * @author xtlamich
 */

@EnableWebMvc
@Configuration
@Import({ServiceConfig.class, SampleDataConfiguration.class})
@ComponentScan(basePackages = {"cz.fi.muni.pa165.rest.controllers"})
public class RootWebContext  extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllowOriginInterceptor());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
