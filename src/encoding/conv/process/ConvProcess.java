package encoding.conv.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mozilla.universalchardet.UniversalDetector;

/**
 * 转换过程
 */
public class ConvProcess {
	
	/**
	 * 识别一个文件列表中的所有文件的编码格式；
	 * 
	 * @param fileList 待识别的所有文件
	 * @return 返回[文件-编码格式]的映射
	 * 
	 * @throws IOException
	 * */
	public Map<File, String> recognizeEncoding(List<File> fileList) throws IOException {
		
		Map<File, String> file_code = new HashMap<>();
		String encoding = "";
		for(int i=0; i<fileList.size(); i++) {
			encoding = recognizeEncoding(fileList.get(i));
			file_code.put(fileList.get(i), encoding);
		}
		return file_code;
	}
	
	
	/**
	 * 识别文件的编码格式；利用第三方jar包 juniversalchardet；
	 * 
	 * @param curFile 待识别编码格式的File类对象
	 * @return 当前文件的编码格式名称
	 * 
	 * @throws IOException 
	 * 
	 */
	public String recognizeEncoding(File curFile) throws IOException {
		
		byte[] buf = new byte[4096];
		
		//通过待检测的文件创建文件输入流
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
	
	
	
	
}
