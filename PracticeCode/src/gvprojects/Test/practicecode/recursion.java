package gvprojects.Test.practicecode;
public class recursion {
	public int compareTo(String s, String s2) {
		if (s == null && s2 == null)
			return 0;
		else if (s == null && s2.length() > 0)
			return -1;
		else if (s.length() > 0 && s2 == null)
			return 1;
		else
			return compareToHelper(s, s2, 0);
	}
	private int compareToHelper(String s, String s2, int start) {
		if (s.length() - start == 0)
			return -1;
		else if (s2.length() - start == 0)
			return 1;
		else if (s.charAt(start) == s2.charAt(start))
			return compareToHelper(s, s2, start + 1);
		else
			return s.charAt(start) - s2.charAt(start);
	}
	public static int multiply(int a, int b) {
		return a == 0 ? 0 : multiply(a >> 1, b << 1) + (a % 2 == 1 ? b : 0);
	}
	public int parseInts(String s) {
		if (s.equals(null) == true || s.equals("") == true)
			throw new NumberFormatException("Illegal Number");
		else
			return parseIntHelper(s, 0);
	}
	private static int parseIntHelper(String s, int start) {
		if (s.length() == start)
			return 0;
		else {
			int x = (int) ((s.charAt(start) - 48) * (Math.pow(10, s.length()
					- 1 - start)));
			return x + parseIntHelper(s, start + 1);
		}
	}
	public static String makeStars(int length) {
		if (length == 0)
			return "";
		else
			return '*' + makeStars(length - 1);
	}
	public static void main(String[] arg) {
		recursion x = new recursion();
		System.out.println(x.compareTo("null", "nu"));
		System.out.println(x.parseInts("12"));
		System.out.println("Multi:" + multiply(12, 12));
		System.out.println(multiply(1000, 1000));
		System.out.println(multiply(3, 4));
		System.out.println(5/2);
	}
}
