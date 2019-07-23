package cy.ac.ucy.cs.epl231.IDs911719.Lab06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestPhoneRecords {

	public static void main(String args[]) {

		try {
			ArrayList<CompanyRecordCompareByEmail> byEmail = new ArrayList<CompanyRecordCompareByEmail>();

			Scanner scanner;
			scanner = new Scanner(new FileInputStream("records.txt"));
			String email;
			String web;
			String telno;
			while (scanner.hasNextLine()) {
				email = scanner.next();
				web = scanner.next();
				telno = scanner.next();
				byEmail.add(new CompanyRecordCompareByEmail(email, web, telno));
			}
			scanner.close();

			CompanyRecordCompareByEmail records[] = new CompanyRecordCompareByEmail[byEmail.size()];
			for (int i = 0; i < records.length; i++)
				records[i] = byEmail.get(i);

			Sort.quicksort(records);
			// Sort.oddEvenMergeSort(records); // We need to have ^2 records in this case
			// 512
			for (int i = 0; i < records.length; i++)
				System.out.println(records[i]);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class CompanyRecord {
	private String email;
	private String web;
	private String telno;

	CompanyRecord(String email, String web, String telno) {
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

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getTelno() {
		return this.telno;
	}

	@Override
	public String toString() {
		return email + "\t" + web + "\t" + telno;
	}

}

class CompanyRecordCompareByEmail extends CompanyRecord implements Comparable<CompanyRecordCompareByEmail> {
	CompanyRecordCompareByEmail(String email, String web, String telno) {
		super(email, web, telno);
	}

	public int compareTo(CompanyRecordCompareByEmail arg0) {
		return this.getEmail().compareTo((arg0).getEmail());
	}
}
