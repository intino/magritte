package org.siani.teseo.plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import org.siani.teseo.plugin.TeseoIcons;
import tara.intellij.project.TaraModuleType;

abstract class Action extends AnAction {

	@Override
	public void update(AnActionEvent e) {
		final boolean enabled = TaraModuleType.isTara(LangDataKeys.MODULE.getData(e.getDataContext()));
		e.getPresentation().setVisible(enabled);
		e.getPresentation().setEnabled(enabled);
		e.getPresentation().setIcon(TeseoIcons.ICON_16);
	}
}
