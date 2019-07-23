package cy.ac.ucy.cs.epl231.IDs911719.homework2;

import java.io.Serializable;
import java.util.ArrayList;

public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;
	private int counter;
	private SkipList docID = new SkipList();
	private ArrayList<ArrayList<Integer>> positions = new ArrayList<>();

	public Word(String content, int id, int position) {
		this.content = content;
		this.counter = 1;
		createDocID(id, position);
	}

	public String getContent() {
		return this.content;
	}

	public int getCounter() {
		return this.counter;
	}

	public SkipList getDocID() {
		return this.docID;
	}

	public ArrayList<ArrayList<Integer>> getPositions() {
		return this.positions;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void increaseCounter() {
		this.counter++;
	}

	private String printDocID() {
		String docIDOutput = "[ ";
		for (int i = 0; i < docID.size(); i++) {
			docIDOutput += docID.get(i) + " ";
		}
		return docIDOutput += "]";
	}

	private String printPositions() {
		String posOutput = "";
		for (int i = 0; i < positions.size(); i++) {
			posOutput += " " + docID.get(i) + ": { ";
			for (int j = 0; j < positions.get(i).size(); j++) {
				posOutput += positions.get(i).get(j) + " ";
			}
			posOutput += "}";
		}
		return posOutput;
	}

	private void createDocID(int id, int position) {
		docID.add(id);
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(position);
		positions.add(temp);
	}

	public void updateDocID(int id, int position) {
		if (!docID.contains(id)) {
			docID.add(id);
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(position);
			positions.add(temp);
		} else {
			int index = docID.indexOf(id);
			ArrayList<Integer> temp = new ArrayList<>();
			temp = positions.get(index);
			temp.add(position);
			positions.set(index, temp);
		}
		counter++;
	}

	public String toString() {
		return this.content + "   : " + this.counter + "   " + printDocID() + " | " + printPositions();
	}
}
