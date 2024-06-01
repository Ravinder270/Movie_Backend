package dev.aryan.movies.controller;

import dev.aryan.movies.dto.ReviewDto;
import dev.aryan.movies.entity.Review;
import dev.aryan.movies.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")

public class ReviewController {
    @Autowired
    private ReviewService reviewService;

//    @PostMapping
//    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload)
//    {
//        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody Map<String, String> payload)
    {
        return new ResponseEntity<ReviewDto>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);
    }

}
