package application;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class QuestionsM implements Serializable{
	public ArrayList<String> list;
	
	public QuestionsM() {
		list=new ArrayList<String>();
	}
}
