package cy.ac.ucy.cs.epl231.ID911719.Lab03;

public class Customer {
	int timeOfArrival;
	public int getTimeOfArrival() {
		return timeOfArrival;
	}
	Customer(int time){
		this.timeOfArrival =time;
	}
	public String toString(){
		return Integer.toString(this.timeOfArrival);
	}

}
