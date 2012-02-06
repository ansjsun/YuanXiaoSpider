import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ansj.sun.util.StringUtils;

public class XmlTest {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder dombuilder = domfac.newDocumentBuilder();
		InputStream is = new FileInputStream("D:/data/mil.news.sina.com.cn.xml");
		Document doc = dombuilder.parse(is);
		Element root = doc.getDocumentElement();
		NodeList obj = root.getChildNodes();
		for (int i = 0; i < obj.getLength(); i++) {
			String name = obj.item(i).getNodeName();
			if (StringUtils.isNotBlank(name) && name.charAt(0) != '#') {
				System.out.println(obj.item(i).getTextContent().trim()) ;
			}
		}
	}
}
