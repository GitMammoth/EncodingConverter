package encoding.conv.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 获取待转换的所有文件；
 * */
public class GetFiles {
	
	public List<File> fileList = new ArrayList<>();
	
	
	@Test
	public void getFileList(String rootPath) {
		/*
		 * 测试路径
		 * "D:\\ljp_SoftSpace\\__myDevProj\\EncodingConverter_eclipse202009\\ToConvDir"
		 * */
		getFiles(rootPath);
		
		
		/* 测试通过；
		for(int i=0; i<fileList.size(); i++) {
			System.out.println( fileList.get(i) );
		}
		*/
	}
	
	
	/**
	 * 递归获取所有文件；
	 * */
	public void getFiles(String dirPath){
		
		File dirFile = new File(dirPath);
		
		if(dirFile.isDirectory() && dirFile.list().length>0) {
			File[] items = dirFile.listFiles();
			
			for(int i=0; i<items.length; i++) {
				getFiles(items[i].getAbsolutePath());
			}
		}
		
		if(!dirFile.isDirectory()) {
			fileList.add(dirFile);
		}
		
	}
	
	
	
	
}
