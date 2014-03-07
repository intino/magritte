package monet.tara.intellij.plugin_generation;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.SimpleJavaParameters;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SimpleJavaSdkType;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.intellij.util.SystemProperties;
import monet.tara.compiler.core.CompilerConfiguration;
import org.intellij.grammar.generator.BnfConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JFlexToJavaGenerator {
	public static void jFlexToJava(Project project, VirtualFile lexFile, String outputPath, RunContentDescriptor console) {
		try {
			FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
			fileDocumentManager.saveAllDocuments();
			Document document = fileDocumentManager.getDocument(lexFile);
			if (document == null) return;

			final String commandName = "JFlex to java Generation";
			final PsiDirectory directory = PsiManager.getInstance(project).findDirectory(lexFile.getParent());
			if (directory == null) return;

			String text = document.getText();
			Matcher matcher = Pattern.compile("%class (\\w+)").matcher(text);
			if (!matcher.find()) {
				Notifications.Bus.notify(new Notification(
					BnfConstants.GENERATION_GROUP,
					lexFile.getName() + " lexer generation", "Lexer class name option not found, use <pre>%class LexerClassName</pre>",
					NotificationType.ERROR), project);
				return;
			}
			final String lexerClassName = matcher.group(1);
			List<File> jflex = getJLexerFiles();
			if (jflex.isEmpty()) return;

			SimpleJavaParameters javaParameters = new SimpleJavaParameters();
			Sdk sdk = new SimpleJavaSdkType().createJdk("tmp", SystemProperties.getJavaHome());
			javaParameters.setJdk(sdk);
			javaParameters.getClassPath().add(jflex.get(0));
			javaParameters.setMainClass("JFlex.Main");
			javaParameters.getVMParametersList().add("-Xmx512m");
			javaParameters.getProgramParametersList().add("-sliceandcharat");
			javaParameters.getProgramParametersList().add("-skel", jflex.get(1).getAbsolutePath());
			javaParameters.getProgramParametersList().add("-d", VfsUtil.virtualToIoFile(lexFile.getParent()).getAbsolutePath());
			javaParameters.getProgramParametersList().add(VfsUtil.virtualToIoFile(lexFile).getAbsolutePath());

			OSProcessHandler processHandler = javaParameters.createOSProcessHandler();

			((ConsoleViewImpl) console.getExecutionConsole()).attachToProcess(processHandler);


			processHandler.addProcessListener(new ProcessAdapter() {
				@Override
				public void processTerminated(ProcessEvent event) {
					if (event.getExitCode() == 0) {
						ApplicationManager.getApplication().invokeLater(new Runnable() {
							@Override
							public void run() {
							}
						}, directory.getProject().getDisposed());
					}
				}
			});
			processHandler.startNotify();
		} catch (ExecutionException ex) {
			Messages.showErrorDialog(project, "Unable to run JFlex" + "\n" + ex.getLocalizedMessage(), "Lexer to Java generation");
		}
	}

	private static List<File> getJLexerFiles() {
		ArrayList<File> files = new ArrayList<>();
		files.add(new File(files.getClass().getResource("flex/JFlex.jar").getPath()));
		files.add(new File(files.getClass().getResource("flex/idea-flex.skeleton").getPath()));
		return files;
	}

	public static void jFlexToJava(CompilerConfiguration configuration, File lexFile) {
	}
}
