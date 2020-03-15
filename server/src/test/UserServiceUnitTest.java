import com.giftshop.GiftShopApplication;
import com.giftshop.models.User;
import com.giftshop.repository.UserDAO;
import com.giftshop.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GiftShopApplication.class)
public class UserServiceUnitTest {
    @InjectMocks
    User user;

    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDao;
    private EmbeddedDatabase database;
    @Before
    public void init() {
        user.setPassword("test");
        user.setEmail("test_email");
        user.setPhoneNumber("aaaaaaaa");
        user.setName("test");
        user.setSurname("test");
        user.setBirthDate(  LocalDate.now());
        user.setIsActivated(true);
        user.setPatronymic("test");

        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void insertNewUserAndDelete_Test(){

        Assert.assertEquals((long)1, (long)userDao.insertUser(user));
        Assert.assertEquals( (long)1, (long) userDao.deleteUser(user.getEmail()));


    }
    @Test
    public void findByIdTest(){
        Assert.assertEquals(1, (long) userDao.insertUser(user));


        User u = userDao.findUserByEmail(user.getEmail());

        Assert.assertNotNull(userDao.findUserById(u.getUserId()));

        Assert.assertEquals(  (long)1 ,  (long) userDao.deleteUser(user.getEmail()));

    }
    @Test
    public void findByEmailTest(){
        Assert.assertEquals(1,(long) userDao.insertUser(user));

        Assert.assertNotNull(userDao.findUserByEmail(user.getEmail()));

        Assert.assertEquals((long) userDao.deleteUser(user.getEmail()), (long)1);

    }
    @Test
    public void findUserRoleTest(){

        Assert.assertEquals( 1,(long)userDao.insertUser(user));

        User u = userDao.findUserByEmail(user.getEmail());
        Assert.assertNotNull(userDao.findUserRoleById(u.getUserId()));

        Assert.assertEquals((long) userDao.deleteUser(user.getEmail()), (long)1);
    }

}
