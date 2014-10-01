package siani.tara.compiler.core.operation;

import org.siani.itrules.AbstractFrame;
import org.siani.itrules.Document;
import org.siani.itrules.RuleEngine;
import siani.tara.compiler.codegeneration.FrameCreator;
import siani.tara.compiler.core.CompilationUnit;
import siani.tara.compiler.core.CompilerConfiguration;
import siani.tara.compiler.core.errorcollection.CompilationFailedException;
import siani.tara.compiler.core.operation.model.ModelOperation;
import siani.tara.lang.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		AbstractFrame[] frames = FrameCreator.create(model);
		for (String ruleFile : rulesFolder.list())
			for (AbstractFrame frame : frames) {
				FileInputStream fileInputStream = getRulesInput(ruleFile);
				Document document = new Document();
				new RuleEngine(fileInputStream).render(frame, document);
				printDocument(configuration.getOutDirectory(), document.content());
			}
	}

	private void printDocument(File outDirectory, String content) {

	}

	private FileInputStream getRulesInput(String ruleFile) {
		try {
			return new FileInputStream(new File(ruleFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
