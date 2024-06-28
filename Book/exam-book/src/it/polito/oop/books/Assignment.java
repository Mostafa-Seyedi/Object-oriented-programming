package it.polito.oop.books;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Assignment {
    private String studentID; 
    private ExerciseChapter chapter; 
    private Map<Question, Double> scores = new TreeMap<>();

    public Assignment(String studentID, ExerciseChapter chapter) {
        this.studentID = studentID;
        this.chapter = chapter;
    }

    public String getID() {
        return studentID;
    }

    public ExerciseChapter getChapter() {
        return chapter;
    }

    public double addResponse(Question q,List<String> answers) {
        Set<String> correct = q.getCorrectAnswers(); 
        Set<String> incorrect = q.getIncorrectAnswers(); 

        double n = correct.size() + incorrect.size(); 

        double fp = answers.stream().filter(a -> !correct.contains(a)).count(); 
        double fn = correct.stream().filter(a -> !answers.contains(a)).count(); 

        double score = (n - fp- fn) / n; 

        scores.put(q, score);
        
        return score;
    }
    
    public double totalScore() {
        return scores.values().stream().mapToDouble(s -> s).sum();
    }

}
