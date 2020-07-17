import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongodbUtil {
    private static MongoClient mongoClient;
    static{
        String ip = "127.0.0.1";
        int port = 27017;
        mongoClient = new MongoClient(ip,port);
    }
    // ------------------------------------共用方法---------------------------------------------------
    /**
     * 获取db实例
     */
    public MongoDatabase getdb(String dbName){
        if (dbName!=null&& !"".equals(dbName))
       return  mongoClient.getDatabase(dbName);
        return null;
    }
    /**
     * 获取collection对象
     */
    public MongoCollection<Document> getCollection(String dbName, String collName) {
        if (dbName!=null || "".equals(dbName)){
            return null;
        }
        if (collName!=null || "".equals(collName)){
            return null;
        }
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
        return collection;
    }

    /**
     * 获取db下所有集合
     */
    public List<String> getAllCollections(String dbName) {
        List<String> collectionsName = new ArrayList<>();
        MongoIterable<String> strings = getdb(dbName).listCollectionNames();
        for (String name:strings){
            collectionsName.add(name);
        }
        return collectionsName;
    }
    /**
     * 获取所有数据库名称
     */
    public MongoIterable<String> getAllDBNames() {
        MongoIterable<String> strings = mongoClient.listDatabaseNames();
        return strings;
    }
    /**
     * 删除一个数据库
     */
    public void dropDB(String dbName) {
        if (dbName!=null || "".equals(dbName)){
            throw  new RuntimeException("not found database");
        }
        mongoClient.dropDatabase(dbName);
    }
    /**
     * 根据id获取对象
     */
    public Document findById(MongoCollection<Document> coll, String id) {
        if (id==null && !"".equals(id)){
            return null;
        }
        ObjectId objectId  = new ObjectId(id);
        Document id1 = coll.find(Filters.eq("_id", objectId)).first();
        return id1;
    }
    /** 统计数 */
    public int getCount(MongoCollection<Document> coll) {
                int count = (int) coll.count();
                 return count;
           }
    /** 条件查询 */
    public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        FindIterable<Document> documents = coll.find(filter);
        MongoCursor<Document> iterator = documents.iterator();
        return iterator;
    }
    /** 分页查询 */
    public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
        Bson bson = new BasicDBObject("_id",1);
        MongoCursor<Document> iterator = coll.find(filter).sort(bson).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
        return iterator;
    }
    /**
     * 通过id删除
     */
    public int deleteById(MongoCollection<Document> coll, String id) {
        Bson id1 = Filters.eq("_id", new ObjectId(id));
        DeleteResult deleteResult = coll.deleteOne(id1);
        long deletedCount = deleteResult.getDeletedCount();
        return (int)deletedCount;
    }

    /**
     * 更新文档
     * @param coll
     * @param id
     * @param newdoc
     * @return
     */
    public Document updateById(MongoCollection<Document> coll, String id, Document newdoc) {
        ObjectId _idobj = null;
              try {
                       _idobj = new ObjectId(id);
                     } catch (Exception e) {
                        return null;
                   }
              Bson filter = Filters.eq("_id", _idobj);
                // coll.replaceOne(filter, newdoc); // 完全替代
                coll.updateOne(filter, new Document("$set", newdoc));
               return newdoc;
            }

    /**
     * 删除集合
     * @param dbName
     * @param collName
     */
    public void dropCollection(String dbName, String collName) {
                 getdb(dbName).getCollection(collName).drop();
           }

    /**
         * 关闭Mongodb
          */
    public void close() {
                if (mongoClient != null) {
                       mongoClient.close();
                      mongoClient = null;
                    }
           }
}
