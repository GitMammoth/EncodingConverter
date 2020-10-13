package encoding.conv.setting;

/**
 * 用户的参数设置；
 * */
public class ConvSetting {
	
	private String dirPath;
	public static final String DIRPATH = "dirPath";
	
	private String oriEncoding;
	public static final String ORIENCODING = "oriEncoding";
	
	private String dstEncoding;
	public static final String DSTENCODING = "dstEncoding";
	
	public ConvSetting(String dirPath, String oriEncoding, String dstEncoding) {
		super();
		this.dirPath = dirPath;
		this.oriEncoding = oriEncoding;
		this.dstEncoding = dstEncoding;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public String getOriEncoding() {
		return oriEncoding;
	}

	public void setOriEncoding(String oriEncoding) {
		this.oriEncoding = oriEncoding;
	}

	public String getDstEncoding() {
		return dstEncoding;
	}

	public void setDstEncoding(String dstEncoding) {
		this.dstEncoding = dstEncoding;
	}
	
}
