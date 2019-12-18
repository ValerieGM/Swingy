package controller;

import model.helpers.View;

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
            View.print("Unable To Read Image");
            System.exit(0);
        }
        return image;
    }
}
