package prGrading;

public class ArithmeticMean implements AverageCalculation {

	public ArithmeticMean() {
	}

	@Override
	public double calculate(Student[] students) throws StudentException {
		if (students.length <= 0) {
			throw new StudentException("No students");
		}
		double result = 0;
		for (Student s : students) {
			result += s.getGrade();
		}
		return result / students.length;
	}
}
