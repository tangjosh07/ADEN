
// TODO: Auto-generated Javadoc
/**
 * The Class IntegerConverter. This class provides static methods for converting
 * integer, binary and hex strings to specified numeric primitives (int, shor
 */
public class IntegerConvert {

	/**
	 * Instantiates a new integer converter.
	 */
	public IntegerConvert() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Convert string to int.
	 *
	 * @param in the String to convert
	 * @return the converted value of the string represented as an int
	 * @throws NumberFormatException the number format exception
	 */
	public static int parseInt(String in) throws NumberFormatException {
		return 0;
	}

	/**
	 * Convert string to short.
	 *
	 * @param in the String to convert
	 * @return the converted value of the string represented as a short
	 * @throws NumberFormatException the number format exception
	 */
	public static short parseShort(String in) throws NumberFormatException {
		return 0;
	}

	/**
	 * Convert string to byte.
	 *
	 * @param in the String to convert
	 * @return the byte
	 * @throws NumberFormatException the number format exception
	 */
	public static byte parseByte(String in) throws NumberFormatException {
		return 0;
	}
	
	/**
	 * Parses an unsigned binary string and returns the equivalent integer value (signed)
	 *
	 * @param in   the input binary string. Should have a leading "0b"
	 * @return  the integer value of the converted string
	 * @throws NumberFormatException the number format exception
	 */
	public static int parseBinStrToInt(String in) throws NumberFormatException {
		checkValid(in);
		int total = 0;
		int checkRange = 0;
		for (int index = 2; index < in.length(); index++) {
			int[] changedTotal = increaseTotal(in.charAt(index),total, checkRange);
			total = changedTotal[0];
			checkRange = changedTotal[1];
		}
		return total;
	}
	
	/**
	 * Parses an unsigned binary string and returns the equivalent byte value (signed) 
	 *
	 * @param in  the input binary string. Should have a leading "0b"
	 * @return the byte value of the converted string
	 * @throws NumberFormatException the number format exception
	 */
	public static byte parseBinStrToByte(String in) throws NumberFormatException {
		checkValid(in);
		int total = 0;
		byte checkRange = 0;
		for (int index = 2; index < in.length(); index++) {
			if (in.charAt(index) == '0' || in.charAt(index) == '1') {
				checkInRange(total,checkRange);
			}
			int[] changedTotal = increaseTotal(in.charAt(index),total, checkRange);
			total = changedTotal[0];
			checkRange = (byte) changedTotal[1];
		}
		return (byte) total;
	}
	
	/**
	 * Increases the value of the number being converted and also the range checker
	 *
	 * @param c is the character at an index in the input string
	 * @param total is the value of the binary string being converted
	 * @param range the value that makes sure the length of the binary string is in range
	 * @return an int[] of the total and range for the function to store and use
	 */
	public static int[] increaseTotal(char c, int total, int range) {
		if (c == '1') {
			checkInRange(total,range);
			total = total * 2 + 1;
			range = range * 2 + 1;
		}
		else if (c == '0') {
			checkInRange(total,range);
			total = total * 2;
			range = range * 2 + 1;
		}
		else {
			if (c != '_') {
				throw new NumberFormatException("Invalid input format");
			}
		}
		return new int[] {total, range};
	}
	
	/**
	 * Checks if the input is valid
	 * Throws NumberFormatException if input is empty or doesn't start with "0b"
	 *
	 * @param in the input of a binary string
	 */
	public static void checkValid(String in) {
		if (in.length() <= 2) {
			throw new NumberFormatException("Input cannot be empty");
		}
		if (!in.substring(0,2).equals("0b")) {
			throw new NumberFormatException("Invalid input format");
		}
	}
	
	/**
	 * Checks if the total and the range are within bounds
	 * Throws NumberFormatException if it is -1 or out of range
	 *
	 * @param total is the current binary amount
	 * @param checkRange current range of the number
	 */
	public static void checkInRange(int total, int checkRange) {
		if (total == -1 || checkRange == -1) {
			throw new NumberFormatException("Input too large");
		}
	}
	
	/**
	 * Parses an unsigned hex string and returns the equivalent integer value (signed).
	 *
	 * @param in  the input hex string. Should have a leading "0x"
	 * @return the integer value of the converted string
	 * @throws NumberFormatException the number format exception
	 */
	public static int parseHexStrToInt(String in) throws NumberFormatException {
		return 0;
	}
	
	/**
	 * Parses an unsigned hex string and returns the equivalent byte value (signed).
	 *
	 * @param in  the input hex string. Should have a leading "0x"
	 * @return the byte value of the converted string
	 * @throws NumberFormatException the number format exception
	 */
	public static byte parseHexStrToByte(String in) throws NumberFormatException {
		return 0;
	}

	/**
	 * Returns the equivalent unsigned binary string (32 bits)
	 *
	 * @param in the integer to convert
	 * @return the equivalent binary string representation (32 bits)
	 */
	public static String intToBinaryString(int in) {
		return intToBinaryString(in,32);
	}
	
	/**
	 * Returns the equivalent unsigned binary string (8 bits)
	 *
	 * @param in the byte to convert
	 * @return the equivalent binary string representation (8 bits)
	 */
	public static String byteToBinaryString(byte in) {
		return intToBinaryString(in,8);
	}
	
	/**
	 * Returns equivalent unsigned binary string but accounts for length to reuse code
	 *
	 * @param in the in
	 * @param length the length
	 * @return the string
	 */
	public static String intToBinaryString(int in, int length) {
		String str = "";
		String start = "";
		int amount = 1;
		if (in < 0) {
			start = "1";
			in = in + ((length==32)?Integer.MAX_VALUE:127) + 1;
		}
		else {
			start = "0";
		}
		for (int i = 0; i < length-1; i++) {
			str = computeBinValue(in) + str;
			in = in/2;
			amount = amount * 2 + 1;
		}
		return(start +  str);
	}
	
	/**
	 * Computes the binary value of one digit
	 *
	 * @param n the number input
	 * @return the binary value of that place as a string of either 0 or 1
	 */
	public static String computeBinValue(int n) {
		if (n % 2 == 0) {
			return "0";
		}
		return "1";
	}
	
	/**
	 * Returns the equivalent unsigned hex string (8 hex chars)
	 *
	 * @param in the integer to convert
	 * @return the equivalent hex string representation (8 hex chars)
	 */
	public static String intToHexString(int in) {
		return intToHexString(in,32);
	}
	
	/**
	 * Returns the equivalent unsigned hex string (2 hex chars)
	 *
	 * @param in the integer to convert
	 * @return the equivalent hex string representation (2 hex chars)
	 */
	public static String byteToHexString(byte in) {
		return intToHexString(in,8);
	}
	
	/**
	 * Returns equivalent unsigned hex string but accounts for length inorder to reuse code for byteToHexString
	 *
	 * @param in the in
	 * @param length the length
	 * @return the string
	 */
	public static String intToHexString(int in, int length) {
		String str = "";
		String binary = ((length == 32)?intToBinaryString(in) + "_":byteToBinaryString((byte)in) + "_");
		String [] hex = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
		for (int i = 0; i < length; i += 4) {
			int nib = parseBinStrToInt("0b" + binary.substring(i,i+4));
			str = str + hex[nib];
		}
		return(str);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// You should write basic testing of each of your methods here.
		// I will provide a more comprehensive testing using JUnit later.
		System.out.println(parseBinStrToInt("0b1111_1111_1111_1111_1111_1111_1111_1111"));
		System.out.println(parseBinStrToInt("0b11111111"));
		System.out.println(parseBinStrToByte("0b00001111_________"));
		System.out.println(parseBinStrToByte("0b11111110"));
		
		try {
			parseBinStrToInt("");
		} catch (NumberFormatException n) {
			System.out.println(n);
		}
		try {
			System.out.println(parseBinStrToInt("0b11111_1111_1111_1111_1111_1111_1111_1111"));
		} catch (NumberFormatException n) {
			System.out.println(n);
		}
		try {
			parseBinStrToInt("0b9");
		} catch (NumberFormatException n) {
			System.out.println(n);
		}
		try {
			parseBinStrToByte("");
		} catch (NumberFormatException n) {
			System.out.println(n);
		}
		try {
			System.out.println(parseBinStrToByte(("0b1111_1111_1111_1111")));
		} catch (NumberFormatException n) {
			System.out.println(n);
		}
		try {
			parseBinStrToByte("0b9");
		} catch (NumberFormatException n) {
			System.out.println(n);
		}
		
		System.out.println(intToBinaryString(100));
		System.out.println(intToBinaryString(parseBinStrToInt("0b0111_1111_1111_1111_1111_1111_1111_1111")));
		System.out.println(byteToBinaryString((byte)100));
		System.out.println(byteToBinaryString((byte)127));
		System.out.println(intToHexString(100));
		System.out.println(intToHexString(parseBinStrToInt("0b0111_1111_1111_1111_1111_1111_1111_1111")));
		System.out.println(byteToHexString((byte)100));
		System.out.println(byteToHexString((byte)127));
	}

}
