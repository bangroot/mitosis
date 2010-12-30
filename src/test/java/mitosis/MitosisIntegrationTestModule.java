package mitosis;

import com.mongodb.DB;
import com.mongodb.Mongo;

import java.net.UnknownHostException;

import static org.mockito.Mockito.*;

public class MitosisIntegrationTestModule extends MitosisModule {
  private Mongo m;

  public MitosisIntegrationTestModule() throws UnknownHostException {
    m = new Mongo();
  }

  @Override
  protected DB getDatabase() {
    return spy(m.getDB("mitosis-test"));
  }
}
