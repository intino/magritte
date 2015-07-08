package siani.tara.intellij.refactoring.move;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;
import java.awt.*;

public class TaraMoveNodePanel extends JPanel {
	private JPanel myPanel;
	private TextFieldWithBrowseButton myBrowseTargetFileButton;
	private JLabel myElementsToMove;

	public TaraMoveNodePanel(String elementsToMoveText, String initialTargetFile) {
		super();
		this.setLayout(new BorderLayout());
		add(myPanel, BorderLayout.CENTER);
		myElementsToMove.setText(elementsToMoveText);
		myBrowseTargetFileButton.setText(initialTargetFile);
	}

	public TextFieldWithBrowseButton getBrowseTargetFileButton() {
		return myBrowseTargetFileButton;
	}
}