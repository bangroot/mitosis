package mitosis;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;

public class GenePool {
  private String _id;

  public String get_id() {
    return _id;
  }

  public void save(DB database) {
    BasicDBObject object = new BasicDBObject();
    object.put("size", 15);
    database.getCollection("genepool").insert(object);
    _id = object.getString("_id");
  }
}
