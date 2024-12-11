package com.microservice.reviewms.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }
    @GetMapping
    public ResponseEntity<List<Reviews>> getAllReviews(@RequestParam Long companyId){

        return new ResponseEntity<>(reviewsService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String>  createReview(@RequestParam Long companyId,@RequestBody Reviews reviews){
        boolean createdReview = reviewsService.createReview(companyId,reviews);
        if(createdReview){
            return new ResponseEntity<>("Review created successfully",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Review didn't created successfully",HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Reviews> getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewsService.getReview(reviewId),HttpStatus.OK);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Reviews review){
        boolean updatedReview = reviewsService.updateReview(reviewId,review);
        if(updatedReview){
            return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review didn't updated successfully",HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean reviewDeleted = reviewsService.deleteReview(reviewId) ;
        if(reviewDeleted){
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Review didn't deleted successfully",HttpStatus.NOT_FOUND);
        }
    }
}
