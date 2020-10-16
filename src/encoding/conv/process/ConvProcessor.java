package encoding.conv.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import org.junit.Test;

import encoding.conv.process.detector.CodeDetector;
import encoding.conv.process.detector.DetectorFactory;
import encoding.conv.process.extraction.FileExtracter;
import encoding.conv.setting.UserConvSetting;
import encoding.conv.utils.string.StringUtil;

/**
 * 编码转换过程处理器；
 * 该类需要提供的功能有：
 * 		1. 抽取根目录下的所有文件；
 * 		2. 如果需要，则对文件的编码格式进行自动识别；
 * 		3. 将文件转换成指定编码格式的新文件；
 */
public class ConvProcessor {
	
	private String rootPath;
	private String oriEncoding;
	private String dstEncoding;
	
	private List<File> fileList;
	
	/***
	 * 转换处理过程的统一入口方法；
	 * */
	public void convProcess(UserConvSetting ust) throws IOException {
		
		//将常用的参数设置赋值到到成员变量上，省去方法调用中的参数传递；
		rootPath = ust.getParam(ust.dirPath);
		oriEncoding = ust.getParam(ust.oriEncoding);
		dstEncoding = ust.getParam(ust.dstEncoding);
		FileExtracter fext = new FileExtracter();	//创建文件抽取器对象；
		fileList = fext.getFileList(rootPath);		//根据指定目录抽取到其中的所有文件；
		
		
		//根据用户设置判断是否需要自动识别原文件的编码格式；
		if(StringUtil.isBlank(oriEncoding)) {//用户没有指定原编码格式，则需要自动识别；
			
			//根据用户参数创建指定的编码识别器；
			CodeDetector detector = DetectorFactory.getDetector(ust.getParam(ust.detectingTool));
			List<String> codeList = detector.recognizeEncoding(fileList);	//识别到的编码列表与fileList的索引互相对应；
			
			convert(codeList);
		}else {//若用户指定了原编码格式，则不需要自动识别；
			convert();
		}
		
	}
	
	/**
	 * 将所有的文件从其原来的编码格式转换为指定的编码格式；
	 * 自动识别原文件的编码格式；
	 * */
	private void convert(List<String> codeList) throws IOException {
		for(int i=0; i<fileList.size(); i++) {
			String code = codeList.get(i);
			if(StringUtil.isBlank(code)) {
				continue;
			}/********
				* 注意自动识别出的编码格式可能是空的；此时若直接忽略则新目录dir_new下会有文件丢失；// TODO 该问题后续修复；
			 	*/
			convFileEncoding(fileList.get(i), code);
		}
	}
	
	/**
	 * 转换文件列表中所有文件的编码格式；用户指定原编码格式；
	 * */
	private void convert() throws IOException {
		for(int i=0; i<fileList.size(); i++) {
			convFileEncoding(fileList.get(i), oriEncoding);	//按照配置文件中的参数进行转换；
		}
	}
	
	
	/**
	 * 将一个文件转换为指定的编码格式；
	 * 当前方法在运行中需要的信息有：
	 * 		1. 待转换的文件
	 * 		2. 原编码格式；
	 * 		3. 指定编码格式；
	 * 		4. 转换后文件的路径；
	 * 		其中1、2参数为调用者提供；3参数为成员变量提供；4参数为根据当前文件的路径计算得出；
	 * 
	 * @param file 待转换编码的文件
	 * @param originalCode 当前文件的编码格式名称；要保证传入的值是正确的，因为该方法内部不做检查；
	 * 
	 * @throws IOException
	 * 
	 * */
	private void convFileEncoding(File file, String originalCode) throws IOException {
		
		//根据用户指定的根目录和当前文件现在的路径，计算出转换后新的路径；
		String newPath = getNewPath(rootPath, file.getAbsolutePath());
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), originalCode.toLowerCase());
		/****
		 * begin 这里目前有一个问题，_new目录不存在会报错；
		 * */
//		File temp = new File(newPath);
//		temp.mkdirs();
		
		
		/***end**/
//        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(newPath), dstEncoding.toLowerCase());
//        int len = 0;
//        char[] chars = new char[1024];
//        while ((len = isr.read(chars)) != -1) {
//            osw.write(chars,0, len);
//        }
//        osw.close();
        isr.close();
	}
	
	/**
	 * 根据当前路径确定转换后新的路径地址；
	 * 
	 * 如：
	 * 指定转换的根目录：	rootPath =  D:/dir
	 * 当前待转换的文件：	curPath  =  D:/dir/xxx/yyy/file.txt
	 * 转换后新的路径：		newPath  =  D:/dir_new/xxx/yyy/file.txt
	 * 
	 * */
	private String getNewPath(String rootPath, String curPath) {
		String newPath = rootPath + "_new";
		
		String path = curPath.substring(rootPath.length());
		newPath += path;
		
		//这里需要为新路径的创建目录，否则直接使用输出流会报路径找不到的错误；
		int aa = newPath.lastIndexOf("/");
		String bb = newPath.substring(0, aa);
		File file = new File(bb);
		file.mkdirs();
		
		return newPath;
	}
	
	/**
	 * 测试路径的转换；
	 * */
	//@Test
	public void test() {
		String rootPath = "D:/dir";
		String curPath  = "D:/dir/xxx/yyy/file.txt";
		String newPath = getNewPath(rootPath, curPath);
		
		
		System.out.println( "[" + curPath + "]" );
		System.out.println( "[" + newPath + "]" );
	}
}
