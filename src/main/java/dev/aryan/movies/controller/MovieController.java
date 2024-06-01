package dev.aryan.movies.controller;//Status Shown
//Mapping of requests
import dev.aryan.movies.dto.MovieDto;
import dev.aryan.movies.entity.Movie;
import dev.aryan.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")

public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return new ResponseEntity<List<MovieDto>>(movieService.allMovies(), HttpStatus.OK);
    }
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<MovieDto>> getSingleMovie(@PathVariable String imdbId)
    {
        Optional<MovieDto> movieDto = movieService.singleMovie(imdbId);

        if (movieDto.isPresent())
        {
                return ResponseEntity.ok(movieDto);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}
