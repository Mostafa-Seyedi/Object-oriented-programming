package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TheoryChapter extends Chapter{

    private String text;
    private Set<Topic> topics = new HashSet<>();

    public TheoryChapter(String title, int numPages, String text) {
        super(title, numPages);
        this.text = text;
    }

    public String getText() {
		return text;
	}

    public void setText(String newText) {
        this.text = newText; 
    }


	public List<Topic> getTopics() {
        List<Topic> lst = new ArrayList<>(topics); 
        Collections.sort(lst); 
        return lst;
	}
    
    public void addTopic(Topic topic) {
        if(!topics.contains(topic)){ 
            topics.add(topic);
            for (var t : topic.getSubTopics()){ 
                addTopic(t);
            } {         
            }
        }
    }
    
}
