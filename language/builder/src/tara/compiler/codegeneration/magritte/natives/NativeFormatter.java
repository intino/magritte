package tara.compiler.codegeneration.magritte.natives;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.siani.itrules.model.Frame;
import tara.Language;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.magritte.NameFormatter;
import tara.compiler.codegeneration.magritte.TemplateTags;
import tara.compiler.model.VariableReference;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.Valued;
import tara.lang.model.Variable;
import tara.lang.model.rules.variable.NativeObjectRule;
import tara.lang.model.rules.variable.NativeRule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static tara.compiler.codegeneration.magritte.NameFormatter.cleanQn;
import static tara.lang.model.Primitive.OBJECT;

@SuppressWarnings("ALL")
public class NativeFormatter implements TemplateTags {

	private final String outDsl;
	private final Language language;
	private final String aPackage;
	private final boolean system;
	private final Map<String, Set<String>> imports;

	public NativeFormatter(String outDsl, Language language, String aPackage, boolean system, File importsFile) {
		this.outDsl = outDsl;
		this.language = language;
		this.aPackage = aPackage;
		this.system = system;
		this.imports = load(importsFile);
	}


	private Map<String, Set<String>> load(File importsFile) {
		if (importsFile == null) return new HashMap<>();
		try {
			return new Gson().fromJson(new FileReader(importsFile), new TypeToken<Map<String, Set<String>>>() {
			}.getType());
		} catch (FileNotFoundException e) {
			return new HashMap<>();
		}
	}

	public void fillFrameForFunctionVariable(Frame frame, Variable variable, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		final String signature = NativeHelper.getSignature(variable);
		frame.addFrame(PACKAGE, this.aPackage);
		final Set<String> imports = new HashSet<>(((NativeRule) variable.rule()).imports());
		imports.addAll(collectImports(variable));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!slots.contains(LANGUAGE.toLowerCase())) frame.addFrame(LANGUAGE, outDsl.toLowerCase());
		if (!slots.contains(GENERATED_LANGUAGE.toLowerCase())) frame.addFrame(GENERATED_LANGUAGE, outDsl.toLowerCase());
		if (!slots.contains(RULE.toLowerCase())) frame.addFrame(RULE, cleanQn(NativeHelper.getInterface(variable)));
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, variable.name());
		if (!slots.contains(QN.toLowerCase())) frame.addFrame(QN, variable.container().qualifiedName());
		frame.addFrame(FILE, variable.file());
		frame.addFrame(LINE, variable.line());
		frame.addFrame(COLUMN, variable.column());
		if (body != null) frame.addFrame(BODY, NativeHelper.formatBody(body.toString(), signature));
		frame.addFrame(NATIVE_CONTAINER, cleanQn(NativeHelper.buildFunctionContainerPath(variable.scope(), variable.container(), outDsl)));
		frame.addFrame(SIGNATURE, signature);
		frame.addFrame(UID, variable.getUID());
		NativeExtractor extractor = new NativeExtractor(signature);
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		frame.addFrame("returnType", extractor.returnValue());
	}

	public void fillFrameForFunctionParameter(Frame frame, Parameter parameter, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		final String signature = NativeHelper.getSignature(parameter);
		if (!slots.contains(GENERATED_LANGUAGE.toLowerCase())) frame.addFrame(GENERATED_LANGUAGE, this.outDsl);
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, parameter.name());
		if (!this.aPackage.isEmpty()) frame.addFrame(PACKAGE, this.aPackage.toLowerCase());
		if (!slots.contains(QN.toLowerCase())) frame.addFrame(QN, parameter.container().qualifiedName());
		if (!slots.contains(LANGUAGE.toLowerCase())) frame.addFrame(LANGUAGE, NativeHelper.getLanguageScope(parameter, language));
		if (!slots.contains(RULE.toLowerCase())) frame.addFrame(RULE, cleanQn(NativeHelper.getInterface(parameter)));
		final Set<String> imports = new HashSet<String>(((NativeRule) parameter.rule()).imports());
		imports.addAll(collectImports(parameter));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(SIGNATURE, signature);
		frame.addFrame(FILE, parameter.file());
		frame.addFrame(LINE, parameter.line());
		frame.addFrame(COLUMN, parameter.column());
		frame.addFrame(NATIVE_CONTAINER, cleanQn(NativeHelper.buildFunctionContainerPath(parameter.scope(), parameter.container(), outDsl)));
		frame.addFrame(UID, parameter.getUID());
		NativeExtractor extractor = new NativeExtractor(signature);
		frame.addFrame("methodName", extractor.methodName());
		frame.addFrame("parameters", extractor.parameters());
		if (body != null) frame.addFrame(BODY, NativeHelper.formatBody(body.toString(), signature));
		frame.addFrame("returnType", extractor.returnValue());
	}

	public void fillFrameNativeVariable(Frame frame, Variable variable, Object body) {
		final List<String> slots = Arrays.asList(frame.slots());
		frame.addTypes(NATIVE);
		frame.addFrame(FILE, variable.file());
		frame.addFrame(LINE, variable.line());
		frame.addFrame(COLUMN, variable.column());
		final Set<String> imports = new HashSet<>(variable.rule() != null ? ((NativeRule) variable.rule()).imports() : new HashSet<>());
		imports.addAll(collectImports(variable));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		if (!aPackage.isEmpty()) frame.addFrame(PACKAGE, aPackage.toLowerCase());
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, variable.name());
		if (!slots.contains(GENERATED_LANGUAGE.toLowerCase())) frame.addFrame(GENERATED_LANGUAGE, outDsl);
		frame.addFrame(NATIVE_CONTAINER.toLowerCase(), NativeHelper.buildContainerPathOfExpression(variable, outDsl));
		if (!slots.contains(TYPE.toLowerCase())) frame.addFrame(TYPE, NativeHelper.typeFrame(type(variable), variable.isMultiple()));
		frame.addFrame(UID, variable.getUID());
		if (body != null) frame.addFrame(BODY, NativeHelper.formatBody(body.toString(), variable.type().getName()));
	}

	public void fillFrameNativeParameter(Frame frame, Parameter parameter, String body) {
		final List<String> slots = Arrays.asList(frame.slots());
		frame.addTypes(NATIVE);
		frame.addFrame(FILE, parameter.file());
		frame.addFrame(LINE, parameter.line());
		frame.addFrame(COLUMN, parameter.column());
		final Set<String> imports = new HashSet<>(parameter.rule() != null ? ((NativeRule) parameter.rule()).imports() : new HashSet<>());
		imports.addAll(collectImports(parameter));
		frame.addFrame(IMPORTS, imports.toArray(new String[imports.size()]));
		frame.addFrame(NATIVE_CONTAINER, NativeHelper.buildContainerPathOfExpression(parameter, outDsl));
		frame.addFrame(UID, parameter.getUID());
		if (!aPackage.isEmpty()) frame.addFrame(PACKAGE, aPackage.toLowerCase());
		if (!slots.contains(NAME.toLowerCase())) frame.addFrame(NAME, parameter.name());
		if (!slots.contains(GENERATED_LANGUAGE.toLowerCase())) frame.addFrame(GENERATED_LANGUAGE, outDsl.toLowerCase());
		if (!slots.contains(TYPE.toLowerCase())) frame.addFrame(TYPE, NativeHelper.typeFrame(type(parameter), parameter.isMultiple()));
		if (body != null) frame.addFrame(BODY, NativeHelper.formatBody(body, parameter.type().getName()));
	}

	private String type(Variable variable) {
		final boolean multiple = variable.isMultiple();
		if (variable.isReference()) return NameFormatter.getQn(((VariableReference) variable).destinyOfReference(), outDsl);
		if (OBJECT.equals(variable.type())) return ((NativeObjectRule) variable.rule()).type();
		if (Primitive.WORD.equals(variable.type()))
			return NameFormatter.getQn(variable.container(), outDsl) + "." + Format.firstUpperCase().format(variable.name());
		return variable.type().javaName();
	}

	private String type(Parameter parameter) {
		final boolean multiple = parameter.isMultiple();
		return parameter.type().equals(OBJECT) ?
			((NativeObjectRule) parameter.rule()).type() :
			parameter.type().javaName();
	}

	private List<String> collectImports(Valued parameter) {
		final String qn = (parameter.container().qualifiedName() + "." + parameter.name()).replace(":", "");
		return imports.containsKey(qn) ? new ArrayList<>(imports.get(qn)) : Collections.emptyList();
	}


}
