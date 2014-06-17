package siani.tara.intellij.highlighting;

import com.intellij.lexer.FlexLexer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.lang.lexer.LexerLoader;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TaraHighlighterLexProxy implements FlexLexer {

	private final Project project;
	FlexLexer destiny;

	public TaraHighlighterLexProxy(Project project) {
		this.project = project;
	}


	@Override
	public void yybegin(int state) {
		destiny.yybegin(state);
	}

	@Override
	public int yystate() {
		return destiny.yystate();
	}

	@Override
	public int getTokenStart() {
		return destiny.getTokenStart();
	}

	@Override
	public int getTokenEnd() {
		return destiny.getTokenEnd();
	}

	@Override
	public IElementType advance() throws IOException {
		return destiny.advance();
	}

	public void reset(CharSequence buf, int start, int end, int initialState) {
		if (buf.length() != 0)
			calculateParent(buf);
		else destiny = new TaraHighlighterLex((Reader) null);
		destiny.reset(buf, start, end, initialState);
	}

	private void calculateParent(CharSequence buf) {
		Module module = ModuleProvider.getModuleOfDocument(project, buf.toString());
		if (module == null) destiny = new TaraHighlighterLex((Reader) null);
		else {
			String parent = getParent(module);
			destiny = (parent != null) ? loadClass(getClassName(parent) + "HighlighterLex") : new TaraHighlighterLex((Reader) null);
		}

	}

	private String getClassName(String parent) {
		return parent.substring(0, 1).toUpperCase() + parent.substring(1, parent.length());
	}

	private String getParent(Module module) {
		ModuleConfiguration configuration = (ModuleConfiguration) module.getComponent("ModuleConfiguration");
		return (!configuration.getParentName().isEmpty()) ? configuration.getParentName() : null;
	}

	private FlexLexer loadClass(String lexerName) {
		try {
			LexerLoader loader = new LexerLoader(this.getClass().getClassLoader());
			Class<?> aClass = Class.forName(this.getClass().getPackage().getName() + "." + lexerName, true, loader);
			Constructor<?> constructor = aClass.getDeclaredConstructor(Reader.class);
			constructor.setAccessible(true);
			Object o = constructor.newInstance((Reader) null);
			return (FlexLexer) o;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			return new TaraHighlighterLex((Reader) null);
		}

	}
}
