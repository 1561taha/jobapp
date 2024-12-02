package com.taha.getjobapp.Service;

import com.taha.getjobapp.Model.Application;
import com.taha.getjobapp.Model.ApplicationDto;
import com.taha.getjobapp.Model.Job;
import com.taha.getjobapp.Model.User;
import com.taha.getjobapp.Repository.ApplicationRepo;
import com.taha.getjobapp.Repository.JobRepo;
import com.taha.getjobapp.Repository.UserRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<ApplicationDto> getapplications() {
        User loggedinuser=userService.getLoggedInUsername();

        return applicationRepo.findByUser(loggedinuser).stream().map(application -> {
            ApplicationDto applicationDto=new ApplicationDto();
            applicationDto.setId(application.getId());
            applicationDto.setJobTitle(application.getJob().getTitle());
            applicationDto.setUsername(application.getUser().getUsername());
            applicationDto.setUserEmail(application.getUser().getEmail());
            return applicationDto;
        }).collect(Collectors.toList());
    }

    public ApplicationDto findapp(Long id) {
        return applicationRepo.findById(id).map(application -> {
            ApplicationDto applicationDto= new ApplicationDto();
            applicationDto.setId(application.getId());
            applicationDto.setJobTitle(application.getJob().getTitle());
            applicationDto.setUsername(application.getUser().getUsername());
            applicationDto.setUserEmail(application.getUser().getEmail());
            return applicationDto;

        })
                .orElseThrow(()->new RuntimeException("invalid application id"));
    }
}
