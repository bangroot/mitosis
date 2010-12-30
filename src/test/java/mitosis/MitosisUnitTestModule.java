package mitosis;

import com.mongodb.DB;

import static org.mockito.Mockito.*;

public class MitosisUnitTestModule extends MitosisModule {
  @Override
  protected DB getDatabase() {
    return mock(DB.class);
  }
}
