package encoding.conv.process.detector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.mozilla.universalchardet.UniversalDetector;

/**
 * 该编码格式识别器对应第三方包提供的功能：juniversalchardet_v1.0.3_new.jar
 */
public class Juniversalchardet_v103 implements CodeDetector {
	
	//构造器为包内访问权限；
	Juniversalchardet_v103(){
		
	}
	
	@Override
	public String recognizeEncoding(File curFile) throws IOException {
		
		byte[] buf = new byte[4096];

		// 通过待检测的文件创建文件输入流
		FileInputStream fis = new FileInputStream(curFile);
		// (1)
		UniversalDetector detector = new UniversalDetector(null);
		// (2)
		int nread;
		while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
			detector.handleData(buf, 0, nread);
		}
		// (3)
		detector.dataEnd();
		// (4)
		String encoding = detector.getDetectedCharset();
		// (5)
		detector.reset();
		fis.close();
		return encoding;
	}

	@Override
	public List<String> recognizeEncoding(List<File> fileList) throws IOException {
		List<String> codeList = new ArrayList<>();
		String code = "";
		for(int i=0; i<fileList.size(); i++) {
			code = recognizeEncoding(fileList.get(i));
			codeList.add(code);
		}
		return codeList;
	}

}
