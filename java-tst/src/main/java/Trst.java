import org.apache.yetus.audience.InterfaceAudience.Private;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Trst {
    private static final String PARENT_NODE = "/aa";
    private static final String CHILD_NODE = "/aa/bb";
    public static void main(String[] args) throws Exception {
        List<ACL> list = new ArrayList<>();
        ACL acl = new ACL(Perms.ALL, Ids.ANYONE_ID_UNSAFE);
        list.add(acl);
        
        Watcher defaultWatcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getPath());
            }
        };
        ZooKeeper client = new ZooKeeper("127.0.0.1:2181",3000,defaultWatcher);
        //创建节点
        if (client.exists(PARENT_NODE,false)!=null){
            client.delete(PARENT_NODE,-1);
        }
        client.create(PARENT_NODE,"zhouyang".getBytes(),list, CreateMode.PERSISTENT);
        //创建子节点
        client.create(CHILD_NODE,"zhangsan".getBytes(),list,CreateMode.PERSISTENT);
        //获取子节点数据
        Stat stat = new Stat();
        byte[] data = client.getData(CHILD_NODE, false, stat);
        System.out.println("childdata:"+new String(data));
        //获取父节点的子节点
        List<String> children = client.getChildren(PARENT_NODE, false);
        System.out.println(children);
        
    }
}
