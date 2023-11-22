package telran.view;
import java.util.*;

public class StandardInputOutput implements InputOutput {
	
	private Scanner scanner = new Scanner(System.in);
	
	@Override
	public String readString(String promt) {
		
		writeLine(promt);
		return scanner.nextLine();
	
	}

	@Override
	public void write(String str) {
	
		System.out.println(str);
		
	}

}
