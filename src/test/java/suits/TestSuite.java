package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.FirstTest;
import tests.GetStartedTest;
import tests.SearchTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTests.class,
        FirstTest.class,
        GetStartedTest.class,
        SearchTests.class
})
public class TestSuite {
}
