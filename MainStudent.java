import prGrading.Student;
import prGrading.StudentException;

public class MainStudent {

	public static void main(String[] args) {
		try {
			Student s1 = new Student("22456784F", "Gonzalez Perez, Juan", 5.5);
			Student s2 = new Student("33456777S", "Gonzalez Perez, Juan", 3.4);
			System.out.printf("The students %s and %s are %s\n", s1.toString(), s2.toString(), s1.equals(s2) ? "equal" : "not equal");
			Student s3 = new Student("22456784F", "Gonzalez Perez, Juan", -3.4);
		} catch (StudentException e) {
			e.printStackTrace();
		}
	}

}
