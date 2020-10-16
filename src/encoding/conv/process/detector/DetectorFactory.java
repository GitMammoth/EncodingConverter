package encoding.conv.process.detector;

import encoding.conv.utils.string.StringUtil;

public class DetectorFactory {
	
	/**
	 * 根据用户参数获取指定的编码识别器；
	 * */
	public static CodeDetector getDetector(String param) {
		
		CodeDetector detector = null;
		
		if(StringUtil.isBlank(param)) {
			param="det01";
		}
		
		switch(param) {
		case "det01":
			detector = new Juniversalchardet_v103();
			break;
		case "det02":
			detector = new Juniversalchardet_v103();	// TODO 因为没有其他选择，这里暂时先重复设置；
			break;
		default:
			detector = new Juniversalchardet_v103();
		}
		
		return detector;
	}
	
}
