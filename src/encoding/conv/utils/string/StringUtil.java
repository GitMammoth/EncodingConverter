package encoding.conv.utils.string;
/**
 * 字符串相关的工具类；
 * */
public class StringUtil {
	
	/**
	 * 校验字符串是否为null或者空串(包括只含有空白字符)
	 * */
	public static boolean isBlank(String s) {
		if(s==null || s.trim().length()<1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否不是空串；
	 * */
	public static boolean isNotBlank(String s) {
		return !isBlank(s);
	}
	
	
}
