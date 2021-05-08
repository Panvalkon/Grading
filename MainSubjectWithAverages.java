import java.util.Locale;
import java.util.Scanner;

import prGrading.ArithmeticMean;
import prGrading.HarmonicMean;
import prGrading.Student;
import prGrading.StudentException;
import prGrading.Subject;
import prGrading.SubjectWithAverages;
import prGrading.TrimmedMean;

public class MainSubjectWithAverages {
	public static void main(String[] args) {
		try {
			String[] data = {
					"25653443S;Garcia Gomez, Juan;8.1", 
					"23322443K;Lopez Turo, Manuel;4.3",
					"24433522M;Merlo Martinez, Juana;5.3",
					"42424312G;Lopez Gama, Luisa;7.1",
					"53553421D;Santana Medina, Petra;-7.1",
					"55343442L,Godoy Molina, Marina;6.3",
					"34242432J;Fernandez Vara, Pedro;2.k" 
					};
			SubjectWithAverages swavg = new SubjectWithAverages("Algebra", data, null); 

			String[] names = { "Lopez Turo, Manuel;23322443k", "Fernandez Vara, Pedro;34242432J" };
			String grades = getGradesOf(swavg, names);
			System.out.print(grades);

			swavg.setAverageCalculation(new ArithmeticMean());
			System.out.printf(Locale.ENGLISH, "Arithmetic Mean: %.2f\n", swavg.getAverage());
			swavg.setAverageCalculation(new HarmonicMean());
			System.out.printf(Locale.ENGLISH, "Harmonic Mean: %.2f\n", swavg.getAverage());
			double min = 5, max = 9;
			swavg.setAverageCalculation(new TrimmedMean(min, max));
			System.out.printf(Locale.ENGLISH, "Mean of values in the range [%.2f, %.2f]: %.2f\n", min, max,	swavg.getAverage());
			String s = getStudents(swavg);
			System.out.println("Students...\n" + s);
			s = getErrors(swavg);
			System.out.println("Errors...\n" + s);
			System.out.println("Subject...\n" + swavg.toString());

		} catch (StudentException e) {
			e.printStackTrace();
		}
	}

	private static String getErrors(SubjectWithAverages swavg) {
		StringBuilder sb = new StringBuilder();
		String[] errors = swavg.getErrors();
		for (int i = 0; i < errors.length - 1; i++) {
			sb.append(errors[i]).append("\n");
		}
		sb.append(errors[errors.length - 1]);
		return new String(sb);
	}

	private static String getStudents(SubjectWithAverages swavg) {
		StringBuilder sb = new StringBuilder();
		Student[] students = swavg.getStudents();
		for (int i = 0; i < students.length - 1; i++) {
			String s = String.format(Locale.ENGLISH, "%s: %.1f\n", students[i].toString(), students[i].getGrade());
			sb.append(s);
		}
		String s = String.format(Locale.ENGLISH, "%s: %.1f", students[students.length - 1].toString(), students[students.length - 1].getGrade());
		sb.append(s);
		return new String(sb);
	}

	/**
	 * 
	 * @param poo   object that contain all pupils that studying an Obj Oriented
	 *              programming
	 * @param names array of students names and DNI that we want to receive the
	 *              information of
	 * @return String that represent the information of of student grades (if
	 *         inscribed); each student's info starts from new line.
	 */
	private static String getGradesOf(Subject poo, String[] names) {
		Student[] stud = poo.getStudents();
		StringBuilder sb = new StringBuilder();
		for (String st : names) {
			try (Scanner sc = new Scanner(st)) {
				sc.useDelimiter("\\s*[;]\\s*");
				sc.useLocale(Locale.ENGLISH);
				String name = sc.next();
				String dni = sc.next();
				Student student = new Student(dni, name);
				int cnt = 0;
				while (cnt < stud.length && !student.equals(stud[cnt])) {
					cnt++;
				}
				if (cnt == stud.length) {
					sb.append("The student ").append(student.toString()).append(" has not been found\n");
				} else {
					sb.append("Grade of ").append(stud[cnt].toString()).append(": ").append(stud[cnt].getGrade())
							.append("\n");
				}
			}
		}
		return new String(sb);
	}
}
