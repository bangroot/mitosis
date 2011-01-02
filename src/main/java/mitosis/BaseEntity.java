package mitosis;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.HashMap;

public abstract class BaseEntity {
  private static HashMap<Class<? extends BaseEntity>, String> collectionMap = new HashMap<Class<? extends BaseEntity>, String>();
  private ObjectId _id;

  public static String getCollectionFor(Class<? extends BaseEntity> clazz) {
    return collectionMap.get(clazz);
  }

  protected static void registerCollectionName(Class<? extends BaseEntity> clazz, String collection) {
    collectionMap.put(clazz, collection);
  }

  public ObjectId getId() {
    return _id;
  }

  protected void setId(ObjectId id) {
    _id = id;
  }

  public void save(DB database) {
    DBObject object = toDbObject();
    if (null == _id) {
      database.getCollection(getCollectionFor(getClass())).insert(object);
      _id = (ObjectId) object.get("_id");
    } else {
      object.put("_id", _id);
      database.getCollection(getCollectionFor(getClass())).update(new BasicDBObject("_id", _id), object, true, false);
    }
  }

  public static <T extends BaseEntity> T findByKey(Class<T> entityClass, ObjectId id, DB database) {
    // this should be protected by the framework
    //noinspection unchecked
    DBObject oData = database.getCollection(getCollectionFor(entityClass)).findOne(new BasicDBObject("_id", id));
    try {
      T val = entityClass.newInstance();
      val.fromDbObject(oData);
      return val;
    } catch (Throwable e) {
      throw new RuntimeException("Error reading object from database.", e);
    }
  }

  protected abstract DBObject toDbObject();

  protected abstract void fromDbObject(DBObject data);
}
