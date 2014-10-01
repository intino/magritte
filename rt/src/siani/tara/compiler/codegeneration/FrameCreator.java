package siani.tara.compiler.codegeneration;

import org.siani.itrules.DateTime;
import org.siani.itrules.Frame;
import siani.tara.lang.Annotations;
import siani.tara.lang.Model;
import siani.tara.lang.Node;
import siani.tara.lang.NodeObject;

import java.util.ArrayList;
import java.util.List;

public class FrameCreator {
	public static Frame[] create(Model model) {
		List<Frame> frames = new ArrayList<>();
		for (final Node node : model.getNodeTable().values()) {
			frames.add(new Frame(getTypes(node)) {{
				property("Name", node.getName());
				property("QualifiedName", node.getQualifiedName());
				if (node.getObject().getParent() != null)
					property("Parent", node.getObject().getParentName());
				property("Doc", node.getObject().getDoc());
				property("Birthday", new Frame(getTypes(node)) {{
					property("Name", node.getName());
					property("Birthday", new DateTime("29/07/1966"));
					property("Country", "Spain");
				}});
				property("Country", "Spain");
			}});
		}
		return frames.toArray(new Frame[frames.size()]);
	}

	private static String[] getTypes(Node node) {
		List<String> types = new ArrayList<>();
		NodeObject object = node.getObject();
		types.add(object.getType());
		for (Annotations.Annotation annotation : object.getAnnotations())
			types.add(annotation.getName());
		return types.toArray(new String[types.size()]);
	}
}
