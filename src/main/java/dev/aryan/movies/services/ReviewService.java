package dev.aryan.movies.services;

import dev.aryan.movies.entity.Movie;
import dev.aryan.movies.entity.Review;
import dev.aryan.movies.dto.ReviewDto;
import dev.aryan.movies.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

//    public Review createReview(String reviewBody, String imdbId) {
//        Review review = reviewRepository.insert(new Review(reviewBody));
//
//        mongoTemplate.update(Movie.class)
//                .matching(Criteria.where("imdbId").is(imdbId))
//                .apply(new Update().push("reviewIds").value(review))
//                .first();
//        return review;
//    }
public ReviewDto createReview(String reviewBody, String imdbId) {
    Review review = reviewRepository.insert(new Review(reviewBody));
    ReviewDto reviewDto = new ReviewDto();

    mongoTemplate.update(Movie.class)
            .matching(Criteria.where("imdbId").is(imdbId))
            .apply(new Update().push("reviewIds").value(review))
            .first();
    return modelMapper.map(review, ReviewDto.class);
}

}