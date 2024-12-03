package com.chetan.firstjobapp.reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
    List<Reviews> findByCompanyId(Long companyId);

}
