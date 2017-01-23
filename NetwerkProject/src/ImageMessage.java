import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by Vincent Brands on 01-Jun-16.
 *
 *
 */
public class ImageMessage extends Message implements Serializable {

    private BufferedImage image;

    public ImageMessage(String name, String message,BufferedImage image){
        super(name, message);
        this.image = image;
    }



}
