import java.util.Locale;
import java.util.Scanner;

import prGrading.Student;
import prGrading.StudentException;
import prGrading.Subject;

public class MainSubject {
	
		public static void main(String[] args) {
			try{
			String[] data = { "12455666F;Lopez Perez, Pedro;6.7",
							  "33678999D;Merlo Gomez, Isabel;5.8",
							  "23555875G;Martinez Herrera, Lucia;9.1"
							  };
			Subject poo = new Subject("POO", data);
			String dni = getDni(poo);
			String[] names = {
					"Lopez Perez, Pedro;12455666F",
					"Lopez Lopez, Pedro;12455666F"
			};
			String grades = getGradesOf(poo, names);
			System.out.printf(Locale.ENGLISH, "Average %.2f\nDNIs: %s%s", poo.getAverage(), dni, grades);
		}
		catch (StudentException e) {
			// Should not happen;
		}
	}
		
	/**
	 * 	
	 * @param poo object that contain all pupils that studying an Obj Oriented programming
	 * @return a string of pupils and their notes
	 */
	private static String getDni(Subject poo) {
		StringBuilder sb = new StringBuilder();
		Student[] stud = poo.getStudents();
		for(int i = 0; i<stud.length - 1; i++ ) {
			sb.append(stud[i].getDni()).append(", ");
		}
		sb.append(stud[stud.length - 1].getDni());
		return new String(sb);
	}
	
	/**
	 * 
	 * @param poo object that contain all pupils that studying an Obj Oriented programming
	 * @param names array of students names and DNI that we want to receive the information of
	 * @return String that represent the information of of student grades (if inscribed); each student's info starts from new line.
	 */
	private static String getGradesOf(Subject poo, String[] names) {
		Student[] stud = poo.getStudents();
		StringBuilder sb = new StringBuilder();
		for (String st : names) {
			try (Scanner sc = new Scanner(st)){
				sc.useDelimiter("\\s*[;]\\s*");
				sc.useLocale(Locale.ENGLISH);
				String name = sc.next();
				String dni = sc.next();
				Student student = new Student(dni, name);
				int cnt = 0;
				while (cnt < stud.length && !student.equals(stud[cnt])) {
					cnt++;
				}
				if ( cnt == stud.length){
					sb.append("\nThe student ").append(student.toString()).append(" has not been found");
				} else {
					sb.append("\nGrade of ").append(stud[cnt].toString()).append(": ").append(stud[cnt].getGrade());
				}
			}
		}
		return new String(sb);
	}
	
}