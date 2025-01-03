package com.microservice.jobms.job;

import com.microservice.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);
    JobDTO getJobId(Long id);
    boolean deleteJobById(Long id);

    boolean updateJob(Long id,Job job);
}
