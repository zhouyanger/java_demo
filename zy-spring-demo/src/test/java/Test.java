import com.zy.annotion.AopTest;
import com.zy.annotion.Book;
import com.zy.annotion.Category;
import com.zy.entity.Student;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import javax.annotation.Resource;
import java.util.Calendar;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class Test {
    @Resource
    private Book book;
    @Resource
    private Category category;
    @Resource
    private AopTest aopTest;
    @org.junit.Test
    public void test1(){
        System.out.println(book);
        System.out.println(category);
    }

    @org.junit.Test
    public void test2(){
        aopTest.say();
    }
}
