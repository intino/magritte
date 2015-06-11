package siani.tara.compiler.codegeneration.magritte;

import org.siani.itrules.model.Frame;
import siani.tara.semantic.model.Primitives;
import siani.tara.compiler.model.Variable;
import siani.tara.compiler.model.impl.VariableReference;
import siani.tara.semantic.Allow;
import siani.tara.semantic.constraints.ReferenceParameterAllow;

import static siani.tara.compiler.codegeneration.magritte.NameFormatter.getQn;
import static siani.tara.semantic.model.Variable.NATIVE_SEPARATOR;

public class VarFrameCreator implements TemplateTags {


	private final String generatedLanguage;
	private int modelLevel;

	public VarFrameCreator(String generatedLanguage, int modelLevel) {
		this.generatedLanguage = generatedLanguage;
		this.modelLevel = modelLevel;
	}

	protected Frame createVarFrame(final Variable variable) {
		Frame frame = new Frame() {
			{
				addFrame(NAME, variable.getName());
				addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
				if (variable.getContract() != null) addFrame(CONTRACT, format(variable.getContract()));
				addFrame(TYPE, variable instanceof VariableReference ? getQn(((VariableReference) variable).getDestiny(), generatedLanguage.toLowerCase()) : getType());
				if (variable.getType().equals(Variable.WORD))
					addFrame(WORDS, variable.getAllowedValues().toArray(new String[(variable.getAllowedValues().size())]));
				else if (variable.getType().equals(Primitives.MEASURE)) {
//					TODO addFrame(MEASURE, variable.getContract());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
				} else if (variable.getType().equals(Primitives.NATIVE)) {
					final NativeExtractor nativeExtractor = new
						NativeExtractor(variable.getContract().substring(0, variable.getContract().indexOf(NATIVE_SEPARATOR)),
						variable.getName(), variable.getContract().substring(variable.getContract().indexOf(NATIVE_SEPARATOR) + 1));
					addFrame("parameters", nativeExtractor.parameters());
					addFrame("interfaceName", nativeExtractor.interfaceName());
					addFrame("methodName", nativeExtractor.methodName());
					addFrame("returnValue", nativeExtractor.returnValue());
				}
			}

			private String format(String contract) {
				if (contract == null) return "";
				final int i = contract.indexOf(NATIVE_SEPARATOR);
				return (i >= 0) ? contract.substring(0, i) : contract;
			}

			private String getType() {
				if (variable.getType().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
				else return variable.getType();
			}
		};

		return frame.addTypes(MorphCreatorHelper.getTypes(variable, modelLevel));
	}

	protected Frame createVarFrame(final Allow.Parameter variable) {
		Frame frame = new Frame() {
			{
				addFrame(NAME, variable.name());
				addFrame(GENERATED_LANGUAGE, generatedLanguage.toLowerCase());
				addFrame(TYPE, variable instanceof ReferenceParameterAllow ? variable.allowedValues().get(0) : getType());//TODO QN completo
				if (variable.type().equals(Variable.WORD))
					addFrame(WORDS, variable.allowedValues().toArray(new String[(variable.allowedValues().size())]));
				else if (variable.type().equals(Primitives.MEASURE)) {
//					TODO addFrame(MEASURE, variable.getContract());
//					if (((Attribute) variable).getMeasureValue() != null)
//						addFrame(MEASURE_VALUE, resolveMetric(((Attribute) variable).getMeasureValue()));
				}
			}

			private String getType() {
				if (variable.type().equalsIgnoreCase(Primitives.NATURAL)) return Primitives.INTEGER;
				else return variable.type();
			}
		};

		return frame.addTypes(MorphCreatorHelper.getTypes(variable));
	}
}
