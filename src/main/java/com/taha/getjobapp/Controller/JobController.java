package com.taha.getjobapp.Controller;

import com.taha.getjobapp.Model.Job;
import com.taha.getjobapp.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class JobController {
    @Autowired
    private JobService jobService;


    @GetMapping("/")
    public ResponseEntity<List<Job>> myjobs(){
        return  ResponseEntity.ok(jobService.myjobs());
    }


    @PostMapping("/add-job")
    public void addjob(@RequestBody Job job){
        jobService.add(job);
    }

    @PutMapping("/update-job/{id}")
    public ResponseEntity<Job> updatejob(@PathVariable Long id,
                              @RequestBody Job job){

        return ResponseEntity.ok(jobService.updatejob(id,job));

    }

    @DeleteMapping("/delete-job/{id}")
    public ResponseEntity<String> deletejob(@PathVariable Long id){
        try {
            jobService.deletejob(id);
            return ResponseEntity.ok("job deleted");
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/job-search")
    public ResponseEntity<List<Job>> searchjob(@RequestParam String keyword) {
        return ResponseEntity.ok(jobService.search(keyword));
    }
}
