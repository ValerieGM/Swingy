package controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageException {
    public static BufferedImage imageUpload(String url) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(url));
        } catch(IOException e) {
            System.out.println("Unable To Read Image");
            System.exit(0);
        }
        return image;
    }
}
