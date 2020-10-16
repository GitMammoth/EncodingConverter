package encoding.conv.process.detector;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 该接口规定文件编码识别器应该提供的功能；
 * */
public interface CodeDetector {
	
	/**
	 * 传入单个文件，识别并返回其编码格式
	 * */
	public String recognizeEncoding(File file) throws IOException;
	
	/**
	 * 传入文件列表，返回对应的编码格式列表
	 * */
	public List<String> recognizeEncoding(List<File> fileList) throws IOException;
	
	
	
	
}
