package com.taha.getjobapp.Service;

import com.taha.getjobapp.Model.Application;
import com.taha.getjobapp.Model.Job;
import com.taha.getjobapp.Model.User;
import com.taha.getjobapp.Repository.ApplicationRepo;
import com.taha.getjobapp.Repository.JobRepo;
import com.taha.getjobapp.Repository.UserRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepo applicationRepo;
    private final JobRepo jobRepo;
    private final UserRepo userRepository;
    private final UserService userService;
    public Application  applyjob(Long id) {
        User loggedinuser= userService.getLoggedInUsername();

        Job job=jobRepo.findById(id).orElseThrow(()-> new RuntimeException("invalid job"));

        Application application= new Application();
        application.setUser(loggedinuser);
        application.setJob(job);

        return applicationRepo.save(application);



        



    }

    public List<Application> getapplications() {
        User loggedinuser=userService.getLoggedInUsername();
        return applicationRepo.findByUser(loggedinuser);
    }

    public Application findapp(Long id) {
        return applicationRepo.findById(id).orElseThrow(()->new RuntimeException("invalid application id"));
    }
}
