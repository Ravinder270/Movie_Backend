package dev.aryan.movies.services;
//Database Access Methods Written here
//Business logic written here

import dev.aryan.movies.config.ModelMapperConfig;

import dev.aryan.movies.dto.MovieDto;
import dev.aryan.movies.entity.Movie;
import dev.aryan.movies.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper)
    {
        this.movieRepository =movieRepository;
        this.modelMapper =modelMapper;
    }

    public List<MovieDto> allMovies(){
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto>movieDtos =new ArrayList<>();

        for(Movie movie: movies)
        {
            MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
            movieDtos.add(movieDto);
        }
        return movieDtos;
    }

//    public List<Movie> allMovies() {
//        return movieRepository.findAll();
//    }
    public Optional<MovieDto> singleMovie(String imdbId)
    {
        Optional<Movie> movie = movieRepository.findMovieByImdbId(imdbId);
        return movie.map(p -> modelMapper.map(p, MovieDto.class)); // important
    }


//    public Optional<Movie> singleMovie(String imdbId){
//        return movieRepository.findMovieByImdbId(imdbId);
//    }
}
