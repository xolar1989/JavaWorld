package carlos.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LegendPhoto extends JPanel {
    private BufferedImage image ;
    public LegendPhoto(String species){
        super();

        String pathName = "photos/"+species+".png" ;
        File imageFile = new File(pathName) ;
        try{
            image = ImageIO.read(imageFile) ;
        }
        catch (IOException e){
            System.out.println("coś się popsuło i nie było mnie słychać");
            e.printStackTrace();
        }
        Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
    }


}
