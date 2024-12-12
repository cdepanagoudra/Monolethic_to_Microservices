package com.microservice.jobms.job;

import com.microservice.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    Job getJobId(Long id);
    boolean deleteJobById(Long id);

    boolean updateJob(Long id,Job job);
}
