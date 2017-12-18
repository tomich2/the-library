import cz.fi.muni.pa165.library.persistance.entity.Member;
import cz.fi.muni.pa165.sampleData.SampleDataConfiguration;
import cz.fi.muni.pa165.service.BookService;
import cz.fi.muni.pa165.service.LoanItemService;
import cz.fi.muni.pa165.service.LoanService;
import cz.fi.muni.pa165.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * Tests data loading.
 *
 * @author Jan Tlamicha (xtlamich)
 */
@ContextConfiguration(classes = {SampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoaderTest  extends AbstractTestNGSpringContextTests {
    @Autowired
    private BookService bookService;

    @Autowired
    private LoanItemService loanItemService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private LoanService loanService;

    @Test
    public void createSampleData() {

        Assert.assertTrue(bookService.findAll().size() > 0, "no books");

        Assert.assertTrue(loanItemService.findAll().size() > 0, "no loan items");

        Assert.assertTrue(memberService.findAll().size() > 0, "no members");

        Member admin = memberService.findAll().stream().filter(memberService::isAdmin).findFirst().get();
        Assert.assertTrue(memberService.authenticateMember(admin,"password"));
    }

}
