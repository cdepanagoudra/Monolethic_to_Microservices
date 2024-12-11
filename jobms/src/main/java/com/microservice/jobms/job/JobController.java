package com.microservice.jobms.job;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>( "Jobs added successfully", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id){
        Job job = jobService.getJobId(id);
        if(job!=null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody Job job){
         boolean updated= jobService.updateJob(id,job);
         if(updated){
             return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
