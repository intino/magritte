package siani.tara.intellij.annotator;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.TokenMgrError;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.model.Frame;
import siani.tara.intellij.framework.maven.NativeTemplate;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.semantic.Allow;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentByType;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getLanguage;
import static siani.tara.semantic.model.Variable.NATIVE_SEPARATOR;

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
		PsiElement element = getVarInit(stringValue);
		if (element == null) element = getParameter(stringValue);
		if (element == null) element = getVariable(stringValue);
		Node node = getContainerNode(element);
		frame.addFrame("module", ModuleProvider.getModuleOf(stringValue).getName().toLowerCase());
		frame.addFrame("language", getLanguage(stringValue).languageName().toLowerCase());
		Allow.Parameter allow = TaraUtil.getCorrespondingAllow(node, element);
		frame.addFrame("intention", allow != null ?
			intention(allow.contract()) :
			((siani.tara.intellij.lang.psi.Variable) element).getContract().getText());
		final String methodBody = stringValue.getValue().replace("\\n", "\n").replace("\\\"", "\"");
		frame.addFrame("variable", getName(element)).
			addFrame("qn", node.getQualifiedName().replace("@anonymous", "").replace(".", "_")).
			addFrame("parent", firstNamedNode(node).replace(".", "_")).
			addFrame("body", methodBody.endsWith(";") ? methodBody : methodBody + ";").
			addFrame("signature", getSignature(allow != null ? allow.contract() : findNativeInterface(((siani.tara.intellij.lang.psi.Variable) element).getContract())));
		return frame;
	}

	private String findNativeInterface(Contract contract) {
		final PsiElement element = ReferenceManager.resolveContract(contract);
		if (element == null || !(element instanceof PsiClass)) return "";
		PsiClass aClass = (PsiClass) element;
		if (aClass.getMethods().length == 0) return "";
		final PsiMethod psiMethod = aClass.getMethods()[0];
		return psiMethod.getText().replace(";", "");
	}

	private String getSignature(String contract) {
		return contract.substring(contract.indexOf(NATIVE_SEPARATOR) + 1);
	}

	private String intention(String contract) {
		return contract.contains(NATIVE_SEPARATOR) ? contract.substring(0, contract.indexOf(NATIVE_SEPARATOR)) : "";
	}

	private String getName(PsiElement element) {
		if (element instanceof Parameter) return ((Parameter) element).getExplicitName();
		else if (element instanceof VarInit) return ((VarInit) element).getName();
		else return ((siani.tara.intellij.lang.psi.Variable) element).getName();
	}

	private static VarInit getVarInit(StringValue stringValue) {
		PsiElement element = getParentByType(stringValue, VarInit.class);
		return element instanceof VarInit ? (VarInit) element : null;
	}

	private static Parameter getParameter(StringValue stringValue) {
		PsiElement element = getParentByType(stringValue, Parameter.class);
		return element instanceof Parameter ? (Parameter) element : null;
	}

	private PsiElement getVariable(StringValue stringValue) {
		PsiElement element = getParentByType(stringValue, siani.tara.intellij.lang.psi.Variable.class);
		return element instanceof siani.tara.intellij.lang.psi.Variable ? (siani.tara.intellij.lang.psi.Variable) element : null;
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
