package prGrading;

public class StudentWithDegree extends Student {
	private Degree degree;
	
	public StudentWithDegree(String dni, String name, String degree) throws StudentException {
		super(dni, name);
		try {
			this.degree = Degree.valueOf(degree.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new StudentException("Wrong degree");
		}
	}
	
	public StudentWithDegree(String dni, String name, String degree, double mark) throws StudentException {
		super(dni, name, mark);
		try {
			this.degree = Degree.valueOf(degree.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new StudentException("Wrong degree");
		}
	}
	
	public StudentWithDegree(String dni, String name, Degree degree) throws StudentException {
		super(dni, name);
		try {
			this.degree = degree;
		} catch (IllegalArgumentException e) {
			throw new StudentException("Wrong degree");
		}
	}
	
	public StudentWithDegree(String dni, String name, Degree degree, double mark) throws StudentException {
		super(dni, name, mark);
		try {
			this.degree = degree;
		} catch (IllegalArgumentException e) {
			throw new StudentException("Wrong degree");
		}
	}
	
	public Degree getDegree() {
		return this.degree;
	}
	
	@Override
	public String toString() {		
		return super.toString() + " " + this.getDegree();
	}
}
