package lab05;

import java.util.Comparator;
import java.util.List;

import lab05.database.Database;
import lab05.database.Student;
import lab05.database.Tester;

/**
 * Comparator Practice for Lab 05, CS2420 - Fall 2017 
 * @author ryans
 */
public class ComparatorPractice {
	
	public static void main(String[] args) {
		Database database = new Database();
		Tester tester = new Tester();
		
		/*
		 * This is an example of using a named Comparator Class. 
		 * Check it out at the bottom of this file!
		 */
		tester.sortByName(new NameComparator());
		/** OR */
		/*
		 * This is an example of using a comparator as an "Anonymous Class"
		 */
		tester.sortByName(new Comparator<Student>() {
			@Override
			public int compare(Student leftHandSide, Student rightHandSide) {
				return leftHandSide.name.compareTo(rightHandSide.name);
			}
		});
		/** OR */
		/*
		 * This is an example of using Java 8's Lambda notation.
		 */
		tester.sortByName((leftHandSide, rightHandSide) -> leftHandSide.name.compareTo(rightHandSide.name));
		
		/*
		 * The rest are up to you! How many can you get?
		 * You can test your comparator against the "Database" like so...
		 */
		
		List<Student> studentsOrderedByName = database.getStudentsOrderedBy(new NameComparator()); // or any other Comparator synatx.
		printStudentList(studentsOrderedByName); // comment me out to avoid printint out the entire list.
		
		/* 
		 * Sort the students by their Major alphabetically, 
		 * 	then by their gpas in reverse order (high to low), 
		 * 		and list all the girls before the boys.
		 */
		tester.sortByMajor_ReverseGPA_GirlsFirst((lhs,rhs)->
		{
			int result = lhs.major.compareTo(rhs.major);
			if(result == 0)
			{
				result = Double.compare( rhs.gpa, lhs.gpa);
				if(result == 0)
				{
					return lhs.gender.compareTo(rhs.gender);
				}
			}
			return result; 
			
			
		});
		
		
			
		/*
		 * Sort the students by the number of cast they have, 
		 *   then number of dogs,
		 *      finally by reverse order of their LAST name.
		 *      (All names have the format: First Last)
		 */
		tester.sortBy_numCats_numDogs_ReverseLastName(null);
		
		/*
		 * Sort the students by the length of their name (shorter first)
		 *    then by their uid in lexicographical order.
		 */
		tester.sortByNameLength_UID(null);
		
		/*
		 * Sort by UID in lexicographical order
		 *    sort by GPA (high to low)
		 *       sort by age (Younger to older)
		 */
		tester.sortByUid_ReverseGPA__age(null);
		
		/*
		 * Sort by names that have the fewest number of vowels (aeiou) first
		 *    then by who has more dogs
		 *       then by GPA (high to low)
		 *       
		 *       These are the best students.
		 */
		tester.sortBySmallestNumberOfVowels_Dogs_GPA((lhs,rhs)->
		{
			
		}
		);
		
		tester.runTest();
	}
	
	/**
	 * You can use this method to print out the list returned from the database.
	 * Only use it for debugging purposes.
	 * @param students
	 */
	private static void printStudentList(List<Student> students) {
		students.forEach(System.out::println); // <-- this is some funky Java 8 syntax! 
	}
	
	/**
	 * This is an example of how you can define a class to be a comparator.
	 * Usually this is done if the comparator has dependencies on other data, and needs 
	 * work done inside its constructor.
	 * @author ryans
	 */
	private static class NameComparator implements Comparator<Student> {
		@Override
		public int compare(Student leftHandSide, Student rightHandSide) {
			return leftHandSide.name.compareTo(rightHandSide.name);
		}
		
	}

}
