
import java.io.File;

import myfileio.MyFileIO;

// TODO: Auto-generated Javadoc
/**
 * The Class MyFileIO_Tester.
 */
public class MyFileIO_Tester {

	/**
	 * The main method. 
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		MyFileIO fileIO = new MyFileIO();
		/* You can use this main to test your MyFileIO methods...
		   For example, you might want to get a file handle for a file
		   that you know does not exist, check the various methods that File
		   has, and check what your checkTextFile returns.
		   Then create a new empty file - is it there? does it have the right size?
		   then delete it...
		   Then write a new file (in output/ directory), save and close,
		   then read it...
		   */
		File f;
		System.out.println("Testing getting file handle of null value (return null): " + fileIO.getFileHandle(null));
		System.out.println("Testing getting file handle of valid value (return NewFile): " + fileIO.getFileHandle("NewFile"));
		System.out.println("Testing creation of file that already exists (return false): " + fileIO.createEmptyFile("NewFile"));
		System.out.println("Testing creation of file that doesn't exist (return true): " + fileIO.createEmptyFile("RandomFile"));
		System.out.println("Testing deletion of file (return true): " + fileIO.deleteFile("RandomFile"));
		System.out.println("Testing deletion of file that doesn't exist (return false): " + fileIO.deleteFile(""));
		f = fileIO.getFileHandle("");
		System.out.println("Testing empty file name (return 1): " + fileIO.checkTextFile(f, true));
		f = fileIO.getFileHandle("NewFile");
		System.out.println("Testing writable text file (return 7): " + fileIO.checkTextFile(f, false));
		System.out.println("Testing OpenFileReader with null value (return null): " + fileIO.openFileReader(null));
		System.out.println("Testing OpenFileReader with NewFile value (return fileReader object): " + fileIO.openFileReader(fileIO.getFileHandle("NewFile")));
		System.out.println("Testing openFileWriter with invalid value (return null): " + fileIO.openFileWriter(null));
		System.out.println("Testing openFileWriter with valid value (return fileWriter object): " + fileIO.openFileWriter(fileIO.getFileHandle("RandomFile")));
		System.out.println("Testign oeopnBufferedReader with null value (return null): " + fileIO.openBufferedReader(null));
		System.out.println("Testing openBufferedReader with valid value (return bufferedReader object): " + fileIO.openBufferedReader(fileIO.getFileHandle("NewFile")));
		System.out.println("Testign oeopnBufferedWriter with null value (return null): " + fileIO.openBufferedWriter(null));
		System.out.println("Testing openBufferedWriter with valid value (return bufferedWriter object): " + fileIO.openBufferedWriter(fileIO.getFileHandle("AnotherAnotherFile")));
		System.out.println("Testing close file reader with null value (should throw nullpointerexception:");
		try {
			fileIO.closeFile(fileIO.openFileReader(null));
		} catch(NullPointerException e) {
			System.out.println("Exception caught");
		}
		System.out.println("Testing close file reader with valid value (nothing thrown): ");
		fileIO.closeFile(fileIO.openFileReader(fileIO.getFileHandle("NewFile")));
		System.out.println("Testing close file writer with null value (should throw nullpointerexception:");
		try {
			fileIO.closeFile(fileIO.openFileWriter(null));
		} catch(NullPointerException e) {
			System.out.println("Exception caught");
		}
		System.out.println("Testing close file writer with valid value (nothing thrown): ");
		fileIO.closeFile(fileIO.openFileWriter(fileIO.getFileHandle("NewFile")));
		System.out.println("Testing close buffered file reader with null value (should throw nullpointerexception:");
		try {
			fileIO.closeFile(fileIO.openBufferedReader(null));
		} catch(NullPointerException e) {
			System.out.println("Exception caught");
		}
		System.out.println("Testing close buffered file reader with valid value (nothing thrown): ");
		fileIO.closeFile(fileIO.openBufferedReader(fileIO.getFileHandle("NewFile")));
		System.out.println("Testing close buffered file writer with null value (should throw nullpointerexception:");
		try {
			fileIO.closeFile(fileIO.openBufferedWriter(null));
		} catch(NullPointerException e) {
			System.out.println("Exception caught");
		}
		System.out.println("Testing close buffered file writer with valid value (nothing thrown): ");
		fileIO.closeFile(fileIO.openBufferedWriter(fileIO.getFileHandle("NewFile")));
	}

}
