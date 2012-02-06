package ansj.sun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class IOUtil {

	private static final String ENCODING = System.getProperty("file.encoding");

	/**
	 * 获得文件读取流
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static BufferedReader getBufferedReader(String path)
			throws FileNotFoundException, UnsupportedEncodingException {
		return getBufferedReader(new File(path), ENCODING);
	}

	public static BufferedReader getBufferedReader(String path,
			String charEncoding) throws FileNotFoundException,
			UnsupportedEncodingException {
		return getBufferedReader(new File(path), charEncoding);
	}

	public static BufferedReader getBufferedReader(File file)
			throws FileNotFoundException, UnsupportedEncodingException {
		return getBufferedReader(file, ENCODING);
	}

	public static BufferedReader getBufferedReader(File file,
			String charEncoding) throws FileNotFoundException,
			UnsupportedEncodingException {
		return getBufferedReader(new FileInputStream(file),charEncoding);
	}
	
	public static BufferedReader getBufferedReader(InputStream openStream) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return getBufferedReader(openStream,ENCODING) ;
	}

	public static BufferedReader getBufferedReader(InputStream openStream,
			String charEncoding) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return new BufferedReader(new InputStreamReader(openStream,charEncoding));
	}

	/**
	 * 保存文件
	 * 
	 * @author ansj
	 * @mail ansj-sun@163.com
	 * @param path
	 * @param content
	 * @throws IOException
	 */
	public static void write(String path, String content) throws IOException {
		write(new File(path), content, ENCODING);
	}

	public static void write(String path, String content, String charEncoding)
			throws IOException {
		write(new File(path), content, charEncoding);
	}

	public static void write(File file, String content) throws IOException {
		write(file, content, ENCODING);
	}

	public static void write(File file, String content, String charEncoding)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(content.getBytes());
		fos.flush();
		fos.close();
	}

	public static void write(FileChannel fc, String content) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
		fc.write(buffer);
	}

}
