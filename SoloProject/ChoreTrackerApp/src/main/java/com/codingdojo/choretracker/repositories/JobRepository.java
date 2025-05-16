package com.codingdojo.choretracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.choretracker.models.Job;
import com.codingdojo.choretracker.models.User;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    List<Job> findAll();
    List<Job> findByWorkerIsNull();
    List<Job> findByWorker(User worker);
    List<Job> findByCreator(User creator);
}