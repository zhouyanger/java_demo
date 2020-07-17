import com.zy.configuration.SpringConfiguration;
import com.zy.entity.Book;
import com.zy.entity.Student;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class Test {
    @Resource
    private Book book;
    @Resource
    private Student student;
    @org.junit.Test
    public void test(){
        System.out.println(student);
    }
}
