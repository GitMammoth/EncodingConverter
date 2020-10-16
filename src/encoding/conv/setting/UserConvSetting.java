package encoding.conv.setting;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类对应用户配置文件中的转换参数设置；
 * */
public class UserConvSetting {
	
	//用户参数设置选项；
	public final String dirPath = "dirPath";	
	public final String oriEncoding = "oriEncoding";
	public final String dstEncoding = "dstEncoding";
	public final String detectingTool = "detectingTool";
	
	//存放用户设置的参数；以静态方式存放；
	private static final Map<String,String> userSetting = new HashMap<>();
	
	//构造器包内访问权限；
	UserConvSetting(){
		
	}
	
	//添加参数；
	public void putParam(String key, String value) {
		userSetting.put(key, value);
	}
	//获取参数；
	public String getParam(String param) {
		return userSetting.get(param);
	}
	
}
