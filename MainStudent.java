import prGrading.Student;
import prGrading.StudentException;

public class MainStudent {

	public static void main(String[] args) {
		try {
			Student s1 = new Student("22456784F", "Gonzalez Perez, Juan", 5.5);
			Student s2 = new Student("33456777S", "Gonzalez Perez, Juan", 3.4);
			StringBuilder s = new StringBuilder();
			s.append("DNI: ").append(s1.getDni()).append(", Name: ").append(s1.getName()).append(", Grade: ").append(s1.getGrade());
			s.append("\nDNI: ").append(s2.getDni()).append(", Name: ").append(s2.getName()).append(", Grade: ").append(s2.getGrade());
			System.out.println(s);
			
			System.out.printf("The students %s and %s are %s\n", s1.toString(), s2.toString(), s1.equals(s2) ? "equal" : "not equal");
			Student s3 = new Student("22456784F", "Gonzalez Perez, Juan", -3.4);
		} catch (StudentException e) {
			e.printStackTrace();
		}
	}

}
