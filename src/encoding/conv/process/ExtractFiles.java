package encoding.conv.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 获取待转换的所有文件；
 * */
public class ExtractFiles {
	
	@Test
	public void test() {
		
		List<File> files = getFileList("D:\\ljp_SoftSpace\\__myDevProj\\EncodingConverter_eclipse202009\\ToConvDir");
		
		for(int i=0; i<files.size(); i++) {
			System.out.println( files.get(i) );
		}
		
		files = getFileList("D:\\ljp_SoftSpace\\__myDevProj\\EncodingConverter_eclipse202009\\ToConvDir");
		
	}
	
	//有待转换的所有文件；
	private static List<File> fileList = new ArrayList<>();
	private static boolean hasExtracted = false;

	
	/**
	 * 传入根目录地址，获取目录下所有待转换的文件列表；
	 * 
	 * @param rootPath 根目录字符串
	 * @return 待转换的文件列表
	 * */
	public List<File> getFileList(String rootPath) {
		if(!hasExtracted) {
			searchFiles(new File(rootPath));
			hasExtracted=true;
		}
		return fileList;
	}
	
	
	/**
	 * 递归获取所有文件，将文件添加到静态成员fileList；
	 * 
	 * @param curFile 当前路径对应的File对象
	 * */
	private void searchFiles(final File curFile){
				
		if(!curFile.isDirectory()) {//若当前路径不是文件夹；
			fileList.add(curFile);
			return ;
		}
		
		if(curFile.list().length>0) {//若当前路径是文件夹，且内部有内容；
			File[] items = curFile.listFiles();
			for(int i=0; i<items.length; i++) {
				searchFiles(items[i]);
			}
		}
		
	}
	
	
}
