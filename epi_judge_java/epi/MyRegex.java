package epi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegex {

	public static void main(String[] args) {
		Solution solution = new Solution();
		solution.myAtoi("-20000000000000000000");
	}

}

class Solution {
	public int myAtoi(String str) {
		Pattern p = Pattern.compile("^ *(-|\\+?)0*([1-9]\\d*).*$");
		Matcher m = p.matcher(str);
		try {
			m.matches();
			String sign = m.group(1);
			String number = m.group(2);
			int result = 0;
			if (sign.equals("") || sign.equals("+")) {
				try {
					result = (int) Math.min(Integer.MAX_VALUE, Long.parseLong(number));
				} catch (Exception e) {
					result = Integer.MAX_VALUE;
				}
			} else {
				try {
					result = (int) Math.max(Integer.MIN_VALUE, Long.parseLong(sign + number));
				} catch (Exception e) {
					result = Integer.MIN_VALUE;
				}
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
}
