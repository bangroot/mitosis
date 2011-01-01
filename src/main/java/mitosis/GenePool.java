package mitosis;

import com.mongodb.BasicDBObject;

public class GenePool extends BaseEntity {
  private int size = 15;

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
  protected BasicDBObject toDbObject() {
    BasicDBObject object = new BasicDBObject();
    object.put("size", size);
    return object;
  }
}
