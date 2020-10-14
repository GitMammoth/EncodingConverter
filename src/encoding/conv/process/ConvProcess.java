package encoding.conv.process;

import java.io.*;
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
			file_code.put(fileList.get(i), encoding);	// 注意这里存入的编码名称可能是null
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
	
	/**
	 * 将一个文件转换为指定的编码格式并重新写入到文件；
	 * 
	 * @param file 待转换编码的文件
	 * @param oriEncoding 当前文件的编码格式名称
	 * @param dstEncoding 目标编码名称
	 * 
	 * @throws IOException
	 * 
	 * */
	public void convFileEncoding(File file, String oriEncoding, String dstEncoding) throws IOException {
		
		if(oriEncoding==null) {//注意传入的原编码格式名称可能是null，该情况下不做转换；
			return;
		}
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), oriEncoding.toLowerCase());
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath()+"1"), dstEncoding.toLowerCase());
        
        
        int len = 0;
        char[] chars = new char[1024];
        while ((len = isr.read(chars)) != -1) {
            osw.write(chars,0, len);
        }
        osw.close();
        isr.close();
	}
	
	/**
	 * 将所有的文件从其原来的编码格式转换为指定的编码格式，并重新写入到对应的文件；
	 * 
	 * @param file_code 上一步方法recognizeEncoding的返回值
	 * @param dstEncoding 目标编码格式名称
	 * 
	 * 
	 * */
	public void convFileEncoding(Map<File, String> file_code, String dstEncoding) throws IOException {
		for(Map.Entry<File, String> entry : file_code.entrySet()) {
		    File file = entry.getKey();
		    String oriEncoding = entry.getValue();
		    
		    convFileEncoding(file, oriEncoding, dstEncoding);
		}
	}
	
}
