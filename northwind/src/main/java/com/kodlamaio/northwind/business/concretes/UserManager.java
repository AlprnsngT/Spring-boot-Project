package com.kodlamaio.northwind.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.northwind.business.abstracts.UserService;
import com.kodlamaio.northwind.core.dataAccess.UserDao;
import com.kodlamaio.northwind.core.entities.User;
import com.kodlamaio.northwind.core.utilities.results.DataResult;
import com.kodlamaio.northwind.core.utilities.results.Result;
import com.kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import com.kodlamaio.northwind.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı Eklendi");

    }

    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<User>
        (this.userDao.findByEmail(email),"Kullanıcı Bulundu");
    }

}
