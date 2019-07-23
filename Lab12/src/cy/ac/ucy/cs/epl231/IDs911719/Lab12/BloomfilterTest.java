/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cy.ac.ucy.cs.epl231.IDs911719.Lab12;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 
 */
public class BloomfilterTest {
	static int elementCount = 50000; // Number of elements to test

	public static void main(String[] argv) {

		BloomFilter<String> bf = new BloomFilter<String>(0.001, elementCount);
		Hashtable<String, String> ht = new Hashtable<>();
		final Random r = new Random();
		// Generate elements first
		List<String> existingElements = new ArrayList<>(elementCount);
		for (int i = 0; i < elementCount; i++) {
			byte[] b = new byte[200];
			r.nextBytes(b);
			existingElements.add(new String(b));
		}
		long start = System.currentTimeMillis();
		for (String element : existingElements) {

			ht.put(element, element + "Plus Something else...");
		}
		System.out.println("Time to add " + elementCount + " ht: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		for (String element : existingElements) {
			bf.add(element);
		}
		System.out.println("Time to add " + elementCount + " bf: " + (System.currentTimeMillis() - start));
		// **** Count the False Positives *****
		// ADD another 50000 elements in the list that are not in the Hash Table
		for (int i = 0; i < elementCount; i++) {
			byte[] b = new byte[200];
			r.nextBytes(b);
			existingElements.add(new String(b));
		}
		int NumberOfElements = 0;
		int NumberOfElementsInHT = 0;
		int NumberOfFalsePositive = 0;
		int NumberOfFalseNegative = 0;
		for (String element : existingElements) {
			NumberOfElements++;
			if (bf.contains(element)) {
				if (ht.get(element) != null) {
					NumberOfElementsInHT++;
				} else {
					NumberOfFalsePositive++;
				}
			} else {
				if (ht.get(element) != null) {
					NumberOfFalseNegative++;
				}
			}
		}
		/*** ADD YOUR CODE HERE ***/

		System.out.println("Number of Elemens:" + NumberOfElements + " Number of Elements in HT: "
				+ NumberOfElementsInHT + "\nNumber of False Posistive: " + NumberOfFalsePositive
				+ " Number of False Negative: " + NumberOfFalseNegative);
	}

}
