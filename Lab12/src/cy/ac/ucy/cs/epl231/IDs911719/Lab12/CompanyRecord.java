package cy.ac.ucy.cs.epl231.IDs911719.Lab12;

public class CompanyRecord {
	private String email;
	private String web;
	private Integer telno;

	CompanyRecord(String email, String web, Integer telno) {
		this.email = email;
		this.web = web;
		this.telno = telno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public void setTelno(Integer telno) {
		this.telno = telno;
	}

	public Integer getTelno() {
		return this.telno;
	}

	@Override
	public String toString() {
		return web + "\t" + email + "\t" + telno;
	}

}
