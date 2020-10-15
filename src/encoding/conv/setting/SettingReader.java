package encoding.conv.setting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 读取用户的设置；
 * */
public class SettingReader {
	
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
		//校验目录；
		if(setting.getParam(setting.dirPath)==null) {
			throw new RuntimeException("转换目录为空！");
		}
		//校验目标格式；
		if(setting.getParam(setting.dstEncoding)==null) {
			setting.putParam(setting.dstEncoding, "utf-8");
		}
		//校验编码识别工具；
		if(setting.getParam(setting.detectingTool)==null) {
			setting.putParam(setting.detectingTool, "juniversalchardet_v1.0.3_new.jar");
		}
	}
	
}
