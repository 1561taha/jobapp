package com.taha.getjobapp.Controller;


import com.taha.getjobapp.Model.User;
import com.taha.getjobapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.ok(userService.adduser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user){
      Authentication authentication= authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken
                        (user.getUsername(),user.getPassword()));

        if (authentication.isAuthenticated()){
            return ResponseEntity.ok("login succccessfull");
        }
        else
            return ResponseEntity.badRequest().build();

    }
}
