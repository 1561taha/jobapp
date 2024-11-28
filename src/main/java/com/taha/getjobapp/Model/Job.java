package com.taha.getjobapp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private   Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private   String company;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "job_qualifiications" ,joinColumns = @JoinColumn(name = "job_id"))
    @Column(name ="qualification" , nullable = false)
    private List<String> qualifications;

    @Column(nullable = false)
    private  Long exp;

    @ElementCollection
    @CollectionTable(name = "job_skills", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "skill" , nullable = false)
    private List<String> skills;

    @Column(nullable = false)
    private String postedby;
}
