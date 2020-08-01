package carlos.wirtual_life;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class FileSaves {
    private static final String Address = "saves.txt";
    World world ;

    public FileSaves(World world){
        this.world = world ;
    }

    public void save() throws FileNotFoundException {
        PrintWriter file = new PrintWriter(Address) ;
        file.println(world.getWidth()+" "+world.getHeight());

        for (Organism organism : world.organisms) {
            if(organism.isAlive()){
                String species = organism.Species() ;
                String positionX = Integer.toString(organism.place.x) ;
                String positionY = Integer.toString(organism.place.y) ;
                String power = Integer.toString(organism.getPower()) ;
                file.println(species+" "+positionX+" "+positionY+" "+power);
            }

        }
        file.close();

    }

    public static World load() throws FileNotFoundException {
        World world ;
        File file = new File(Address) ;
        Scanner scanner = new Scanner(file) ;
        int[] cords = new int[2];
        Arrays.fill(cords, -1);
        int index= 0 ;
        while(scanner.hasNextInt()){
            cords[index++] = scanner.nextInt()  ;
        }
        System.out.println(cords[0]+" "+cords[1]);
        if(cords[0] != -1 && cords[1] != -1) {
            world = new World(cords[0], cords[1]);
            while(scanner.hasNextLine()){
               String line = scanner.nextLine() ;
               String[] organismData = line.split(" ") ;
               ListOfOrganism species = ListOfOrganism.getSpecies(organismData[0]);
               if(species != null) {
                   int x = Integer.parseInt(organismData[1]);
                   int y = Integer.parseInt(organismData[2]);
                   int power = Integer.parseInt(organismData[3]);
                   Organism organism =  world.generateOrganism(species , world.getField(y,x)) ;
                   organism.setPower(power);
                   world.addNewOrganism(organism);

                   System.out.println(species+" "+x+" "+y);
               }


            }

            return world ;
        }

            return null ;

    }


}
