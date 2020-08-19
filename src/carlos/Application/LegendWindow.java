package carlos.Application;

import carlos.wirtual_life.ListOfOrganism;
import carlos.wirtual_life.Organism;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LegendWindow extends Frame  {
    JPanel[] lines;
    private static final int FieldSize = 40;



    public LegendWindow(){
        ArrayList<String> photoNames = new ArrayList<>() ;
        for (ListOfOrganism item:ListOfOrganism.values()) {
           photoNames.add(item.getName());
        }

        lines = new JPanel[photoNames.size()] ;

        for (int i = 0; i <photoNames.size() ; i++) {
            lines[i] = new JPanel(new BorderLayout()) ;
            lines[i].add( new LegendPhoto(photoNames.get(i))  ,BorderLayout.WEST );
            lines[i].add(new Label(photoNames.get(i)) , BorderLayout.EAST) ;

            add(lines[i]) ;

        }

        setLayout(new GridLayout(ListOfOrganism.Size()+1,1));

        setSize(400,FieldSize*(ListOfOrganism.Size()+1)+100);
        setLocation(0,50);
        setVisible(false);
    }
    public void open(){

     setVisible(true);
    }
    public void close(){
        setVisible(false);
    }

}
