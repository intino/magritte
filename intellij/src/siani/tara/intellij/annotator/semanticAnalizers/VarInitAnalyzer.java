package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.TaraStringValue;
import siani.tara.intellij.lang.psi.VarInit;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.*;

import java.io.File;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.lang.Primitives.*;

public class VarInitAnalyzer extends TaraAnalyzer {

	private final VarInit varInit;
	private Variable variable;
	private Model metamodel;

	public VarInitAnalyzer(VarInit varInit) {
		super();
		this.varInit = varInit;
		metamodel = getMetamodel(varInit);
		Node node = getMetaConcept(TaraPsiImplUtil.getConceptContainerOf(varInit));
		if (node != null) variable = searchVariable(node);
	}

	@Override
	public void analyze() {
		if (variable == null) {
			results.put(varInit, new AnnotateAndFix(ERROR, "Variable not found"));
			return;
		}
		String valueType = varInit.getValueType();
		if (!valueType.equalsIgnoreCase(variable.getType())
			&& !areCompatibleTypes(valueType) && !(valueType.equals(REFERENCE) && (variable instanceof Reference || variable instanceof Word)))
			results.put(varInit, new AnnotateAndFix(ERROR, "Incompatible types. Found " + valueType + ". " + variable.getType() + " expected"));
		if (!hasErrors() && variable.getType().equals(Primitives.DOUBLE))
			analyzeAsTuple();
		if (hasErrors()) return;
		if (variable instanceof Resource) analyzeAsResource();
		else if (!variable.getType().equals(Primitives.MEASURE)) return;
		String[] values = varInit.getValues();
		if (varInit.getMeasureValue() != null) {
			AnnotateAndFix result = new MetricAnalyzer(metamodel, variable, values, varInit.getMeasureValue()).analyze();
			if (result != null) results.put(varInit, result);
		}

	}

	private void analyzeAsResource() {
		List<TaraStringValue> values = varInit.getValue().getStringValueList();
		for (TaraStringValue value : values) {
			if (!existResource(value.getText()))
				results.put(varInit, new AnnotateAndFix(ERROR, "Resource not found"));
			else if (!sameType(value.getText().replace("\"", ""), variable.getType()))
				results.put(varInit, new AnnotateAndFix(ERROR, "Incompatible types. Found " +
					value.getText().substring(value.getText().lastIndexOf(".")) + ". " + variable.getType() + " expected"));
		}
	}

	private boolean existResource(String destiny) {
		return findResource(destiny.replace("\"", "")) != null;
	}

	private File findResource(String destiny) {
		VirtualFile[] parent = ModuleRootManager.getInstance(ModuleProvider.getModuleOfFile(varInit.getContainingFile())).getSourceRoots();
		for (VirtualFile virtualFile : parent) {
			File file = new File(virtualFile.getPath(), destiny);
			if (file.exists()) return file;
		}
		return null;
	}

	private boolean sameType(String destiny, String type) {
		return type.equals(Resource.ANY) || destiny.substring(destiny.lastIndexOf(".") + 1).equalsIgnoreCase(type);
	}

	private boolean areCompatibleTypes(String valueType) {
		return (valueType.equals(NATURAL) && (variable.getType().equals(INTEGER) || variable.getType().equals(DOUBLE)) || variable.getType().equals(MEASURE))
			|| (valueType.equals(INTEGER) && (variable.getType().equals(DOUBLE) || variable.getType().equals(MEASURE)))
			|| (valueType.equals(DOUBLE) && (variable.getType().equals(MEASURE)))
			|| (valueType.equals(STRING) && (variable instanceof Resource));
	}

	private void analyzeAsTuple() {
		Integer count;
		if ((count = ((Attribute) variable).count) <= 1) return;
		if (varInit.getValue().getDoubleValueList().size() != count)
			results.put(varInit, new AnnotateAndFix(ERROR, MessageProvider.message("tuple.restriction") + " " + count.toString()));
	}

	private Variable searchVariable(Node node) {
		for (Variable var : node.getObject().getVariables())
			if (var.getName().equals(varInit.getName())) return var;
		return null;
	}


}
