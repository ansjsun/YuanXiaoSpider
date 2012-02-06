import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlInputStreamDecoder
{
	private static final String ENCODING_TAG = "<meta[^;]+;//s*charset//s*=//s*([^\"//s]+)[^>]*>";
	private static final String IGNORE_TAG_REGEX = "<(head|script|style|iframe|comment)[^>]*>.*?<///1>";
	private static final String DEFAULT_ENCODING = "iso8859-1";
	private static final Pattern EMPTY_LINE = Pattern.compile("/n^//s*$", Pattern.MULTILINE | Pattern.UNIX_LINES);

	private static final int BLOCK_SIZE = 1024;

	public String decode(InputStream is) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		byte[] bytes = new byte[BLOCK_SIZE];
		int len = is.read(bytes);
		String encoding = resolveEncoding(bytes, len);
		
		sb.append(stripEmptyLine(new String(bytes, 0, len, encoding)));
		while ((len = is.read(bytes)) > 0)
		{
			sb.append(stripEmptyLine(new String(bytes, 0, len, encoding)));			
		}
		return sb.toString();
	}

         //去除无关的tag，比如Script，style，head等
	public String stripIngoreTag(String input)
	{
		Pattern ingorePattern = Pattern.compile(IGNORE_TAG_REGEX, Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		Matcher m = ingorePattern.matcher(input);
		return m.replaceAll("");
	}

         //去除多余空行
	protected String stripEmptyLine(String input)
	{
		Matcher m = EMPTY_LINE.matcher(input);
		return m.replaceAll("");
	}

	protected String resolveEncoding(byte[] bytes, int len) throws UnsupportedEncodingException
	{
		String encoding = resolveEncoding(bytes);
		if (encoding == null)
		{
			String detector = new String(bytes, 0, len, DEFAULT_ENCODING);
			Pattern encodingPattern = Pattern.compile(ENCODING_TAG, Pattern.CASE_INSENSITIVE);
			Matcher m = encodingPattern.matcher(detector);
			if (m.find())
			{
				encoding = m.group(1);
			} 
			else
			{
				encoding = DEFAULT_ENCODING;
			}
		}
		return encoding;
	}

	public String resolveEncoding(byte[] rawBytes)
	{
		String result = null;

		boolean utf16BEBom = false;
		boolean utf16LEBom = false;

		boolean utf16BE = false;
		boolean utf16LE = false;

		if (rawBytes.length >= 2)
		{
			if (((byte) 0xFE == rawBytes[0]) && ((byte) 0xFF == rawBytes[1]))
			{
				utf16BEBom = true;
			} 
			else if ((byte) 0xFF == (rawBytes[0]) && ((byte) 0xFE == rawBytes[1]))
			{
				utf16LEBom = true;
			}
		}

		if (rawBytes.length >= 4)
		{
			if ((0 != rawBytes[0]) && (0 == rawBytes[1]) && (0 != rawBytes[2]) && (0 == rawBytes[3]))
			{
				utf16LE = true;
			} 
			else if ((0 == rawBytes[0]) && (0 != rawBytes[1]) && (0 == rawBytes[2]) && (0 != rawBytes[3]))
			{
				utf16BE = true;
			}
		}

		if (utf16LE)
		{
			result = "UTF-16LE";
		} 
		else if (utf16BE)
		{
			result = "UTF-16BE";
		} 
		else if (utf16LEBom)
		{
			result = "UTF-16LEBom";
		} 
		else if (utf16BEBom)
		{
			result = "UTF-16BEBom";
		}
		return result;
	}

	public static void main(String[] args) throws IOException
	{
		HtmlInputStreamDecoder decoder = new HtmlInputStreamDecoder();
		String text = decoder.decode(new URL("http://www.csdn.com/").openStream());
		System.out.println(text);
		System.out.println(decoder.stripIngoreTag(text));
	}

}