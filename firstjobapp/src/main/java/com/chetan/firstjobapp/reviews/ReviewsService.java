package com.chetan.firstjobapp.reviews;

import java.util.List;

public interface ReviewsService {
    List<Reviews> getAllReviews(Long companyId);
    boolean createReview(Long companyId,Reviews reviews);
    Reviews getReview(Long companyId,Long reviewId);

    boolean updateReview(Long reviewId,Long companyId,Reviews review);
}
