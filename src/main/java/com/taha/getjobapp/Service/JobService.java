package com.taha.getjobapp.Service;

import com.taha.getjobapp.Model.Job;
import com.taha.getjobapp.Repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    JobRepo jobRepo;

    public List<Job> findposts() {
        return jobRepo.findAll();

    }


    public Job findjob(long id) {
        return jobRepo.findById(id).orElseThrow(()-> new RuntimeException("invalid job id"));
    }

    public void add(Job job) {
        jobRepo.save(job);
    }


    public Job  updatejob(Long id, Job job) {
        return jobRepo.findById(id)
                .map(existingjob ->
                {
                    existingjob.setTitle(job.getTitle());
                    existingjob.setCompany(job.getCompany());
                    existingjob.setType(job.getType());
                    existingjob.setDescription(job.getDescription());
                    existingjob.setQualifications(job.getQualifications());
                    existingjob.setExp(job.getExp());
                    existingjob.setSkills(job.getSkills());
                    existingjob.setPostedby(job.getPostedby());
                    return jobRepo.save(existingjob);
                }).orElseThrow(() ->new RuntimeException("invalid job id"));
    }

    public void deletejob(Long id) {
        if (!jobRepo.existsById(id)){
            throw new RuntimeException("invalid job id" + id);
        }
        jobRepo.deleteById(id);
    }

    public List<Job> search(String keyword) {
      return jobRepo.findByTitleContainingOrDescriptionContainingOrSkillsIn(keyword,keyword,List.of(keyword));
    }
}
