package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.config.ServiceConfiguration;
import cz.fi.muni.pa165.service.LoanItemService;
import cz.fi.muni.pa165.service.LoanService;
import cz.fi.muni.pa165.service.MemberService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * Beans for mocked services - needed for injection.
 */

@Configuration
@Import(ServiceConfiguration.class)
public class FacadeTestConfiguration {

    @Bean
    @Primary
    public LoanService loanService(){
        return Mockito.mock(LoanService.class);
    }

    @Bean
    @Primary
    public MemberService memberService(){
        return Mockito.mock(MemberService.class);
    }
    @Bean
    @Primary
    public LoanItemService loanItemService(){
        return Mockito.mock(LoanItemService.class);
    }
}
