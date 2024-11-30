package com.taha.getjobapp.Repository;

import com.taha.getjobapp.Model.Application;
import com.taha.getjobapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo  extends JpaRepository<Application,Long> {

    List<Application> findByUser(User user);
}
