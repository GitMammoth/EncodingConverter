package encoding.conv.setting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import encoding.conv.utils.string.StringUtil;

/**
 * 读取用户的设置；
 * */
public class SettingReader {
	
	/**
	 * 从配置文件中读取用户设置参数，并做校验；最终将参数以对象形式返回；
	 * 
	 * @return 返回用户参数设置的对象
	 * 
	 * */
	public UserConvSetting readUserSetting() throws IOException {
		
		UserConvSetting userSetting = new UserConvSetting();
		
		//获取到配置文件；
		Properties properties = new Properties();
	    BufferedReader bufferedReader = new BufferedReader(new FileReader("usersetting.properties"));
	    properties.load(bufferedReader);
	    
	    //读取配置文件中的参数；
	    Iterator<Map.Entry<Object, Object>> iter = properties.entrySet().iterator();
	    while(iter.hasNext()) {
	    	Map.Entry<Object, Object> entry = iter.next();
	    	userSetting.putParam((String)entry.getKey(), (String)entry.getValue());
	    }
	    
	    //校验用户设置；
	    checkSetting(userSetting);
	    
		return userSetting; 
	}
	
	private void checkSetting(UserConvSetting setting) {
		/**
		 * 校验指定根目录：
		 * 		1. 目录不能为空；
		 * 		2. 该目录必须存在； 
		 */
		String dirPath = setting.getParam(setting.dirPath);
		if(StringUtil.isBlank(dirPath)) {
			throw new RuntimeException("转换目录不能为空！");
		}
		File dirFile = new File(dirPath);
		if(!dirFile.exists()) {
			throw new RuntimeException("指定目录不存在！");
		}
		
		/**
		 * 原编码格式不做校验，如果为空程序会自动识别；
		 * */
		
		
		/**
		 * 校验目标编码格式；
		 * 		1. 如果目标编码为空，则默认为utf-8
		 */
		if(StringUtil.isBlank( setting.getParam(setting.dstEncoding) )) {
			setting.putParam(setting.dstEncoding, "utf-8");
		}
		
		/**
		 * 校验编码识别工具；
		 * 		1. 若编码识别参数为空，则默认指定为det01；
		 * 		2. 若后续程序匹配不上该参数值，也会走默认的det01；
		 */
		if(StringUtil.isBlank( setting.getParam(setting.detectingTool) )) {
			setting.putParam(setting.detectingTool, "det01");
		}
	}
	
}
