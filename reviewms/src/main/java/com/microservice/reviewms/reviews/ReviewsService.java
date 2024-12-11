package com.microservice.reviewms.reviews;

import com.microservice.reviewms.reviews.Reviews;

import java.util.List;

public interface ReviewsService {
    List<Reviews> getAllReviews(Long companyId);
    boolean createReview(Long companyId,Reviews reviews);
    Reviews getReview(Long reviewId);

    boolean updateReview(Long reviewId,Reviews review);

    boolean deleteReview(Long reviewId);
}
