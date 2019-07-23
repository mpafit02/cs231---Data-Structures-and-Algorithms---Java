package cy.ac.ucy.cs.epl231.IDs911719.Lab12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class TestHashing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<Integer, CompanyRecord> companiesHT = new Hashtable<Integer, CompanyRecord>();

		Scanner scanner;
		try {
			scanner = new Scanner(new FileInputStream("records.txt"));
			
			String email;
			String web;
			Integer telno;
			System.out.println("Storing records from a text file to HashTable...");
			while (scanner.hasNextLine()) {
				email = scanner.next();
				web = scanner.next();
				telno = new Integer(scanner.next());
				companiesHT.put(telno, new CompanyRecord(email, web, telno));
				/*** ADD YOUR CODE HERE ***/
			}
			scanner.close();

			// Look for some phone numbers that exist and does not exist in ht
			System.out.println("Searching phone for 80002323 ... : " + companiesHT.get(80002323)
			);

			// Get all the Elements in the Hashtable
			/*** ADD YOUR CODE HERE ***/
			Set<Integer> keys = companiesHT.keySet();
			for (int key : keys) {
				System.out.println(key + " : " + companiesHT.get(key));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
