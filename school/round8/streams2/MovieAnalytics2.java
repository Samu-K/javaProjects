import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieAnalytics2 {
    private List<Movie> movies;

    public MovieAnalytics2() {
        this.movies = List.of();
    }

    public void populateWithData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            this.movies = br.lines()
                    .map(line -> line.split(";"))
                    .map(parts -> new Movie(
                            parts[0],
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            parts[3],
                            Double.parseDouble(parts[4]),
                            parts[5]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCountByDirector(int n) {
        Map<String, Long> countByDirector = movies.stream()
                .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));

        countByDirector.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .forEach(entry -> System.out.printf("%s: %d movies\n", entry.getKey(), entry.getValue()));
    }

    public void printAverageDurationByGenre() {
        Map<String, Double> averageDurationByGenre = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingInt(Movie::getDuration)));

        averageDurationByGenre.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.printf("%s: %.2f\n", entry.getKey(), entry.getValue()));
    }

    public void printAverageScoreByGenre() {
        Map<String, Double> averageScoreByGenre = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getScore)));

        averageScoreByGenre.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> System.out.printf("%s: %.2f\n", entry.getKey(), entry.getValue()));
    }
}

