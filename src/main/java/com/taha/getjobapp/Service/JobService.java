package com.taha.getjobapp.Service;

import com.taha.getjobapp.Model.Job;
import com.taha.getjobapp.Repository.JobRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.sql.Array;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
  private   JobRepo jobRepo;

    public List<Job> findposts() {
        return jobRepo.findAll();

    }


    public Job findjob(long id) {
        return jobRepo.findById(id).orElseThrow(()-> new RuntimeException("invalid job id"));
    }

    public void add(Job job) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        job.setPostedby(username);
        jobRepo.save(job);
    }


    public Job  updatejob(Long id, Job job) {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        return jobRepo.findById(id)
                .map(existingjob ->
                {
                    if (!existingjob.getPostedby().equals(username)) {
                        try {
                            throw new AccessDeniedException(" u can only update your own job");
                        } catch (AccessDeniedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    existingjob.setTitle(job.getTitle());
                    existingjob.setCompany(job.getCompany());
                    existingjob.setType(job.getType());
                    existingjob.setDescription(job.getDescription());
                    existingjob.setQualifications(job.getQualifications());
                    existingjob.setExp(job.getExp());
                    existingjob.setSkills(job.getSkills());

                    return jobRepo.save(existingjob);
                }).orElseThrow(() ->new RuntimeException("invalid job id"));
    }

    public void deletejob(Long id) throws AccessDeniedException {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();

          Job job = jobRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found with id: " + id));

        // Check if the logged-in user is the creator
        if (!job.getPostedby().equals(username)) {
            throw new AccessDeniedException("You can only delete jobs you created.");
        }
        if (!jobRepo.existsById(id)){
            throw new RuntimeException("invalid job id" + id);
        }
        jobRepo.deleteById(id);
    }

    public List<Job> search(String keyword) {
      return jobRepo.findByTitleContainingOrDescriptionContainingOrSkillsIn(keyword,keyword,List.of(keyword));
    }

    public List<Job> myjobs() {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        return jobRepo.findByPostedby(username);
    }
}
