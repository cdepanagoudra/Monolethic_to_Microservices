package com.microservice.jobms.job;


import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {

    void createJob(Job job);

    Job getJobId(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedjob);
}
