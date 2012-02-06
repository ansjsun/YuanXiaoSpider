package ansj.sun.spider.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ansj.sun.spider.pojo.Configure;
import ansj.sun.util.StringUtils;

public class ConfigureXml {
	public static Configure makeConfigure(String xmlPath) {
		try {
			Configure conf = new Configure() ;
			DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder dombuilder = domfac.newDocumentBuilder();
			InputStream is = new FileInputStream(xmlPath);
			Document doc = dombuilder.parse(is);
			Element root = doc.getDocumentElement();
			NodeList obj = root.getChildNodes();
			for (int i = 0; i < obj.getLength(); i++) {
				String name = obj.item(i).getNodeName();
				if (StringUtils.isNotBlank(name) && name.charAt(0) != '#') {
					conf.put(name, obj.item(i).getTextContent().trim()) ;
				}
			}
			return conf;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	
	
}
