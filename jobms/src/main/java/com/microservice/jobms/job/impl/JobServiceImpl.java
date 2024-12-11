package com.microservice.jobms.job.impl;


import com.microservice.jobms.job.Job;
import com.microservice.jobms.job.JobRepository;
import com.microservice.jobms.job.JobService;
import org.springframework.stereotype.Service;

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
    public void createJob(Job j) {
        //job.setId(nextId++);
        jobRepository.save(j);
    }

    @Override
    public Job getJobId(Long i) {
        return jobRepository.findById(i).orElse(null);
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
    public boolean updateJob(Long id, Job updatedj) {
        Optional<Job> jobOptional = jobRepository.findById(id);

            if(jobOptional.isPresent()){
                Job j = jobOptional.get();
                j.setDescription(updatedj.getDescription());
                j.setLocation(updatedj.getLocation());
                j.setTitle(updatedj.getTitle());
                j.setMaxSalary(updatedj.getMaxSalary());
                j.setMinSalary(updatedj.getMinSalary());
                jobRepository.save(j);
                return true;

            }

        return false;
    }
}
