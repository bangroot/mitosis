package mitosis;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import org.bson.types.ObjectId;

import java.util.HashMap;

public abstract class BaseEntity {
  private static HashMap<Class, String> collectionMap = new HashMap<Class, String>();
  private ObjectId _id;

  public static String getCollectionFor(Class clazz) {
    return collectionMap.get(clazz);
  }

  protected static void registerCollectionName(Class clazz, String collection) {
    collectionMap.put(clazz, collection);
  }

  public ObjectId getId() {
    return _id;
  }

  public void save(DB database) {
    BasicDBObject object = toDbObject();
    if (null == _id) {
      database.getCollection(getCollectionFor(getClass())).insert(object);
      _id = (ObjectId) object.get("_id");
    } else {
      object.put("_id", _id);
      database.getCollection(getCollectionFor(getClass())).update(new BasicDBObject("_id", _id), object, true, false);
    }
  }

  public static <T extends BaseEntity> T findByKey(Class<T> entityClass, ObjectId id, DB database) {
    return (T) database.getCollection(getCollectionFor(entityClass)).findOne(new BasicDBObject("_id", id));
  }

  protected abstract BasicDBObject toDbObject();

}
