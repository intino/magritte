package tara.intellij.settings;

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
import java.nio.file.Files;

import static java.io.File.separator;

public class ArtifactoryCredentialsManager {

	private static final String USERNAME = "username";
	private static final String ID = "id";
	private static final String PASSWORD = "password";
	private static final String SERVER = "server";
	private static final String SERVERS = "servers";
	private Document doc = null;

	public ArtifactoryCredentialsManager() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(settingsFile().getPath());
		} catch (ParserConfigurationException | SAXException | IOException ignored) {
		}
	}

	public String[] loadCredentials() {
		final Node server = findServer();
		if (server == null)
			return new String[]{"", "", ""};
		else {
			NodeList list = server.getChildNodes();
			return new String[]{get(list, ID).getTextContent(), get(list, USERNAME).getTextContent(), get(list, PASSWORD).getTextContent()};
		}
	}

	public void saveCredentials(String[] credentials) {
		if (settingsFile().exists()) modifyCredentials(credentials);
		else createSettingsFile(credentials);
	}

	private Node get(NodeList list, String name) {
		for (int i = 0; i < list.getLength(); i++)
			if (list.item(i).getNodeName().equals(name))
				return list.item(i);
		return list.item(0);
	}

	private void modifyCredentials(String[] credentials) {
		try {
			Node sianiServer = findServer();
			if (sianiServer == null) sianiServer = createServer(credentials[0]);
			modifyCredentials(sianiServer, credentials[1], credentials[2]);
			commit(doc);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private void modifyCredentials(Node server, String user, String password) {
		get(server.getChildNodes(), USERNAME).setTextContent(user);
		get(server.getChildNodes(), PASSWORD).setTextContent(password);
	}

	private Node createServer(String name) {
		final NodeList servers = doc.getElementsByTagName(SERVERS);
		if (servers.getLength() == 0) return null;
		Element serverNode = doc.createElement(SERVER);
		Element serverId = doc.createElement(ID);
		serverId.setTextContent(name);
		Element userNode = doc.createElement(USERNAME);
		Element passwordNode = doc.createElement(PASSWORD);
		serverNode.appendChild(serverId);
		serverNode.appendChild(userNode);
		serverNode.appendChild(passwordNode);
		servers.item(0).appendChild(serverNode);
		return serverNode;
	}

	private Node findServer() {
		return doc != null ? doc.getElementsByTagName(SERVER).item(0) : null;
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

	private static void createSettingsFile(String[] credentials) {
		final String settings = ArtifactorySettingsTemplate.create().format(new Frame().addTypes("artifactory").addFrame(SERVER, credentials[0]).addFrame(USERNAME, credentials[1]).addFrame(PASSWORD, credentials[2]));
		try {
			Files.write(settingsFile().toPath(), settings.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static File settingsFile() {
		final File home = new File(System.getProperty("user.home"));
		return new File(home, ".m2" + separator + "settings.xml");
	}
}
