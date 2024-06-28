package it.polito.oop.books;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Question implements Comparable<Question>{
	private String question; 
	private Topic mainTopic; 
	private Map<String, Boolean> answers = new TreeMap<>();
	
	public Question(String question, Topic mainTopic) {
		this.question = question;
		this.mainTopic = mainTopic;
	}

	public String getQuestion() {
		return question;
	}
	
	public Topic getMainTopic() {
		return mainTopic;
	}

	public void addAnswer(String answer, boolean correct) {
		answers.put(answer, correct);
	}
	
    @Override
    public String toString() {
        return question + "(" + mainTopic + ")";
    }

	public long numAnswers() {
	    return answers.size();
	}

	public Set<String> getCorrectAnswers() {
		Set<String> r = new HashSet<>();
		for (var answer : answers.keySet()) 
			if(answers.get(answer)){ 
				r.add(answer);
			}
			return r;
	}

	public Set<String> getIncorrectAnswers() {
		Set<String> w = new HashSet<>();
		for (var answer : answers.keySet()) 
			if(!answers.get(answer)){ 
				w.add(answer);
			}
			return w;
	}

	@Override
	public int compareTo(Question o) {
		return this.question.compareTo(o.question);
	}

}
