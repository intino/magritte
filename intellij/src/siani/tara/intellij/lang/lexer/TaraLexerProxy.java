package siani.tara.intellij.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TaraLexerProxy implements FlexLexer {

	FlexLexer destiny;
	Project project;

	public TaraLexerProxy(Project project) {
		this.project = project;
	}

	public void yybegin(int state) {
		destiny.yybegin(state);
	}

	public int yystate() {
		return destiny.yystate();
	}

	public int getTokenStart() {
		return destiny.getTokenStart();
	}

	public int getTokenEnd() {
		return destiny.getTokenEnd();
	}

	public IElementType advance() throws IOException {
		return destiny.advance();
	}

	public void reset(CharSequence buf, int start, int end, int initialState) {
		calculateParent(buf);
		destiny.reset(buf, start, end, initialState);
	}

	private void calculateParent(CharSequence buf) {
		Module module = ModuleProvider.getNamespaceOfDocument(project, buf.toString());
		if (module == null) {
			destiny = new TaraLexer((Reader) null);
			return;
		}
		String parent = getParent(module);
		destiny = (parent != null) ? loadClass(getClassName(parent) + "Lexer") : new TaraLexer((Reader) null);
	}

	private String getClassName(String parent) {
		return parent.substring(0, 1).toUpperCase() + parent.substring(1, parent.length());
	}

	private String getParent(Module module) {
		ModuleConfiguration configuration = (ModuleConfiguration) module.getComponent("ModuleConfiguration");
		return (!configuration.getParentName().equals("")) ? configuration.getParentName() : null;
	}

	private FlexLexer loadClass(String lexerName) {
		try {
			LexerLoader loader = new LexerLoader(this.getClass().getClassLoader(), project.getName());
			Class<?> aClass = Class.forName(this.getClass().getPackage().getName() + "." + lexerName, true, loader);
			Constructor<?> constructor = aClass.getDeclaredConstructor(Reader.class);
			constructor.setAccessible(true);
			Object o = constructor.newInstance((Reader) null);
			return (FlexLexer) o;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			return new TaraLexer((Reader) null);
		}

	}
}
