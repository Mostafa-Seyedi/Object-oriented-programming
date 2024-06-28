package it.polito.oop.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Book {

    /**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	Map<String, Topic> topics = new TreeMap<>(); 
	Map<Topic, Question> questions = new TreeMap<>(); 
	Map<String, TheoryChapter> theoryChapters = new TreeMap<>(); 
	Map<String, ExerciseChapter> exerciseChapters = new TreeMap<>(); 
	Map<String, Assignment> assignments = new TreeMap<>(); 

	public Topic getTopic(String keyword) throws BookException {

		if(keyword == null || keyword == ""){
			throw new BookException();
		}
		
		if(!topics.containsKey(keyword)){ 
			var topic = new Topic(keyword);
			topics.put(keyword, topic);
		}

	    return topics.get(keyword);
	}



	public Question createQuestion(String question, Topic mainTopic) {
				var q = new Question(question, mainTopic); 
				questions.put(mainTopic, q);
        return q;
	}


	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
				var theoryChapter = new TheoryChapter(title, numPages, text); 
				theoryChapters.put(title, theoryChapter);
        return theoryChapter;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
				var exerciseChapter = new ExerciseChapter(title, numPages); 
				exerciseChapters.put(title, exerciseChapter);
				return exerciseChapter;
	}

	public List<Topic> getAllTopics() {
        return topics.values().stream().toList();
	}

	public boolean checkTopics() {
		for (var t : theoryChapters.values()) {
			for (var e : exerciseChapters.values()) {
				if(t.getTopics().containsAll(e.getTopics())){ 
					return true; 
				}
			}
		}
        return false;
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
		var assignment = new Assignment(ID, chapter); 
		assignments.put(ID, assignment);
        return assignment;
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
			Map<Long, List<Question>> qo = new TreeMap<>(); 
			
			for (var question : questions.values()) {
				Long numAnswers = question.numAnswers(); 
				if(!qo.containsKey(numAnswers)){ 
					qo.put(numAnswers, new ArrayList<>()); 
				}
				qo.get(numAnswers).add(question);
			}
        return qo;
    }
}
