package com.codingdojo.choretracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.choretracker.models.Job;
import com.codingdojo.choretracker.models.User;
import com.codingdojo.choretracker.services.JobService;
import com.codingdojo.choretracker.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class JobController {
    
    @Autowired
    private JobService jobService;
    
    @Autowired
    private UserService userService;
    
    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        // Get user
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        
        // Get all available jobs
        List<Job> availableJobs = jobService.findAvailableJobs();
        model.addAttribute("availableJobs", availableJobs);
        
        // Get user's jobs
        List<Job> myJobs = jobService.findJobsByWorker(user);
        model.addAttribute("myJobs", myJobs);
        
        return "dashboard";
    }
    
    // Add Job Form
    @GetMapping("/jobs/new")
    public String newJobForm(HttpSession session, Model model) {
        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        model.addAttribute("job", new Job());
        return "newJob";
    }
    
    // Create Job
    @PostMapping("/jobs")
    public String createJob(@Valid @ModelAttribute("job") Job job, BindingResult result, 
            HttpSession session, Model model) {
        
        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        // Check for validation errors
        if(result.hasErrors()) {
            return "newJob";
        }
        
        // Set creator
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);
        job.setCreator(user);
        
        // Save job
        jobService.createJob(job);
        
        return "redirect:/dashboard";
    }
    
    // View Job
    @GetMapping("/jobs/{id}")
    public String viewJob(@PathVariable("id") Long id, HttpSession session, Model model) {
        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        // Get job
        Job job = jobService.findJobById(id);
        model.addAttribute("job", job);
        
        // Get user
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        
        return "viewJob";
    }
    
    // Edit Job Form
    @GetMapping("/jobs/{id}/edit")
    public String editJobForm(@PathVariable("id") Long id, HttpSession session, Model model) {
        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        // Get job
        Job job = jobService.findJobById(id);
        
        // Check if user is the creator
        Long userId = (Long) session.getAttribute("userId");
        if(!job.getCreator().getId().equals(userId)) {
            return "redirect:/dashboard";
        }
        
        model.addAttribute("job", job);
        return "editJob";
    }
    
 // Update Job - Using PUT mapping
    @PostMapping("/jobs/{id}")
    public String updateJob(@Valid @ModelAttribute("job") Job job, BindingResult result,
                         @PathVariable("id") Long id, HttpSession session) {

        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        // Get original job
        Job originalJob = jobService.findJobById(id);
        
        // Check if original job exists
        if(originalJob == null) {
            return "redirect:/dashboard";
        }
        
        // Check if user is the creator
        Long userId = (Long) session.getAttribute("userId");
        if(!originalJob.getCreator().getId().equals(userId)) {
            return "redirect:/dashboard";
        }

        // Check for validation errors
        if(result.hasErrors()) {
            return "editJob";
        }
        
        // Set the ID to ensure we're updating the existing record
        job.setId(id);
        
        // Preserve creation timestamp
        job.setCreatedAt(originalJob.getCreatedAt());
        
        // Set creator and worker
        job.setCreator(originalJob.getCreator());
        job.setWorker(originalJob.getWorker());

        // Update job
        jobService.updateJob(job);

        return "redirect:/dashboard";
    }
    
    // Cancel Job
    @GetMapping("/jobs/{id}/cancel")
    public String cancelJob(@PathVariable("id") Long id, HttpSession session) {
        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        // Get job
        Job job = jobService.findJobById(id);
        
        // Check if user is the creator
        Long userId = (Long) session.getAttribute("userId");
        if(!job.getCreator().getId().equals(userId)) {
            return "redirect:/dashboard";
        }
        
        // Delete job
        jobService.deleteJob(id);
        
        return "redirect:/dashboard";
    }
    
    // Add Job to My Jobs
    @GetMapping("/jobs/{id}/add")
    public String addToMyJobs(@PathVariable("id") Long id, HttpSession session) {
        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        // Get job
        Job job = jobService.findJobById(id);
        
        // Get user
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findById(userId);
        
        // Add job to user's list
        jobService.addJobToUserList(job, user);
        
        return "redirect:/dashboard";
    }
    
    // Remove Job from My Jobs (Mark as Done)
    @GetMapping("/jobs/{id}/done")
    public String markJobAsDone(@PathVariable("id") Long id, HttpSession session) {
        // Check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        
        // Get job
        Job job = jobService.findJobById(id);
        
        // Check if user is the worker
        Long userId = (Long) session.getAttribute("userId");
        if(job.getWorker() == null || !job.getWorker().getId().equals(userId)) {
            return "redirect:/dashboard";
        }
        
        // Delete job
        jobService.deleteJob(id);
        
        return "redirect:/dashboard";
    }
}