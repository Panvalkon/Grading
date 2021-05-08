package prGrading;

public class Student {
	private String dni;
	private String name;
	private double grade;

	public Student(String dni, String name) {
		this.name = name;
		this.dni = dni;
	}

	public Student(String dni, String name, double grade) throws StudentException {
		this(dni, name);
		if (grade < 0) {
			throw new StudentException("Negative grade");
		}
		this.grade = grade;
	}

	public boolean equals(Object o) {
		boolean ans = false;
		if (o instanceof Student && ((Student) o).dni.equalsIgnoreCase(this.dni) && ((Student) o).name.equals(this.name)) {
			ans = true;
		}
		return ans;
	}

	public int hashCode() {
		return this.name.hashCode() * 5 + this.dni.toLowerCase().hashCode() * 7;
	}

	public String toString() {
		return String.format("%s (%s)", this.name, this.dni);
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public double getGrade() {
		return grade;
	}

}
