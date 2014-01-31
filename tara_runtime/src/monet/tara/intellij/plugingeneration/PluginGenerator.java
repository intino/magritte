package monet.tara.intellij.plugingeneration;

public class PluginGenerator {
//
//	public static final Logger LOG = Logger.getInstance("PluginGenerator");
//	private static final String SEP = System.getProperty("file.separator");
//	private TaraFile[] taraFiles;
//	private String outputPath;
//	private Project project;
//	private PrintWriter printWriter;
//
//	public PluginGenerator(TaraFile[] taraFiles, String outputPath) {
//		this.taraFiles = taraFiles;
//		this.project = taraFiles[0].getProject();
//		this.outputPath = outputPath + SEP + project.getName().toLowerCase() + "-plugin" + SEP;
//	}
//
//	public void generate() throws IOException {
//		for (String template : TemplateFactory.getTemplates().keySet()) {
//			String templatePath = TemplateFactory.getTemplate(template).replace("tara", project.getName().toLowerCase());
//			openGeneratedFileOutput(new File(getPath(templatePath)));
//			if (template.endsWith("grammar") || template.endsWith("lexer")) continue;
//			writeTemplateBasedFile(template, getTemplateParameters(template));
//			closeOutFile();
//		}
//		VirtualFile grammarFile = getGrammarFile();
//		TaraToJavaGenerator.toJava(project, taraFiles, outputPath + "res" + SEP, createConsole(project, "tara parsing"));
//		BnfToJavaGenerator.bnfToJava(project, grammarFile, outputPath, createConsole(project, "bnf parsing"));
//		JFlexToJavaGenerator.jFlexToJava(project, grammarFile, outputPath, createConsole(project, "lex parsing"));
//	}
//
//	private VirtualFile getGrammarFile() {
//		final Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(taraFiles[0].getVirtualFile());
//		ModifiableRootModel model = ModuleRootManager.getInstance(module).getModifiableModel();
//		VirtualFile[] roots = model.getSourceRoots();
//		return getGenPath(roots).findFileByRelativePath(project.getName().toLowerCase() + "-plugin" + SEP + "monet" + SEP +
//			project.getName().toLowerCase() + SEP + "intellij" + SEP + "metamodel" + SEP + "grammar.bnf");
//	}
//
//	private VirtualFile getGenPath(VirtualFile[] roots) {
//		for (VirtualFile root : roots)
//			if (root.getName().equals("gen"))
//				return root;
//		return null;
//	}
//
//	private HashMap<String, String> getTemplateParameters(String template) {
//		if (!template.equals("grammar") && (template.equals("lexer")))
//			return null;
//		else if (template.equals("grammar"))
//			return generateGrammarFileTemplateKeys();
//		else
//			return generateLexerFileTemplateKeys();
//	}
//
//	private void writeTemplateBasedFile(String template, HashMap<String, String> param) {
//		DefaultRender defaultRender = RendersFactory.getRender(template, project.getName(), param);
//		printWriter.print(defaultRender.getOutput());
//	}
//
//	private HashMap<String, String> generateGrammarFileTemplateKeys() {
//		return null;
//	}
//
//	private HashMap<String, String> generateLexerFileTemplateKeys() {
//		return null;
//	}
//
//
//	private String getPath(String templatePath) {
//		if (templatePath.equals("META-INF/plugin"))
//			return outputPath + templatePath + ".xml";
//		else if (templatePath.endsWith("grammar"))
//			return outputPath + templatePath + ".bnf";
//		else if (templatePath.endsWith("lexer"))
//			return outputPath + templatePath + ".flex";
//		else return outputPath + templatePath + ".java";
//	}
//
//	private void openGeneratedFileOutput(File file) throws IOException {
//		file.getParentFile().mkdirs();
//		file.createNewFile();
//		out("// ---- " + file.getName() + "\n");
//		printWriter = new PrintWriter(new FileOutputStream(file));
//	}
//
//	public void out(String s) {
//		System.out.print(s);
//	}
//
//	private void closeOutFile() {
//		printWriter.close();
//	}
//
//	public static RunContentDescriptor createConsole(Project project, final String tabTitle) {
//		TextConsoleBuilder builder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
//		ConsoleView consoleView = builder.getConsole();
//		DefaultActionGroup toolbarActions = new DefaultActionGroup();
//		JComponent consoleComponent = new JPanel(new BorderLayout());
//		JPanel toolbarPanel = new JPanel(new BorderLayout());
//		toolbarPanel.add(ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, toolbarActions, false).getComponent());
//		consoleComponent.add(toolbarPanel, BorderLayout.WEST);
//		consoleComponent.add(consoleView.getComponent(), BorderLayout.CENTER);
//		RunContentDescriptor descriptor = new RunContentDescriptor(consoleView, null, consoleComponent, tabTitle, null);
//		Executor executor = DefaultRunExecutor.getRunExecutorInstance();
//		for (AnAction action : consoleView.createConsoleActions())
//			toolbarActions.add(action);
//		toolbarActions.add(new CloseAction(executor, descriptor, project));
//		ExecutionManager.getInstance(project).getContentManager().showRunContent(executor, descriptor);
//		consoleView.allowHeavyFilters();
//		return descriptor;
//	}
}
