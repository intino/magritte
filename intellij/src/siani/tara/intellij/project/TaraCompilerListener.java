package siani.tara.intellij.project;

import com.intellij.compiler.server.CustomBuilderMessageHandler;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBusConnection;
import siani.tara.compiler.rt.TaraRtConstants;

import java.io.File;

public class TaraCompilerListener extends AbstractProjectComponent {


	private MessageBusConnection messageBusConnection;


	public TaraCompilerListener(Project project) {
		super(project);
	}

	@Override
	public void initComponent() {
		super.initComponent();
		messageBusConnection = myProject.getMessageBus().connect();
		messageBusConnection.subscribe(CustomBuilderMessageHandler.TOPIC, new FileInvalidationListener());
	}

	@Override
	public void disposeComponent() {
		messageBusConnection.disconnect();
		super.disposeComponent();
	}

	private class FileInvalidationListener implements CustomBuilderMessageHandler {
		@Override
		public void messageReceived(String builderId, String messageType, String messageText) {
			if (TaraRtConstants.TARAC.equals(builderId) && TaraRtConstants.FILE_INVALIDATION_BUILDER_MESSAGE.equals(messageType)) {
				VirtualFile virtualFile = VfsUtil.findFileByIoFile(new File(messageText), true);
				if (virtualFile != null) virtualFile.refresh(true, true);
			}
		}
	}
}

