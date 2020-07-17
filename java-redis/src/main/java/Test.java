import redis.clients.jedis.*;

public class Test {
    //测试没有redis连接池的情况
//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("localhost",6379);
//        int i=0;
//        try {
//            long start = System.currentTimeMillis();
//            while(true){
//                long end =  System.currentTimeMillis();
//                if (end-start>1000){
//                    break;
//                }
//                i++;
//                jedis.set("test"+i,i+"");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println(i);
//    }
    
    //测试redis连接池
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxWaitMillis(2000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"localhost");
        Jedis jedis = jedisPool.getResource();
        long a = System.currentTimeMillis();
        Pipeline pipelined = jedis.pipelined();
        for (int i=0;i<300000;i++){
            pipelined.set("name"+i,i+"");
        }
        pipelined.sync();
//        Transaction multi = jedis.multi();
//        for (int i=0;i<300000;i++){
//            multi.set("name"+i,i+"");
//        }
//        long b = System.currentTimeMillis();
//        System.out.println("流水线时间:"+(b-a));
//        multi.exec();
        
        
    }
}
