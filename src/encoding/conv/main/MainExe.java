package encoding.conv.main;

import java.io.IOException;

import encoding.conv.setting.*;

public class MainExe {
	
	private static ConvSetting convsetting = null;
	
	public static void main(String[] args) throws IOException {
		
		convsetting = ReadSetting.readUserSetting();
		
		/* 读取文件测试通过；
		System.out.println( convsetting.getDirPath() );
		System.out.println( convsetting.getOriEncoding() );
		System.out.println( convsetting.getDstEncoding() );
		*/
		
		
		
	}
}
