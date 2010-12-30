package mitosis;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.DB;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.net.UnknownHostException;

public abstract class BaseTest {

  protected static Injector injector;

  @BeforeClass
  public static void setupInjector() throws UnknownHostException {
    injector = Guice.createInjector(new MitosisIntegrationTestModule());
  }

  @AfterClass
  public static void tearDownData() {
    injector.getInstance(DB.class).dropDatabase();
  }
}
