package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class TriggerExceptionAction extends AnAction {
    public void actionPerformed(@NotNull AnActionEvent e) {
        throw new RuntimeException("Im an artificial exception!");
    }
}
