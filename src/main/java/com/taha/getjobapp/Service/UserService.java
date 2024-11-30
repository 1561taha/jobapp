package com.taha.getjobapp.Service;

import com.taha.getjobapp.Model.User;
import com.taha.getjobapp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public User getLoggedInUsername() {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();

        User user= userRepo.findByUsername(username);
        if (user!=null){
            return user;
        }
        else
            throw new UsernameNotFoundException("invalid user");
    }


}
