import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class fileBytes {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		File testUp = new File("C:\\Users\\a495\\Desktop\\11.zip");
		FileInputStream is = new FileInputStream(testUp);
		int bufferSize = 2097152;// 2097152;
		long contentLength = testUp.length();
		byte[] bytesPortion = new byte[bufferSize];
		System.out.println("File: " + contentLength);
		int i = 1;
		while (is.read(bytesPortion, 0, bufferSize) != -1) {

			byte[] newByteChunk = Arrays.copyOfRange(bytesPortion, 0,
					bufferSize);
			System.out.println("File " + i + ": " + newByteChunk);
			i++;
		}
		is.close();

	}
}
