package prGrading;

import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;

public class SubjectWithDegree extends SubjectWithAverages {

	public SubjectWithDegree(String name, String[] data, prGrading.AverageCalculation ac) {
		super(name, data, ac);
		students = Arrays.copyOf(students, data.length);
		errors = Arrays.copyOf(errors, data.length);
		processStudentsWithDegree(data);
	}
	
	public double getAverage(Degree dgr) throws StudentException {
		int i = 0;
		String[] tmp = new String[students.length];
		for(Student s : students) {
			if (((StudentWithDegree) s).getDegree() == dgr) {
				tmp[i] = String.format(Locale.ENGLISH,"%s;%s;%s" , s.getDni(), s.getName(), String.valueOf(s.getGrade()));
				i++;
			}
		}
		tmp = Arrays.copyOf(tmp, i);
		SubjectWithAverages aa = new SubjectWithAverages("Algebra", tmp, this.getAverageCalculation());
		
		return aa.getAverage();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(": {[");
		for (Degree d : Degree.values()) {
			for (int i = 0; i < students.length - 1; i++) {
				if (d == ((StudentWithDegree) students[i]).getDegree()) {
					sb.append(students[i].toString()).append(", ");
				}
			}
			if (d == ((StudentWithDegree) students[students.length - 1]).getDegree()) {
				sb.append(students[students.length - 1].toString()).append(" , ");
			}
		}
		String s = new String(sb);
		s = s.substring(0, s.length() -2);
		sb = new StringBuilder(s);
		sb.append("], [");
		for (int i = 0; i < errors.length - 1; i++) {
			sb.append(errors[i]).append(", ");
		}
		sb.append(errors[errors.length - 1]).append("]}");
		return new String(sb);
	}

	private void processStudentsWithDegree(String[] data) {
		int stcnt = 0, errcnt = 0;
		for (String d : data) {
			try {

				String[] std = d.split("\\s*[;]\\s*");
				if (std.length < 4) {
					throw new NoSuchElementException();
				}
				String dni = std[0];
				String name = std[1];
				String degree = std[2];
				Double grade = Double.parseDouble(std[3]);
				if (grade <= 0) {
					throw new RuntimeException();
				}
				students[stcnt] = new StudentWithDegree(dni, name, degree, grade);
				stcnt++;

			} catch (NoSuchElementException e) {
				errors[errcnt++] = "ERROR. Missing data: " + d;
			} catch (NumberFormatException e) {
				errors[errcnt++] = "ERROR. Non numerical grade: " + d;
			} catch (StudentException e) {
				errors[errcnt++] = "ERROR. Wrong degree: " + d;
			} catch (RuntimeException e) {
				errors[errcnt++] = "ERROR. Negative grade: " + d;
			}
		}
		students = Arrays.copyOf(students, stcnt);
		errors = Arrays.copyOf(errors, errcnt);
	}
}
