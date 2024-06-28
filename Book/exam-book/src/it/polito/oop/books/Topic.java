package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Topic implements Comparable<Topic>{

	private String keyword; 
	private Set<Topic> subTopics = new HashSet<>(); 
	
	public Topic(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
        return keyword;
	}
	
	@Override
	public String toString() {
	    return keyword;
	}

	public boolean addSubTopic(Topic topic) {
		if(!subTopics.contains(topic)){
			subTopics.add(topic); 
			return true; 
		}
      return false;
	}

	/*
	 * Returns a sorted list of subTopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
			List<Topic> lst = new ArrayList<>(subTopics);
			Collections.sort(lst);  
			return lst;
	}

	@Override
	public int compareTo(Topic o) {
		return keyword.compareTo(o.keyword); 
	}
}
