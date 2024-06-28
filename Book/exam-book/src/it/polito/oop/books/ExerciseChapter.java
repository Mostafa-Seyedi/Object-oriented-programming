package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ExerciseChapter extends Chapter{

    private Set<Question> questions = new HashSet<>();

    public ExerciseChapter(String title, int numPages) {
        super(title, numPages);
    }


    public List<Topic> getTopics() {
        Set<Topic> topics = new HashSet<>(); 
        for (var q : questions) {
            topics.add(q.getMainTopic()); 
        }
        List<Topic> lst = new ArrayList<>(topics); 
        Collections.sort(lst); 
        return lst;
	};
	  

	public void addQuestion(Question question) {
        questions.add(question);
	}	
}
