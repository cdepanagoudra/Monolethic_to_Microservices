package com.chetan.firstjobapp.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewsController {
    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Reviews>> getAllReviews(@PathVariable Long companyId){

        return new ResponseEntity<>(reviewsService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String>  createReview(@PathVariable Long companyId,@RequestBody Reviews reviews){
        boolean createdReview = reviewsService.createReview(companyId,reviews);
        if(createdReview){
            return new ResponseEntity<>("Review created successfully",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Review didn't created successfully",HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Reviews> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewsService.getReview(companyId,reviewId),HttpStatus.OK);
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@PathVariable Long companyId,@RequestBody Reviews review){
        boolean updatedReview = reviewsService.updateReview(reviewId,companyId,review);
        if(updatedReview){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review didn't updated successfully",HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean reviewDeleted = reviewsService.deleteReview(companyId,reviewId) ;
        if(reviewDeleted){
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review didn't deleted successfully",HttpStatus.NOT_FOUND);
        }
    }
}
