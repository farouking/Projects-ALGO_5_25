package com.codingdojo.choretracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.choretracker.models.Job;
import com.codingdojo.choretracker.models.User;
import com.codingdojo.choretracker.repositories.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    
    // Create a new job
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }
    
    // Find all jobs
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }
    
    // Find all available jobs (not assigned to anyone)
    public List<Job> findAvailableJobs() {
        return jobRepository.findByWorkerIsNull();
    }
    
    // Find jobs by worker
    public List<Job> findJobsByWorker(User worker) {
        return jobRepository.findByWorker(worker);
    }
    
    // Find jobs by creator
    public List<Job> findJobsByCreator(User creator) {
        return jobRepository.findByCreator(creator);
    }
    
    // Find job by id
    public Job findJobById(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        return optionalJob.orElse(null);
    }
    
    // Update job
    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }
    
    // Delete job
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
    
    // Add job to user's list
    public Job addJobToUserList(Job job, User user) {
        job.setWorker(user);
        return jobRepository.save(job);
    }
    
    // Remove job from user's list
    public Job removeJobFromUserList(Job job) {
        job.setWorker(null);
        return jobRepository.save(job);
    }
}
