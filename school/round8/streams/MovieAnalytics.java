import java.util.ArrayList;
import java.util.stream.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.function.Consumer;
import java.util.Comparator;

class MovieAnalytics {
    ArrayList<Movie> movies;

    public MovieAnalytics() {
        this.movies = new ArrayList<>();
    }

    public static Consumer<Movie> showInfo() {
        return movie -> System.out.printf("%s (By %s, %d)%n", movie.getTitle(), movie.getDirector(), movie.getReleaseYear());
    }

    public void populateWithData(String fileName) {
        BufferedReader reader;
       
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {
                String[] parts = line.split(";");
                Movie mv = new Movie(
                    parts[0],
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    parts[3],
                    Double.parseDouble(parts[4]),
                    parts[5]);
                movies.add(mv);
                line=reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error:");
            e.printStackTrace();
        }
    }

    public Stream<Movie> moviesAfter(int year) {
       return movies.stream()
           .filter(mv -> mv.getReleaseYear() >= year)
           .sorted(Comparator.comparing(Movie::getTitle))
           .sorted(Comparator.comparingInt(Movie::getReleaseYear));
    }    

    public Stream<Movie> moviesBefore(int year) {
       return movies.stream()
           .filter(mv -> mv.getReleaseYear() <= year)
           .sorted(Comparator.comparing(Movie::getTitle))
           .sorted(Comparator.comparingInt(Movie::getReleaseYear));
    }

    public Stream<Movie> moviesBetween(int yearA, int yearB) {
        return movies.stream()
            .filter(mv -> mv.getReleaseYear() >= yearA && mv.getReleaseYear() <= yearB)
            .sorted(Comparator.comparing(Movie::getTitle))
            .sorted(Comparator.comparingInt(Movie::getReleaseYear));
    }

    public Stream<Movie> moviesByDirector(String director) {
        return movies.stream()
            .filter(mv -> mv.getDirector().equals(director))
            .sorted(Comparator.comparing(Movie::getTitle))
            .sorted(Comparator.comparingInt(Movie::getReleaseYear));
    }
}
