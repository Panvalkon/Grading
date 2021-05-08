package prGrading;

import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Subject {
	private String name;
	private Student[] students;
	protected String[] errors;

	public Subject(String sbj, String[] data) {
		this.name = sbj;
		errors = new String[data.length];
		students = new Student[data.length];
		processStudents(data);
	}

	private void processStudents(String[] data) {
		int stcnt = 0, errcnt = 0;
		for (String d : data) {
			try (Scanner sc = new Scanner(d)) {
				sc.useLocale(Locale.ENGLISH);
				sc.useDelimiter("\\s*[;]\\s*");
				String dni = sc.next();
				String name = sc.next();
				if (sc.hasNextDouble()) {
					// TODO fix the errors of missing data;
					double grade = sc.nextDouble();
					if (grade > 0) {
						students[stcnt] = new Student(dni, name, grade);
						stcnt++;
					} else {
						errors[errcnt++] = "ERROR. Negative grade: " + d;
					}
				} else {
					errors[errcnt++] = "ERROR. Non numerical grade: " + d;
				}
			} catch (NoSuchElementException e) {
				errors[errcnt++] = "ERROR. Missing data: " + d;
			} catch (StudentException e) {
				// THis should not be happening
			}
		}
		students = Arrays.copyOf(students, stcnt);
		errors = Arrays.copyOf(errors, errcnt);
	}

	public double getGrade(Student st) throws StudentException {
		int pos = 0;
		while (pos < students.length && !st.equals(students[pos])) {
			pos++;
		}
		if (pos == students.length) {
			throw new StudentException(String.format("The student %s has not been found", st.toString()));
		}
		return students[pos].getGrade();
	}

	public double getAverage() throws StudentException {
		double avg = 0;
		if (students.length <= 0) {
			throw new StudentException("No students");
		}
		for (Student st : students) {
			avg += st.getGrade();
		}
		return avg/students.length;
	}

	public String toString() {
		// TODO
		return "";
	}

	public String getName() {
		return name;
	}

	public Student[] getStudents() {
		return students;
	}

	public String[] getErrors() {
		return errors;
	}

}
