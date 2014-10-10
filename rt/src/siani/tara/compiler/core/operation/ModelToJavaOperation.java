package siani.tara.compiler.core.operation;

import org.siani.itrules.Frame;
import siani.tara.compiler.codegeneration.FrameCreator;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.lang.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.logging.Logger;

public class ModelToJavaOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(ModelToJavaOperation.class.getName());
	private CompilerConfiguration configuration;
	private File rulesFolder;

	public ModelToJavaOperation(CompilationUnit compilationUnit) {
		super();
		configuration = compilationUnit.getConfiguration();
		rulesFolder = configuration.getRulesDirectory();
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		Map<String, Frame> frames = FrameCreator.create(model);
//		if (rulesFolder.listFiles() == null) return;
//		for (File ruleFile : rulesFolder.listFiles())
//			for (Map.Entry<String, Frame> frame : frames.entrySet()) {
//				FileInputStream fileInputStream = getRulesInput(ruleFile);
//				Document document = new Document();
//				new RuleEngine(fileInputStream).render(frame.getValue(), document);
//				printDocument(configuration.getOutDirectory(), document.content(), frame.getKey());
//			}
	}

	private void printDocument(File outDirectory, String content, String qn) {

	}

	private FileInputStream getRulesInput(File ruleFile) {
		try {
			return new FileInputStream(ruleFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
