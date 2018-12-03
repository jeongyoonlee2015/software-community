package site.namsu.sweng.core.service;

import org.springframework.stereotype.Component;
import site.namsu.sweng.base.Dao;
import site.namsu.sweng.core.dao.UserDao;
import site.namsu.sweng.core.entity.User;
import site.namsu.sweng.util.Mail;

/**
 * @Author : Hyunwoong
 * @When : 2018-11-28 오후 2:31
 * @Homepage : https://github.com/gusdnd852
 */
@Component
public class MailService {

    private final Dao<User> dao = Dao.of(UserDao.class);

    public boolean isCorrectUser(User user) {
        User dbUser = dao.select(user);

        if (dbUser == null) return false;
        else return dbUser.getEmail().equals(user.getEmail())
                && dbUser.getName().equals(user.getName());
    }

    public boolean sendMailSuccessful(User user) {
        if (user == null) return false;
        else return Mail.send(user.getEmail(), user.getStdNumber(), user.getName());
    }
}
