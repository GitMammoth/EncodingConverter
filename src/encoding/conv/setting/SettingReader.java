package encoding.conv.setting;

import java.io.BufferedReader;
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
		//校验目录；
		if(StringUtil.isBlank( setting.getParam(setting.dirPath) )) {
			throw new RuntimeException("转换目录为空！");
		}
		//校验目标格式；
		if(StringUtil.isBlank( setting.getParam(setting.dstEncoding) )) {
			setting.putParam(setting.dstEncoding, "utf-8");
		}
		//校验编码识别工具；
		if(StringUtil.isBlank( setting.getParam(setting.detectingTool) )) {
			setting.putParam(setting.detectingTool, "det01");
		}
	}
	
}
