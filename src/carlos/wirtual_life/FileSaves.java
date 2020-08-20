package carlos.wirtual_life;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSaves {
    private static final String ADDRESS = "saves.txt";
    World world ;

    public FileSaves(World world){
        this.world = world ;
    }

    public void save() throws FileNotFoundException {
        PrintWriter file = new PrintWriter(ADDRESS) ;
        file.println(world.getWidth()+" "+world.getHeight());
        world.organisms.stream().filter(Organism::isAlive).forEach(organism ->{
            file.println(organism.Species()+" "+organism.place.x+" "+organism.place.y+" "
                    +organism.getPower());
        });
        file.close();
    }

    public static World load() throws FileNotFoundException {
        File file = new File(ADDRESS) ;
        if(!file.exists()){
            try {
                file.createNewFile() ;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        Scanner scanner = new Scanner(file) ;
        return loadFile(scanner);
    }


    private static World loadFile(Scanner scanner){
        World world = null;
        int[] cords = new int[2];
        Arrays.fill(cords, -1);
        int index= 0 ;
        while(scanner.hasNextInt()){
            cords[index++] = scanner.nextInt()  ;
        }
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

                }
            }
        }

        return world!=null && world.containHuman() ? world : null ;
    }



}
