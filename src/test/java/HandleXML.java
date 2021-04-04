import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class HandleXML {

    public static String getData (String keyName) throws Exception{
        ClassLoader classLoader = HandleXML.class.getClassLoader();
        //System.out.println("AAAAAAAAAAA " +classLoader.getResource("data.xml").getFile());
        //String xmlFilePath = String.valueOf(new File(classLoader.getResource("data.xml").getFile()));

        String cwd = System.getProperty("user.dir");
        String xmlFilePath= cwd + "\\data.xml";
        File fXmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        System.out.println("AAAA " +fXmlFile.getPath());
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }
}
