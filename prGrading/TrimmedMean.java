package prGrading;

public class TrimmedMean implements AverageCalculation {

	private double min;
	private double max;

	public TrimmedMean(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	@Override
	public double calculate(Student[] students) throws StudentException {
		double avg = 0;
		int cnt = 0;
		for (Student s : students) {
			double grade = s.getGrade();
			if (grade >= min && grade <= max) {
				avg += grade;
				cnt++;
			}
		}
		if (cnt <= 0) {
			throw new StudentException("No students");
		}
		return avg / cnt;
	}

}
