package legio.plugin;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.*;
import org.jetbrains.annotations.NotNull;
import tara.StashBuilder;
import tara.io.Stash;
import tara.io.StashDeserializer;
import tara.magritte.Graph;

import java.io.File;
import java.io.IOException;

public class FileListener implements com.intellij.openapi.components.ApplicationComponent {
    @Override
    public void initComponent() {
        VirtualFileManager.getInstance().addVirtualFileListener(new VirtualFileListener() {
            @Override
            public void propertyChanged(@NotNull VirtualFilePropertyEvent event) {
            }

            @Override
            public void fileDeleted(@NotNull VirtualFileEvent event) {
                removeModuleFromStructure();
            }

            private void removeModuleFromStructure() {
            }

            @Override
            public void fileMoved(@NotNull VirtualFileMoveEvent event) {
            }

            private Project project() {
                final DataContext result = dataContext();
                return result != null ? (Project) result.getData(PlatformDataKeys.PROJECT.getName()) : null;
            }

            private DataContext dataContext() {
                return DataManager.getInstance().getDataContextFromFocus().getResult();
            }

            @Override
            public void contentsChanged(@NotNull VirtualFileEvent event) {
                processFile(event);
            }

            @Override
            public void fileCreated(@NotNull VirtualFileEvent event) {
                addContent(event);
                processFile(event);
            }

            private void addContent(VirtualFileEvent event) {
                //TODO
            }

            private void processFile(VirtualFileEvent event) {
                try {
                    new StashBuilder(new File(event.getFile().getPath())).build();
                    Graph.from( )
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fileCopied(@NotNull VirtualFileCopyEvent event) {

            }

            @Override
            public void beforePropertyChange(@NotNull VirtualFilePropertyEvent event) {
            }

            @Override
            public void beforeContentsChange(@NotNull VirtualFileEvent event) {
            }

            @Override
            public void beforeFileDeletion(@NotNull VirtualFileEvent event) {
                showWarningMessage();
            }

            private void showWarningMessage() {

            }

            @Override
            public void beforeFileMovement(@NotNull VirtualFileMoveEvent event) {

            }
        });
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Legio File Listener";
    }
}