package cy.ac.ucy.cs.epl231.IDs911719.homework1;

public class Url_911719 {

	// Variables
	private int priority;
	private String link;
	private String domain;

	// Constructors
	public Url_911719(String link, int priority) { // Initialize URL with a link and a priority
		this.link = link;
		this.priority = priority;
		this.domain = link.split("/")[0]; // Find the Domain of the URL
	}

	// Getters
	public int getPriority() { // Get the priority of the URL
		return priority;
	}

	public String getLink() { // Get the link of the URL
		return link;
	}

	public String getDomain() { // Get the Domain of the URL
		return domain;
	}

	// Setters
	public void setPriority(int priority) { // Set the priority of the URL
		this.priority = priority;
	}

	// Methods
	public String toString() { 
		return link + " (" + priority + ")";
	}
}
