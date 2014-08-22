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
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class DropListener extends FileOpener implements DropTargetListener {
	
	/**
	 * @param tabbedPane
	 * @param textArea
	 * @param checkboxarr
	 * @param jpane
	 * @param config
	 * @param txtKeyword
	 * @param sb1
	 * @param ht1
	 * @param from
	 * @param to
	 * @param jft
	 * @throws IOException
	 * This constructs a drag and drop file loader, and extends and 
	 * calls methods from FileOpener. 
	 */
	public DropListener(JTabbedPane tabbedPane, JTextPane textArea,
			Hashtable<String, JCheckBox> checkboxarr, JPanel jpane,
			Config config, JTextField txtKeyword, StringBuilder sb1,
			Hashtable<String, Color> ht1, JFormattedTextField from, 
			JFormattedTextField to, JFormattedTextField jft) throws IOException {
		super(tabbedPane, textArea, checkboxarr, jpane, config, txtKeyword,
				sb1, ht1, from, to, jft);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
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
					for (File file : files) {
						try {
							x = readFile(file);
							JTextPane jtp = new JTextPane();
							jtp.setText(x);
							JScrollPane scrollPane = new JScrollPane();
//							scrollPane.setViewportView(jtp);
//							textArea.setText(jtp.getText());
//							textArea.setText(x);
							scrollPane.setViewportView(jtp);
							scrollPane.setName(file.getName());
							tabbedPane.addTab(file.getName(), scrollPane);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						config.setDir(file.getPath());
					}
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
