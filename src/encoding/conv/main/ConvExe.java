package encoding.conv.main;

import java.io.IOException;

import encoding.conv.process.ConvProcessor;
import encoding.conv.setting.SettingReader;
import encoding.conv.setting.UserConvSetting;

public class ConvExe {
		
	public static void main(String[] args) throws IOException {
		
		//获取用户配置信息，当前配置信息已经做了校验处理；
		SettingReader settingReader = new SettingReader();
		UserConvSetting ust = settingReader.readUserSetting();
		
		//获取转换处理器，传入用户参数对象做对应的转换；
		ConvProcessor convp = new ConvProcessor();
		convp.convProcess(ust);
		
		
		System.out.println("-------success-------");
	}
}
