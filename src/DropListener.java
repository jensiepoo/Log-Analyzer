import java.util.Hashtable;
import java.util.List;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class DropListener extends FileOpener implements DropTargetListener {
	public DropListener(JTabbedPane tabbedPane, JTextPane textArea,
			Hashtable<String, JCheckBox> checkboxarr, JPanel jpane,
			Config config, JTextField txtKeyword, StringBuilder sb1,
			Hashtable<String, Color> ht1) {
		super(tabbedPane, textArea, checkboxarr, jpane, config, txtKeyword,
				sb1, ht1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drop(DropTargetDropEvent event) {
		// Accept copy drops
		event.acceptDrop(DnDConstants.ACTION_COPY);

		// Get the transfer which can provide the dropped item data
		Transferable transferable = event.getTransferable();

		// Get the data formats of the dropped item
		DataFlavor[] flavors = transferable.getTransferDataFlavors();
		// Loop through the flavors
		for (DataFlavor flavor : flavors) {
			try {
				// If the drop items are files
				if (flavor.isFlavorJavaFileListType()) {
					// Get all of the dropped files
					List<File> files = (List) transferable.getTransferData(flavor);
					String x = "";
					enable();
					// Loop them through
					for (File file : files) {
						// Print out the file path
						try {
							x = x
									+ "File Name: "
									+ file.getName()
									+ '\n'
									+ '\n'
									+ readFile(file)
									+ "=====================================End Of File===================================="
									+ '\n';
							// tabName = w.getName().substring(0,
							// w.getName().indexOf('_'));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						config.setDir(file.getPath());
					}
					if (log.getLength() > 0) {
						tabbedPane.removeAll();
					}
					textArea.setText(x);
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setViewportView(textArea);
					tabbedPane.addTab("radio log", scrollPane);
				}
			} catch (Exception e) {

				// Print out the error stack
				e.printStackTrace();
			}
		}

		// Inform that the drop is complete
		event.dropComplete(true);
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

}
