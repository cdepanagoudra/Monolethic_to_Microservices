package com.chetan.firstjobapp.reviews.Impl;

import com.chetan.firstjobapp.Company.Company;
import com.chetan.firstjobapp.Company.CompanyService;
import com.chetan.firstjobapp.reviews.Reviews;
import com.chetan.firstjobapp.reviews.ReviewsRepository;
import com.chetan.firstjobapp.reviews.ReviewsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;

    private final CompanyService companyService;

    public ReviewsServiceImpl(ReviewsRepository reviewsRepository,CompanyService companyService) {
        this.reviewsRepository = reviewsRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Reviews> getAllReviews(Long companyId) {
        List<Reviews> review = reviewsRepository.findByCompanyId(companyId);

        return review;
    }

    @Override
    public boolean createReview(Long companyId ,Reviews reviews) {

        Company company = companyService.getCompanyById(companyId);
        if(company!=null){
            reviews.setCompany(company);
            reviewsRepository.save(reviews);
            return true;
        }
        else{
            return false;
        }

    }
    public Reviews getReview(Long companyId, Long reviewId){
        List<Reviews> reviews= reviewsRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId,Long companyId,Reviews updatedReview) {
        if(companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewsRepository.save(updatedReview);
            return true;
        }else{
            return false;
        }

    }
}
