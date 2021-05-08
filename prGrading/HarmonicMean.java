package prGrading;

public class HarmonicMean implements AverageCalculation {
	public HarmonicMean() {
	}

	@Override
	public double calculate(Student[] students) throws StudentException {
		if (students.length <= 0) {
			throw new StudentException("No students");
		}
		double avg = 0;
		int cnt = 0;
		for (Student s : students) {
			if (s.getGrade() > 0) {
				avg += 1 / s.getGrade();
				cnt++;
			}
		}
		return cnt / avg;
	}

}
