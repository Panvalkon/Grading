package prGrading;

public class SubjectWithAverages extends Subject {

	private AverageCalculation AverageCalculation;

	public SubjectWithAverages(String name, String[] data, AverageCalculation ac) {
		super(name, data);
		this.AverageCalculation = ac;
	}

	public AverageCalculation getAverageCalculation() {
		return AverageCalculation;
	}

	public void setAverageCalculation(AverageCalculation ac) {
		this.AverageCalculation = ac;
	}

	@Override
	public double getAverage() throws StudentException {
		return AverageCalculation.calculate(getStudents());
	}
}
