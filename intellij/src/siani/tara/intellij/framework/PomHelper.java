package siani.tara.intellij.framework;

import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
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
import java.util.Collection;

public class PomHelper {
	private String path;
	private Document doc;

	public PomHelper(String path) {
		try {
			this.path = path;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(path);
		} catch (ParserConfigurationException | SAXException | IOException ignored) {
		}
	}


	public boolean hasTaraDependency() {
		NodeList dependencies = doc.getElementsByTagName("dependency");
		for (int i = 0; i < dependencies.getLength(); i++)
			if (isTaraDependency(dependencies.item(i))) return true;
		return false;
	}

	private boolean isTaraDependency(Node item) {
		NodeList childNodes = item.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if ("groupId".equals(child.getNodeName())) {
				if (!"org.siani.Tara".equals(child.getTextContent()))
					return false;
			} else if ("artifactId".equals(child.getNodeName())) return child.getTextContent().equals("Tara");
		}
		return false;
	}

	public void addTaraDependency() {
		try {
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			dependencies.appendChild(createTaraDependency(doc));
			commit();
		} catch (TransformerException ignored) {
		}
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
	}

	private Node createTaraDependency(Document doc) {
		Element dependency = doc.createElement("dependency");
		dependency.appendChild(groupId(doc));
		dependency.appendChild(artifactId(doc));
		dependency.appendChild(version(doc));
		return dependency;
	}

	@NotNull
	private Element groupId(Document doc) {
		Element groupId = doc.createElement("groupId");
		groupId.setTextContent("org.siani.Tara");
		return groupId;
	}

	@NotNull
	private Element artifactId(Document doc) {
		Element artifactId = doc.createElement("artifactId");
		artifactId.setTextContent("Tara");
		return artifactId;
	}

	@NotNull
	private Element version(Document doc) {
		Element artifactId = doc.createElement("version");
		artifactId.setTextContent("LATEST");
		return artifactId;
	}

	public void addModules(Collection<Module> modulesOf) {

	}
}
