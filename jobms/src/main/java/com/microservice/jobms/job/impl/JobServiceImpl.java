package com.microservice.jobms.job.impl;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.microservice.jobms.job.Job;
import com.microservice.jobms.job.JobRepository;
import com.microservice.jobms.job.JobService;
import com.microservice.jobms.job.clients.CompanyClient;
import com.microservice.jobms.job.clients.ReviewClient;
import com.microservice.jobms.job.dto.JobDTO;
import com.microservice.jobms.job.external.Company;
import com.microservice.jobms.job.external.Reviews;
import com.microservice.jobms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    RestTemplate restTemplate;
    int attempt =0;
    private CompanyClient companyClient;
    private ReviewClient reviewClient;
    public JobServiceImpl(JobRepository jobRepository,CompanyClient companyClient,ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }

    //private Long nextId = 1L;
    @Override
//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
   //@Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreaker")
    public List<JobDTO> findAll() {
        System.out.println("Attempts : "+ ++attempt);
        List<Job> jobs = jobRepository.findAll();
        for(Job job : jobs){
            System.out.println(job.getDescription() + "this is jobs object");
        }
        //List<JobDTO> jobDTOS = new ArrayList<>();


        return jobs.stream().map(this ::convertToDto).collect(Collectors.toList());
    }
    public List<JobDTO> companyBreaker(Exception s){
        System.out.println("Rate limiter triggered. Returning fallback response.");
        JobDTO fallbackJob = new JobDTO();
        fallbackJob.setTitle("Fallback Job");
        fallbackJob.setDescription("This is a fallback job description.");
        fallbackJob.setLocation("Fallback Location");
        return List.of(fallbackJob);
    }

    private JobDTO convertToDto(Job job){
//            System.out.println("Entering into company object");
            Company company = companyClient.getCompany(job.getCompanyId());
            //System.out.println("Got the company"+ company.getName() + " this is the company id : "+company.getId());
            List<Reviews> reviews = reviewClient.getReviews(job.getCompanyId());
//          RestTemplate restTemplate = new RestTemplate() ;
            //Company company = restTemplate.getForObject("http://companyms:8081/companies/"+job.getCompanyId(), Company.class);
//            ResponseEntity<List<Reviews>> reviewResponse =  restTemplate.exchange("http://reviewms:8083/reviews?companyId=" +
//                            job.getCompanyId(), HttpMethod.GET, null,
//                    new ParameterizedTypeReference<List<Reviews>>() {
//                    });
//            List<Reviews> reviews = reviewResponse.getBody();
            JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job,company,reviews);
            //jobDTO.setCompany(company);
            return jobDTO;

    }

    @Override
    public void createJob(Job j) {
        //job.setId(nextId++);
//        System.out.println(j.getCompanyId()+" company id");
//        System.out.println(j.getDescription()+" company description");
//        System.out.println(j.getLocation()+" company location");
//        System.out.println(j.getMaxSalary()+" max salary");
//        System.out.println(j.getMinSalary()+" min salary");
//        System.out.println(j.getTitle()+" title");
        jobRepository.save(j);
    }

    @Override
    public JobDTO getJobId(Long i) {
        Job job= jobRepository.findById(i).orElse(null);
        return convertToDto(job);
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
