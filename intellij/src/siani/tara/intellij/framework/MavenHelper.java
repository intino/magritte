package siani.tara.intellij.framework;

import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.maven.model.MavenArtifact;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectsManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
import java.util.List;

public class MavenHelper {
	private static final String MAGRITTE_GROUP_ID = "org.siani.magritte";
	private static final String MAGRITTE_ARTIFACT_ID = "magritte";
	private static final String MAGRITTE_VERSION = "LATEST";
	private static final String MAGRITTE_TYPE = "jar";
	private final Module module;
	private final MavenProject mavenProject;
	private String path;
	private Document doc;

	public MavenHelper(Module module, MavenProject mavenProject) {
		this.module = module;
		this.mavenProject = mavenProject;
		try {
			path = mavenProject.getPath();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(path);
		} catch (ParserConfigurationException | SAXException | IOException ignored) {
		}
	}

	public boolean hasMagritteDependency() {
		NodeList dependencies = doc.getElementsByTagName("dependency");
		for (int i = 0; i < dependencies.getLength(); i++)
			if (isMagritteDependency(dependencies.item(i))) return true;
		return false;
	}

	public void addMagritte() {
		try {
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			dependencies.appendChild(createMagritteDependency());
			commit();
		} catch (TransformerException ignored) {
		}
	}

	public void add(Module newParent) {
		try {
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			Node moduleDependency = createModuleDependency(newParent);
			if (moduleDependency != null) {
				dependencies.appendChild(moduleDependency);
				commit();
			}
		} catch (TransformerException ignored) {
		}
	}

	private Node createModuleDependency(Module newParent) {
		Element dependency = doc.createElement("dependency");
		MavenProject project = MavenProjectsManager.getInstance(newParent.getProject()).findProject(newParent);
		if (project == null) return null;
		dependency.appendChild(groupId(doc, project.getMavenId().getGroupId()));
		dependency.appendChild(artifactId(doc, project.getMavenId().getArtifactId()));
		dependency.appendChild(type(doc, "bundle"));
		dependency.appendChild(scope(doc));
		dependency.appendChild(version(doc, project.getMavenId().getVersion()));
		return dependency;
	}

	public void remove(Module parent) {
		MavenProjectsManager manager = MavenProjectsManager.getInstance(parent.getProject());
		MavenProject mavenParent = manager.findProject(parent);
		List<MavenArtifact> dependencies;
		if (mavenParent == null || (dependencies = mavenProject.findDependencies(mavenParent.getMavenId())).isEmpty())
			return;
		Node xmlDependencies = doc.getElementsByTagName("dependencies").item(0);
		Node xmlParentDependency = findXmlParentDependency(dependencies);
		if (xmlParentDependency != null) xmlDependencies.removeChild(xmlParentDependency);
		try {
			commit();
		} catch (TransformerException ignored) {
		}

	}

	private boolean isMagritteDependency(Node item) {
		NodeList childNodes = item.getChildNodes();
		String[] artifactInfo = getArtifactInfo(childNodes);
		return artifactInfo[0].equals(MAGRITTE_GROUP_ID) && artifactInfo[1].equals(MAGRITTE_GROUP_ID);
	}

	private void commit() throws TransformerException {
		doc.getDocumentElement().normalize();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
		MavenProjectsManager.getInstance(module.getProject()).forceUpdateAllProjectsOrFindAllAvailablePomFiles();

	}

	private Node createMagritteDependency() {
		Element dependency = doc.createElement("dependency");
		dependency.appendChild(groupId(doc, MAGRITTE_GROUP_ID));
		dependency.appendChild(artifactId(doc, MAGRITTE_ARTIFACT_ID));
		dependency.appendChild(version(doc, MAGRITTE_VERSION));
		dependency.appendChild(type(doc, MAGRITTE_TYPE));
		return dependency;
	}

	@NotNull
	private Element groupId(Document doc, String groupId) {
		Element element = doc.createElement("groupId");
		element.setTextContent(groupId);
		return element;
	}

	@NotNull
	private Element artifactId(Document doc, String artifactId) {
		Element element = doc.createElement("artifactId");
		element.setTextContent(artifactId);
		return element;
	}

	@NotNull
	private Element version(Document doc, String version) {
		Element element = doc.createElement("version");
		element.setTextContent(version);
		return element;
	}

	@NotNull
	private Element type(Document doc, String type) {
		Element element = doc.createElement("type");
		element.setTextContent(type);
		return element;
	}

	@NotNull
	private Element scope(Document doc) {
		Element element = doc.createElement("scope");
		element.setTextContent("");
		return element;
	}

	private Node findXmlParentDependency(List<MavenArtifact> dependencies) {
		NodeList dependency = doc.getElementsByTagName("dependency");
		for (int i = 0; i < dependency.getLength(); i++)
			for (MavenArtifact mavenArtifact : dependencies)
				if (isParentModuleDependency(dependency.item(i), mavenArtifact))
					return dependency.item(i);
		return null;
	}

	private boolean isParentModuleDependency(Node item, MavenArtifact mavenArtifact) {
		NodeList childNodes = item.getChildNodes();
		String[] artifactInfo = getArtifactInfo(childNodes);
		return artifactInfo[0].equals(mavenArtifact.getGroupId())
			&& artifactInfo[1].equals(mavenArtifact.getArtifactId())
			&& artifactInfo[2].equals(mavenArtifact.getVersion());
	}

	private String[] getArtifactInfo(NodeList childNodes) {
		String[] artifact = new String[3];
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if ("groupId".equalsIgnoreCase(child.getNodeName())) artifact[0] = child.getTextContent();
			if ("artifactId".equalsIgnoreCase(child.getNodeName())) artifact[1] = child.getTextContent();
			if ("version".equalsIgnoreCase(child.getNodeName())) artifact[2] = child.getTextContent();
		}
		return artifact;
	}
}
