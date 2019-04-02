package Tools;

public class NumericTool {
	public static boolean isNumeric(String s) {
		boolean res=true;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)<'0' || s.charAt(i)>'9') {
				res=false;
				break;
			}
		}
		return res;
	}

}
