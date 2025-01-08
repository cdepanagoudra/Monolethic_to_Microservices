package com.microservice.reviewms.reviews.messaging;

import com.microservice.reviewms.reviews.Dto.ReviewMessage;
import com.microservice.reviewms.reviews.Reviews;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ReviewMessageProducer {
    private RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Reviews review){
        ReviewMessage reviewMessage = new ReviewMessage();

        reviewMessage.setId(review.getId());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setCompanyId(review.getCompanyId());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setTitle(review.getTitle());

        rabbitTemplate.convertAndSend("companyRatingQueue",reviewMessage);

    }
}
