package com.taha.getjobapp.Repository;

import com.taha.getjobapp.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job,Long> {
//    @Query("select j from Job j where j.description = ?1 or j.title = ?2 or j.skills = ?3")
//    List<Job> findByDescriptionOrTitleOrSkills(String description, String title, String skills);

 List<Job> findByTitleContainingOrDescriptionContainingOrSkillsIn(String title, String description,List<String> skills);

 List<Job> findByPostedby(String postedby);
}



