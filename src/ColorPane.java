
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ColorPane extends JTextPane {
  public void append(Color c, String s) { // better implementation--uses
                      // StyleContext
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
        StyleConstants.Foreground, c);

    int len = getDocument().getLength(); // same value as
                       // getText().length();
    setCaretPosition(len); // place caret at the end (with no selection)
    setCharacterAttributes(aset, false);
    replaceSelection(s); // there is no selection, so inserts at caret
  }

	public void append(String x) {
		this.setText(x);
	}

//  public static void main(String argv[]) {
//
//    ColorPane pane = new ColorPane();
//    for (int n = 1; n <= 400; n += 1) {
//      if (isPrime(n)) {
//        pane.append(Color.red, String.valueOf(n) + ' ');
//      } else if (isPerfectSquare(n)) {
//        pane.append(Color.blue, String.valueOf(n) + ' ');
//      } else {
//        pane.append(Color.black, String.valueOf(n) + ' ');
//      }
//    }
//
//    JFrame f = new JFrame("ColorPane example");
//    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    f.setContentPane(new JScrollPane(pane));
//    f.setSize(600, 400);
//    f.setVisible(true);
//  }
}

           