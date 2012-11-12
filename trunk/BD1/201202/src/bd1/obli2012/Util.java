package bd1.obli2012;

import java.awt.Component;
import java.awt.Container;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class Util {

    public static Properties getProperties() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("./database.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[0x1000];
        while (true) {
            int r = in.read(buf);
            if (r == -1) {
                break;
            }
            out.write(buf, 0, r);
        }
        return out.toByteArray();
    }

    public static List<JCheckBox> getAllCheckbox(final Container c) {
        Component[] comps = c.getComponents();
        List<JCheckBox> compList = new ArrayList<JCheckBox>();
        for (Component comp : comps) {
            if (comp instanceof JCheckBox) {
                compList.add((JCheckBox) comp);
            }
            if (comp instanceof Container) {
                compList.addAll(getAllCheckbox((Container) comp));
            }
        }
        return compList;
    }
}
