package mitosis;

import com.mongodb.DB;
import org.junit.Test;

import static junit.framework.Assert.*;

public class GenePoolTest extends BaseTest {

  @Test
  public void testCreationOfNewGenePool() {
    GenePool pool = new GenePool();
    DB db = injector.getInstance(DB.class);
    pool.save(db);
    assertEquals(1, db.getCollection(BaseEntity.getCollectionFor(GenePool.class)).count());
  }

  @Test
  public void testModificationOfGenePool() {
    GenePool pool = new GenePool();
    DB db = injector.getInstance(DB.class);
    pool.save(db);
    assertEquals(1, db.getCollection(BaseEntity.getCollectionFor(GenePool.class)).count());
    int newSize = pool.getSize() + 1;
    pool.setSize(newSize);
    pool.save(db);
    assertEquals(1, db.getCollection(BaseEntity.getCollectionFor(GenePool.class)).count());
    GenePool lookedUpPool = BaseEntity.findByKey(GenePool.class,  pool.getId(), db);
    assertEquals(newSize, lookedUpPool.getSize());
    assertEquals(pool.getId(), lookedUpPool.getId());
  }

  @Test
  public void testPoolSizing() {
    GenePool pool = new GenePool();
    pool.registerGeneClass(TestGene.class);
    pool.initialize();
    assertEquals(15, pool.getCurrentChromosomes().size());
  }

  public static class TestGene extends Gene {
  }
}
