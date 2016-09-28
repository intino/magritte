package plugin.project.facet.maven;

import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class MavenManager {
	private static final String TESEO_GROUP_ID = "org.siani.pandora";
	private static final String TESEO_SCHEDULER_ARTIFACT_ID = "scheduler";
	private static final String TESEO_SERVER_ARTIFACT_ID = "rest-server";
	private static final String TESEO_VERSION = "[1.0.0, 2.0.0)";
	private static final String VERSION = "version";
	private static final String DEPENDENCY = "dependency";
	private static final String DEPENDENCIES = "dependencies";
	private static final String GROUP_ID = "groupId";
	private static final String ARTIFACT_ID = "artifactId";
	private final Module module;
	private String path;
	private Document doc;

	public MavenManager(Module module) {
		this.module = module;
		MavenProject mavenProject = mavenProject(module);
		try {
			path = mavenProject.getPath();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(path);
		} catch (Exception ignored) {
		}
	}

	private static MavenProject mavenProject(Module module) {
		return MavenProjectsManager.getInstance(module.getProject()).findProject(module);
	}

	private boolean hasTeseoDependency(String artifact) {
		if (doc == null) return true;
		NodeList dependencies = doc.getElementsByTagName(DEPENDENCY);
		for (int i = 0; i < dependencies.getLength(); i++)
			if (isTeseoDependency(dependencies.item(i), artifact)) return true;
		return false;
	}

	public void addTeseoServer() {
		if (hasTeseoDependency(TESEO_SERVER_ARTIFACT_ID)) return;
		Node dependencies = doc.getElementsByTagName(DEPENDENCIES).item(0);
		dependencies.appendChild(createTeseoServerDependency());
		commit();
	}

	public void addTeseoScheduler() {
		if (hasTeseoDependency(TESEO_SCHEDULER_ARTIFACT_ID)) return;
		Node dependencies = doc.getElementsByTagName(DEPENDENCIES).item(0);
		dependencies.appendChild(createTeseoSchedulerDependency());
		commit();
	}

	private boolean isTeseoDependency(Node item, String artifact) {
		NodeList childNodes = item.getChildNodes();
		String[] artifactInfo = getArtifactInfo(childNodes);
		return artifactInfo[0].equals(TESEO_GROUP_ID) && artifactInfo[1].equals(artifact);
	}

	private void commit() {
		try {
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
			MavenProjectsManager.getInstance(module.getProject()).forceUpdateAllProjectsOrFindAllAvailablePomFiles();
		} catch (TransformerException ignored) {
		}
	}

	private Node createTeseoSchedulerDependency() {
		Element dependency = doc.createElement(DEPENDENCY);
		dependency.appendChild(groupId(doc, TESEO_GROUP_ID));
		dependency.appendChild(artifactId(doc, TESEO_SCHEDULER_ARTIFACT_ID));
		dependency.appendChild(version(doc, TESEO_VERSION));
		return dependency;
	}

	private Node createTeseoServerDependency() {
		Element dependency = doc.createElement(DEPENDENCY);
		dependency.appendChild(groupId(doc, TESEO_GROUP_ID));
		dependency.appendChild(artifactId(doc, TESEO_SERVER_ARTIFACT_ID));
		dependency.appendChild(version(doc, TESEO_VERSION));
		return dependency;
	}

	@NotNull
	private Element groupId(Document doc, String groupId) {
		Element element = doc.createElement(GROUP_ID);
		element.setTextContent(groupId);
		return element;
	}

	@NotNull
	private Element artifactId(Document doc, String artifactId) {
		Element element = doc.createElement(ARTIFACT_ID);
		element.setTextContent(artifactId);
		return element;
	}

	@NotNull
	private Element version(Document doc, String version) {
		Element element = doc.createElement(VERSION);
		element.setTextContent(version);
		return element;
	}

	@NotNull
	private String[] getArtifactInfo(NodeList childNodes) {
		String[] artifact = new String[3];
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (GROUP_ID.equalsIgnoreCase(child.getNodeName())) artifact[0] = child.getTextContent();
			else if (ARTIFACT_ID.equalsIgnoreCase(child.getNodeName())) artifact[1] = child.getTextContent();
			else if (VERSION.equalsIgnoreCase(child.getNodeName())) artifact[2] = child.getTextContent();
		}
		return artifact;
	}
}
