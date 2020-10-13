package encoding.conv.setting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 读取用户的设置；
 * */
public class ReadSetting {
	
	public static ConvSetting readUserSetting() throws IOException {
		
		Properties properties = new Properties();
	    BufferedReader bufferedReader = new BufferedReader(new FileReader("setting.properties"));
	    properties.load(bufferedReader);
	    
	    //获取配置文件中的参数；
	    ConvSetting convsetting = new ConvSetting(
	    		properties.getProperty(ConvSetting.DIRPATH),
	    		properties.getProperty(ConvSetting.ORIENCODING),
	    		properties.getProperty(ConvSetting.DSTENCODING)
	    		);
	    
		return convsetting; 
	}
	
	
	
}
