package com.taha.getjobapp.Controller;


import com.taha.getjobapp.Model.Job;
import com.taha.getjobapp.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")

public class PublicController {

    @Autowired
    private JobService jobService;

    @GetMapping("/all-jobs")
    public ResponseEntity<List<Job>> allpost(){
        return ResponseEntity.ok( jobService.findposts());
    }
    @GetMapping("/all-jobs/{id}")
    public ResponseEntity<Job> findjob(@PathVariable("id") long id ){

        return ResponseEntity.ok(jobService.findjob(id));
    }
}
