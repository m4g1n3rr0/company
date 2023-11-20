package telran.view;

import java.time.LocalDate;
import java.util.function.Function;

public interface InputOutput {
	
	String readString(String promt);
	void write(String str);
	
	default void writeLine(Object obj) {
		
		write(obj.toString() + "\n");
		
	}
	
	default <T> T readObject(String promt, String errorPromt, Function<String, T> mapper) {
		
		boolean running = false;
		T res = null;
		
		do {
			
			running = false;
			
			try {
				
				String str = readString(promt);
				res = mapper.apply(str);
				
			} catch (Exception e) {
				
				running = true;
				writeLine(errorPromt + ": " + e.getMessage());
				
			}
			
		} while(running);
		
		return res;
		
	}
	
	default int readInt(String promt, String errorPromt) {
		
		return readObject(promt, errorPromt, Integer::parseInt);
		
	}
	
	default int readInt(String promt, String errorPromt, int min, int max) {
		
		return readObject(String.format("%s [%d-%d]", promt, min, max), errorPromt, str -> {
			int num = Integer.parseInt(str);
			if (min < num || num > max) {
				throw new RuntimeException(String.format("Must be in range [%d-%d]", min, max));
			}
			return num;
		});
		
	}
	
	default long readLong(String promt, String errorPromt) {
		
		return readObject(promt, errorPromt, Long::parseLong);
		
	}
	
	default long readLong(String promt, String errorPromt, long min, long max) {
		
		return readObject(String.format("%s [%d-%d]", promt, min, max), errorPromt, str -> {
			long num = Long.parseLong(str);
			if (min < num || num > max) {
				throw new RuntimeException(String.format("Must be in range [%d-%d]", min, max));
			}
			return num;
		});
		
	}
	
	default LocalDate readDate(String promt, String errorPromt) { //example: 2023-11-20
		
		return readObject(promt, errorPromt, LocalDate::parse);
		
	}
	
}
