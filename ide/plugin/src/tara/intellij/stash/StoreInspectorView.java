/*
 *  Copyright 2013-2016 SIANI - ULPGC
 *
 *  This File is part of JavaFMI Project
 *
 *  JavaFMI Project is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License.
 *
 *  JavaFMI Project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with JavaFMI. If not, see <http://www.gnu.org/licenses/>.
 */

package tara.intellij.stash;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.file.StashFileType;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class StoreInspectorView extends JPanel {
	private final Project project;
	private JPanel contentPane;
	private JComboBox<String> stores;
	private JTree storeTree;
	private JButton newStore;
	private JPanel treePanel;
	private DefaultMutableTreeNode root;

	StoreInspectorView(Project project) {
		this.project = project;
		addListeners();
	}

	private void addListeners() {
		stores.addItemListener(e -> reloadTree());
		newStore.addActionListener(e -> addStore());
		storeTree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = storeTree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = storeTree.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					if (e.getClickCount() == 2) {
						openFile(selPath);
					}
				}
			}
		});
		storeTree.addTreeExpansionListener(new TreeExpansionListener() {
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				final DefaultMutableTreeNode component = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
				renderDirectory(component, component.getUserObject() instanceof String ?
					getSelectedStore() :
					((FileNode) component.getUserObject()).file);
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {

			}
		});
	}

	private void openFile(TreePath path) {
		final FileNode nodeFile = (FileNode) ((DefaultMutableTreeNode) path.getLastPathComponent()).getUserObject();
		final File file = nodeFile.file;
		final VirtualFile virtualFile = VfsUtil.findFileByIoFile(file, true);
		if (virtualFile == null) return;
		final PsiFile psiFile = PsiManager.getInstance(this.project).findFile(virtualFile);
		if (psiFile != null) psiFile.navigate(true);
	}

	private void reloadTree() {
		final File store = getSelectedStore();
		root.removeAllChildren();
		renderDirectory(root, store);
		storeTree.setRootVisible(true);
		storeTree.setVisible(true);
		storeTree.expandRow(0);
	}

	@NotNull
	private File getSelectedStore() {
		return new File(this.stores.getSelectedItem().toString());
	}

	private void renderDirectory(DefaultMutableTreeNode parent, File directory) {
		parent.removeAllChildren();
		for (File file : directory.listFiles(candidate -> candidate.isDirectory() || candidate.getName().endsWith("." + StashFileType.INSTANCE.getDefaultExtension()))) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new FileNode(file));
			parent.add(node);
			if (file.isDirectory()) node.add(new DefaultMutableTreeNode(""));
		}
		storeTree.updateUI();
	}

	private void addStore() {
		VirtualFile file = FileChooser.chooseFile(new FileChooserDescriptor(false, true, false, false, false, false), null, null);
		if (file != null) stores.addItem(file.getPath());
	}

	private void createUIComponents() {
		root = new DefaultMutableTreeNode("Store", true);
		storeTree = new Tree(root);
		storeTree.setRootVisible(false);
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(TaraIcons.STASH_16);
		storeTree.setCellRenderer(renderer);
	}

	JPanel getContentPane() {
		return contentPane;
	}

	private class FileNode {
		private File file;

		FileNode(File file) {
			this.file = file;
		}

		@Override
		public String toString() {
			return file.getName().replace(".stash", "");
		}
	}
}