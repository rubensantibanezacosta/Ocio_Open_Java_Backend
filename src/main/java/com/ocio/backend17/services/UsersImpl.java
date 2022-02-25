package com.ocio.backend17.services;

import com.ocio.backend17.dao.UsersDao;
import com.ocio.backend17.entities.Users;
import com.ocio.backend17.utils.DateFormatterSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersImpl implements IUsers {
    @Autowired
    UsersDao usersDao;

    @Autowired
    DateFormatterSQL dateFormatterSQL;

    @Override
    public List<Users> getAll() {
        return (List<Users>) usersDao.findUsersOrderByPunctuacion();
    }

    @Override
    public Optional<Users> getById(String id) {
        return usersDao.findById(id);
    }

    @Override
    public Users createOrUpdate(Users user) {

        if (usersDao.findById(user.getEmail()).isPresent()) {

            Users updatedUser = usersDao.findById(user.getEmail()).get();
            updatedUser.setName(user.getName());
            updatedUser.setSurname(user.getSurname());
            updatedUser.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
            return usersDao.save(updatedUser);
        } else {
            Users createdUser = user;
            createdUser.setLastconnection(dateFormatterSQL.nowTimestampSQLFormat());
            createdUser.setCreatedAt(dateFormatterSQL.todaySQLFormat());
            createdUser.setUpdatedAt(dateFormatterSQL.todaySQLFormat());
            return usersDao.save(createdUser);
        }

    }

    @Override
    public List<Users> getByLastConnection() {
        return usersDao.findUsersOrderByLastconnection();
    }

    @Override
    public void deleteById(String id) {
        usersDao.deleteById(id);
    }

    @Override
    public int getUserPosition(String email) {
        if (usersDao.findById(email).isPresent()) {
            Users user = usersDao.findById(email).get();
            List<Users> orderedUsers = usersDao.findUsersOrderByPunctuacion();
            return orderedUsers.indexOf(user) + 1;
        } else {
            return 0;
        }
    }

    @Override
    public void updateAvgPunctuation(Double punctuation_avg, String id) {
        if (usersDao.existsById(id)) {
            Users oldUser = usersDao.findById(id).get();
            oldUser.setPunctuation_avg(punctuation_avg);
            usersDao.save(oldUser);
        }

    }

    @Override
    public void updateLastConnection(String id) {
        if (usersDao.existsById(id)) {
            Users oldUser = usersDao.findById(id).get();
            oldUser.setLastconnection(new Timestamp(new Date().getTime()));
            usersDao.save(oldUser);
        }
    }
}
