package com.microservice.reviewms.reviews;

import com.microservice.reviewms.reviews.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
    List<Reviews> findByCompanyId(Long companyId);

}
