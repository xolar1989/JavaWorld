package carlos;
import carlos.wirtual_life.* ;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import carlos.Application.Window ;

import javax.xml.xpath.XPath;


public class Main {



    public static void main(String[] args) throws IOException {

       World world = FileSaves.load() ;

       if(world == null){
           world = new World(15,15) ;
           world.start();
       }

        World finalWorld = world;
        EventQueue.invokeLater(() -> {
            Window window = new Window(finalWorld) ;

        });



    }



}
