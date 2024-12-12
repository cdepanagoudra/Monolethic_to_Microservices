package com.microservice.jobms.job.impl;


import com.microservice.jobms.job.Job;
import com.microservice.jobms.job.JobRepository;
import com.microservice.jobms.job.JobService;
import com.microservice.jobms.job.dto.JobWithCompanyDTO;
import com.microservice.jobms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //private Long nextId = 1L;
    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();





        return jobs.stream().map(this ::convertToDto).collect(Collectors.toList());
    }
    private JobWithCompanyDTO convertToDto(Job job){

            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
        RestTemplate restTemplate = new RestTemplate() ;
            Company company = restTemplate.getForObject("http://localhost:8081/companies/"+job.getCompanyId(), Company.class);
            jobWithCompanyDTO.setCompany(company);
            return jobWithCompanyDTO;

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
