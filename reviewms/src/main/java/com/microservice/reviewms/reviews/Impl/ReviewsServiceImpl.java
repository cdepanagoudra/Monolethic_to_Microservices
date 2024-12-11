package com.microservice.reviewms.reviews.Impl;


import com.microservice.reviewms.reviews.Reviews;
import com.microservice.reviewms.reviews.ReviewsRepository;
import com.microservice.reviewms.reviews.ReviewsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;



    public ReviewsServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;

    }

    @Override
    public List<Reviews> getAllReviews(Long companyId) {
        List<Reviews> review = reviewsRepository.findByCompanyId(companyId);

        return review;
    }

    @Override
    public boolean createReview(Long companyId ,Reviews reviews) {


        if(companyId!=null && reviews != null){
            reviews.setCompanyId(companyId);
            reviewsRepository.save(reviews);
            return true;
        }
        else{
            return false;
        }

    }
    public Reviews getReview(Long reviewId){
        return reviewsRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Reviews reviews = reviewsRepository.findById(reviewId).orElse(null);
        if(reviews != null){
            reviewsRepository.delete(reviews);

            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean updateReview( Long reviewId,Reviews updatedReview) {
        Reviews reviews = reviewsRepository.findById(reviewId).orElse(null);
        if(reviews != null){
                reviews.setDescription(updatedReview.getDescription());
                reviews.setTitle(updatedReview.getTitle());
                reviews.setRating(updatedReview.getRating());
                reviews.setCompanyId(updatedReview.getCompanyId());
                reviewsRepository.save(reviews);
                return true;
        }else{
            return false;
        }

    }
}
