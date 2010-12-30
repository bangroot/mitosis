package mitosis;

import com.google.inject.AbstractModule;
import com.mongodb.DB;

public abstract class MitosisModule extends AbstractModule {

  
  @Override
  protected final void configure() {
    bind(DB.class).toInstance(getDatabase());
  }

  protected abstract DB getDatabase();
}
