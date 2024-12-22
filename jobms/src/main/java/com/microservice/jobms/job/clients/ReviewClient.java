package com.microservice.jobms.job.clients;

import com.microservice.jobms.job.external.Company;
import com.microservice.jobms.job.external.Reviews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "reviewms")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Reviews> getReviews(@RequestParam("companyId") Long companyId);
}
