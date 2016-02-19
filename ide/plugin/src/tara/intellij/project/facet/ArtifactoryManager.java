package tara.intellij.project.facet;

import org.siani.itrules.model.Frame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tara.templates.ArtifactorySettingsTemplate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

import static java.io.File.separator;

public class ArtifactoryManager {


	private static final String SERVER_ID = "siani-maven";
	private Document doc = null;

	public ArtifactoryManager() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(settingsFile().getPath());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public Map.Entry<String, String> loadCredentials() {
		final Node sianiServer = findSianiServer();
		if (sianiServer == null)
			return new AbstractMap.SimpleEntry<>("", "");
		else {
			NodeList list = sianiServer.getChildNodes();
			return new AbstractMap.SimpleEntry<>(list.item(1).getTextContent(), list.item(2).getTextContent());
		}
	}

	public void saveCredentials(String user, String password) {
		if (settingsFile().exists()) {
			modifyCredentials(user, password);
		} else {
			createSettingsFile(user, password);
		}
	}

	private void modifyCredentials(String user, String password) {
		try {
			Node sianiServer = findSianiServer();
			if (sianiServer == null) sianiServer = createSianiServer();
			modifyCredentials(sianiServer, user, password);
			commit(doc);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private void modifyCredentials(Node server, String user, String password) {
		if (server.getChildNodes().getLength() > 1) {
			server.getChildNodes().item(1).setTextContent(user);
			server.getChildNodes().item(2).setTextContent(password);
		} else {
			Element userNode = doc.createElement("user");
			userNode.setTextContent(user);
			server.appendChild(userNode);
			Element passwordNode = doc.createElement("password");
			passwordNode.setTextContent(password);
			server.appendChild(passwordNode);
		}

	}

	private  Node createSianiServer() {
		final NodeList servers = doc.getElementsByTagName("servers");
		if (servers.getLength() == 0) return null;
		Element serverNode = doc.createElement("server");
		Element serverId = doc.createElement("id");
		serverId.setTextContent(SERVER_ID);
		serverNode.appendChild(serverId);
		servers.item(0).appendChild(serverNode);
		return serverNode;
	}

	private Node findSianiServer() {
		final NodeList servers = doc.getElementsByTagName("server");
		for (int i = 0; i < servers.getLength(); i++)
			if (isSiani(servers.item(i))) return servers.item(i);
		return null;
	}

	private static boolean isSiani(Node item) {
		for (int i = 0; i < item.getChildNodes().getLength(); i++)
			if (item.getChildNodes().item(i).getNodeName().equals("id") && item.getChildNodes().item(i).getTextContent().equals(SERVER_ID))
				return true;
		return false;
	}

	private static void commit(Document doc) throws TransformerException {
		doc.getDocumentElement().normalize();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(settingsFile());
		transformer.transform(source, result);
	}


	private static void createSettingsFile(String user, String password) {
		ArtifactorySettingsTemplate.create().format(new Frame().addTypes("artifactory").addFrame("user", user).addFrame("password", password));
	}

	private static File settingsFile() {
		final File home = new File(System.getProperty("user.home"));
		return new File(home, ".m2" + separator + "settings.xml");
	}
}
