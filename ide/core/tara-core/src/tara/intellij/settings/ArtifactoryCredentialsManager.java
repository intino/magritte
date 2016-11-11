package tara.intellij.settings;

import org.siani.itrules.model.Frame;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class ArtifactoryCredentialsManager {

	private static final String USERNAME = "username";
	private static final String ID = "id";
	private static final String PASSWORD = "password";
	private static final String SERVER = "server";
	private static final String SERVERS = "servers";
	private Document doc = null;

	ArtifactoryCredentialsManager() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(settingsFile().getPath());
		} catch (ParserConfigurationException | SAXException | IOException ignored) {
		}
	}

	List<ArtifactoryCredential> loadCredentials() {
		List<ArtifactoryCredential> artifactories = new ArrayList<>();
		final List<Node> servers = servers();
		for (Node server : servers) {
			NodeList list = server.getChildNodes();
			artifactories.add(new ArtifactoryCredential(get(list, ID).getTextContent(), get(list, USERNAME).getTextContent(), get(list, PASSWORD).getTextContent()));
		}
		return artifactories;
	}

	void saveCredentials(List<ArtifactoryCredential> credentials) {
		if (settingsFile().exists()) settingsFile().delete();
		createSettingsFile(credentials);
	}

	private Node get(NodeList list, String name) {
		for (int i = 0; i < list.getLength(); i++)
			if (list.item(i).getNodeName().equals(name))
				return list.item(i);
		return list.item(0);
	}

	private List<Node> servers() {
		return doc != null ? asList(doc.getElementsByTagName(SERVER)) : Collections.emptyList();
	}

	private List<Node> asList(NodeList elements) {
		List<Node> nodes = new ArrayList<>();
		for (int i = 0; i < elements.getLength(); i++) nodes.add(elements.item(i));
		return nodes;
	}

	private static void createSettingsFile(List<ArtifactoryCredential> credentials) {
		Frame artifactory = new Frame().addTypes("artifactory");
		List<Frame> credentialFrames = credentials.stream().
			map(credential -> new Frame().addTypes("server").addFrame("name", credential.serverId).addFrame(USERNAME, credential.username).addFrame(PASSWORD, credential.password)).collect(Collectors.toList());
		artifactory.addFrame("server", credentialFrames.toArray(new Frame[credentialFrames.size()]));
		final String settings = ArtifactorySettingsTemplate.create().format(artifactory);
		write(settings);
	}

	private static void write(String settings) {
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
