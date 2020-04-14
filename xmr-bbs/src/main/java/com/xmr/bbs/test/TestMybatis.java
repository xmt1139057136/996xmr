package com.xmr.bbs.test;

import com.xmr.bbs.dao.QuestionExtMapper;
import com.xmr.bbs.dao.QuestionMapper;
import com.xmr.bbs.dao.UserExtMapper;
import com.xmr.bbs.dao.UserMapper;
import com.xmr.bbs.modal.Question;
import com.xmr.bbs.modal.User;
import com.xmr.bbs.utils.DatesUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestMybatis {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtMapper userExtMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private SqlSessionFactory sessionFactory;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private ApplicationContext ctx=null;


    @Test
    public void testgetnewusers(){
        List<User> newUserList = userExtMapper.findNewUserList(10);
        System.out.println(newUserList);
    }
    @Test
    public void getPageBySearch(){
        //List<Question> questions = questionExtMapper.listQuestionWithUserBySearch("java","java", questionQueryDTO.getCategory());
        //System.out.println(questions.size());
    }
    @Test
    public void listQuestionWeekHot(){
        long startweektime = DatesUtil.getBeginDayOfWeek().getTime();
        long endweetime=DatesUtil.getEndDayOfWeek().getTime();
        //List<Question> questions = questionExtMapper.listQuestionHotByTime(startweektime, endweetime, questionQueryDTO.getCategory());
        //System.out.println(questions.size());

    }

    @Test
    public void testsavenotification(){
//        SqlSession sqlSession = sessionFactory.openSession();
//        NotificationMapper mapper = sqlSession.getMapper(NotificationMapper.class);
//        Notification notification = mapper.selectByPrimaryKey(1L);
//        System.out.println(notification);
//        System.out.println(mapper);
//        Notification record = new Notification();
//        record.setStatus(1);
//        record.setOuter(1);
//        record.setReceiver(1L);
//        record.setGmtCreate(System.currentTimeMillis());
//        record.setType(1);
//        record.setNotifier(2L);
//        mapper.insertSelective(record);
       // sqlSession.commit();
    }
    @Test
    public void testgetmapper(){
        System.out.println(ctx);
    }
    @Test
    public void testgetconnection() throws SQLException {
        DataSource bean = ctx.getBean(DataSource.class);
        Connection connection =
                bean.getConnection();
        System.out.println(connection.getClass().getName());
    }

    @Test
    public void testgetquestionwithuser(){
        List<Question> questions = questionExtMapper.listQuestionWithUser();
        for (Question question : questions) {
            User user = question.getUser();
            System.out.println(user);
        }
    }
    @Test
    public void testgetuserquestion(){
        List<Question> questions = questionExtMapper.listQuestionWithUserByUserId(26);
        for (Question question : questions) {
            User user = question.getUser();
            System.out.println(user+"------------------------->");
        }
    }
}
