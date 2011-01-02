package mitosis;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GenePool extends BaseEntity {
  private static final Random RND = new Random(System.currentTimeMillis());
  private int size = 15;
  private Set<Chromosome> currentChromosomes;
  private List<Class<? extends Gene>> availableGenes = new ArrayList<Class<? extends Gene>>();

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }


  static {
    registerCollectionName(GenePool.class, "genepool");
  }

  @Override
  protected DBObject toDbObject() {
    BasicDBObjectBuilder b = BasicDBObjectBuilder.start().add("size", size);
    return b.get();
  }

  @Override
  protected void fromDbObject(DBObject data) {
    size = (Integer) data.get("size");
    setId((ObjectId) data.get("_id"));
  }

  public void registerGeneClass(Class<? extends Gene> geneClass) {
    availableGenes.add(geneClass);
  }

  public void initialize() {
    currentChromosomes = new HashSet<Chromosome>();
    for (int i = 0; i < size; i++) {
      try {
        currentChromosomes.add(new Chromosome(availableGenes.get(RND.nextInt(availableGenes.size())).newInstance()));
      } catch (Throwable e) {
        throw new RuntimeException("Error creating initial gene.", e);
      }
    }
  }

  public Set<Chromosome> getCurrentChromosomes() {
    return currentChromosomes;
  }
}
