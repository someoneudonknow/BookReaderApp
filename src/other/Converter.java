package other;

import java.awt.Graphics;
import java.sql.Blob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;

public final class Converter {

    public static Blob convertImageToBlob(ImageIcon imageIcon) {
        if(imageIcon == null) return null;
        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.createGraphics();
        imageIcon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();

        Blob blob = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            blob = new SerialBlob(imageBytes);
        } catch (IOException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return blob;
    }

    public static ImageIcon convertBlobToImageIcon(Blob blob) {
        if(blob == null) return null;
        byte[] imageBytes;
        ImageIcon imageIcon = null;
        try {
            imageBytes = blob.getBytes(1L, (int) blob.length());
            imageIcon = new ImageIcon(imageBytes);
        } catch (SQLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return imageIcon;
    }
}
