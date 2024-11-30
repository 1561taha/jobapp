package com.taha.getjobapp.Controller;

import com.taha.getjobapp.Model.Application;
import com.taha.getjobapp.Service.ApplicationService;
import com.taha.getjobapp.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class ApplicationController {


    private final ApplicationService applicationService;


   @PostMapping("/apply/{jobid}")

    public ResponseEntity<Application> apply(@PathVariable Long jobid){

       Application application=applicationService.applyjob(jobid);
       return ResponseEntity.ok(application);
   }

   @GetMapping("/myapplications")
    public ResponseEntity<List<Application>> myappli(){
       List<Application> myapplications=applicationService.getapplications();
       return ResponseEntity.ok(myapplications);
   }
}
