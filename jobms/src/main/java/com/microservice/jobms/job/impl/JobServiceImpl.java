package com.chetan.firstjobapp.job.impl;

import com.chetan.firstjobapp.job.Job;
import com.chetan.firstjobapp.job.JobRepository;
import com.chetan.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        //job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobId(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {

        try{
            Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()) {
                jobRepository.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id,Job updatedjob) {
        Optional<Job> jobOptional = jobRepository.findById(id);

            if(jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setDescription(updatedjob.getDescription());
                job.setLocation(updatedjob.getLocation());
                job.setTitle(updatedjob.getTitle());
                job.setMaxSalary(updatedjob.getMaxSalary());
                job.setMinSalary(updatedjob.getMinSalary());
                jobRepository.save(job);
                return true;

            }

        return false;
    }
}
