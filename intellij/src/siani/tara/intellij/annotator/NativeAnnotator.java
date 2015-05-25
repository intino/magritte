package siani.tara.intellij.annotator;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.TokenMgrError;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.model.Frame;
import siani.tara.intellij.framework.maven.NativeTemplate;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.Parameter;
import siani.tara.intellij.lang.psi.StringValue;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.semantic.Allow;
import siani.tara.semantic.model.Variable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentByType;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getLanguage;

public class NativeAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof StringValue && TaraUtil.isNativeValue((StringValue) element)) {
			InputStream stream = new ByteArrayInputStream(createTextFromTemplate(((StringValue) element)).getBytes(StandardCharsets.UTF_8));
			try {
				JavaParser.parse(stream);
				stream.close();
			} catch (ParseException | TokenMgrError e) {
				holder.createErrorAnnotation(element, e.getMessage());
			} catch (IOException ignored) {
			}
		}
	}

	private String createTextFromTemplate(StringValue stringValue) {
		Frame frame = createFrame(stringValue);
		return NativeTemplate.create().format(frame);
	}

	@NotNull
	private Frame createFrame(StringValue stringValue) {
		Frame frame = new Frame().addTypes("native");
		PsiElement element = getVariable(stringValue);
		if (element == null) element = getParameter(stringValue);
		Node node = getContainerNode(element);
		frame.addFrame("module", ModuleProvider.getModuleOf(stringValue).getName().toLowerCase());
		frame.addFrame("language", getLanguage(stringValue).languageName().toLowerCase());
		Allow.Parameter allow = TaraUtil.getCorrespondingAllow(node, element);
		if (allow != null) frame.addFrame("intention", intention(allow.contract()));

		final String methodBody = stringValue.getValue().replace("\\n", "\n").replace("\\\"", "\"");
		frame.addFrame("variable", getName(element)).
			addFrame("qn", node.getQualifiedName().replace("@anonymous", "").replace(".", "_")).
			addFrame("parent", firstNamedNode(node).replace(".", "_")).
			addFrame("body", methodBody.endsWith(";") ? methodBody : methodBody + ";").
			addFrame("signature", getSignature(allow.contract()));
		return frame;
	}

	private String getSignature(String contract) {
		return contract.substring(contract.indexOf(Variable.NATIVE_SEPARATOR) + 1);
	}

	private String intention(String contract) {
		return contract.substring(0, contract.indexOf(Variable.NATIVE_SEPARATOR));
	}

	private String getName(PsiElement element) {
		return element instanceof Parameter ? ((Parameter) element).getExplicitName() : ((VarInit) element).getName();
	}

	private static VarInit getVariable(StringValue stringValue) {
		PsiElement element = getParentByType(stringValue, VarInit.class);
		return element instanceof VarInit ? (VarInit) element : null;
	}

	private static Parameter getParameter(StringValue stringValue) {
		PsiElement element = getParentByType(stringValue, Parameter.class);
		return element instanceof Parameter ? (Parameter) element : null;
	}

	private String firstNamedNode(Node node) {
		if (node.getName() != null) return node.getQualifiedName();
		Node candidate = node;
		while (candidate != null && candidate.getName() == null) candidate = candidate.container();
		return candidate == null ? "" : candidate.getQualifiedName();
	}

	private Node getContainerNode(PsiElement element) {
		return (Node) getParentByType(element, Node.class);
	}

}
