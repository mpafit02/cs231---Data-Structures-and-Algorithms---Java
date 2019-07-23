package cy.ac.ucy.cs.epl231.ID911719.Lab03;

public class Element {
	public Integer value;
	public Integer time;

	Element(Integer v, Integer t) {
		value = v;
		time = t;
	}
	public String toString(){
		return "Value: " + value + " arived at Time: " + time;
	}
}
