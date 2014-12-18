package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class ReportingError extends AnAction {
	public void actionPerformed(AnActionEvent e) {
		throw new RuntimeException("I'm an artificial exception!");
	}

}
