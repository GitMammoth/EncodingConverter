package encoding.conv.junitest;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
import org.mozilla.universalchardet.UniversalDetector;

/**
 * JunitTest 相关类只对代码片段做单元测试；
 * */
public class JunitTest {
	/**
	 * 测试第三方jar包代码的使用；
	 * 目前测试可以正常识别文本文件的编码格式；
	 * 
	 * */
	@Test
	public void test() throws IOException {

		byte[] buf = new byte[4096];

		String fileName = "D:\\ljp_SoftSpace\\__myDevProj\\EncodingConverter_eclipse202009\\ToConvDir\\fileDir.txt";

		FileInputStream fis = new FileInputStream(fileName);

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
		if (encoding != null) {
			System.out.println("Detected encoding = [" + encoding + "]");
		} else {
			System.out.println("No encoding detected.");
		}

		// (5)
		detector.reset();

	}
}
