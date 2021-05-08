package prGrading;

public class MainSubject {

	public static void main(String[] args) {
		String[] data = { "25653443S;Garcia Gomez, Juan;8.1", "23322443K;Lopez Turo, Manuel;4.3",
				"24433522M;Merlo Martinez, Juana;5.3", "53553421D;Santana Medina, Petra;-7.1",
				"55343442L,Godoy Molina, Marina;6.3", "34242432J;Fernandez Vara, Pedro;2.k",
				"42424312G;Lopez Gama, Luisa;7.1" };
		Subject poo = new Subject("POO", data);
	}
}
