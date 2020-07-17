import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @org.junit.Test
    public void test(){
        String encode = bCryptPasswordEncoder.encode("123");
        System.out.println(encode);
    }
} 