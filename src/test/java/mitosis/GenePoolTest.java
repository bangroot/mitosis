package mitosis;

import com.mongodb.DB;
import com.mongodb.DBObject;
import org.junit.Test;

import static junit.framework.Assert.*;

public class GenePoolTest extends BaseTest {

  @Test
  public void testCreationOfNewGenePool() {
    GenePool pool = new GenePool();
    DB db = injector.getInstance(DB.class);
    pool.save(db);
    assertEquals(1, db.getCollection("genepool").count());
    for (DBObject dbo : db.getCollection("genepool").find()) {
      System.out.println(dbo);
    }
    System.out.println(pool.get_id());
  }
}
