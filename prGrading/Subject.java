package prGrading;

import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Subject {
	private String name;
	protected Student[] students;
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
				String mark = sc.next();
				Double grade = Double.parseDouble(mark);
				if (grade < 0) {
					throw new StudentException();
				}
				students[stcnt] = new Student(dni, name, grade);
				stcnt++;
			} catch (NoSuchElementException e) {
				errors[errcnt++] = "ERROR. Missing data: " + d;
			} catch (NumberFormatException e) {
				errors[errcnt++] = "ERROR. Non numerical grade: " + d;
			} catch (StudentException e) {
				errors[errcnt++] = "ERROR. Negative grade: " + d;
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
		return avg / students.length;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(": {[");
		for (int i = 0; i < students.length - 1; i++) {
			sb.append(students[i].toString()).append(", ");
		}
		sb.append(students[students.length - 1].toString()).append("], [");
		for (int i = 0; i < errors.length - 1; i++) {
			sb.append(errors[i]).append(", ");
		}
		sb.append(errors[errors.length - 1]).append("]}");
		return new String(sb);
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
