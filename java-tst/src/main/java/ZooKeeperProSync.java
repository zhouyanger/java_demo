import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.concurrent.CountDownLatch;

public class ZooKeeperProSync  {
    public static void main(String[] args) throws Exception{
        System.out.println(DigestAuthenticationProvider.generateDigest("acluser1:111111"));
    }

}
