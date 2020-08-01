package carlos.Application;

import carlos.wirtual_life.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class CharacterPhoto  {
    private BufferedImage image ;
    public CharacterPhoto(String species){
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

    }


    protected  Graphics2D draw(Graphics g , int x , int y , Window window) {
        Graphics2D g2d = (Graphics2D) g ;
        g2d.setBackground(Color.RED);
        g2d.drawImage(image,x*40,y*40,40,40,window) ;
        return g2d ;
    }


}
