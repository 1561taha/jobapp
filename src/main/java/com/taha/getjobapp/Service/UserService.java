package com.taha.getjobapp.Service;

import com.taha.getjobapp.Model.User;
import com.taha.getjobapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

   private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
    public User adduser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }


}
