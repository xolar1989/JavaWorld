package carlos.wirtual_life;


import carlos.Application.Window;

import carlos.wirtual_life.organism.animals.*;
import carlos.wirtual_life.organism.plants.*;


import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class World {

    public Field[][] Map ;
    public Organism hero ;
    public List<Organism> organisms = new LinkedList<>() ;
    public Commentator commentator ;
    private final int width;
    private final int height ;
    private boolean gameover ;
    private int turn ;
    final double AmountOfPopulation ;
    private List<Organism> toAdd = new LinkedList<>() ;
    private List<Organism> toRemove = new LinkedList<>() ;
    private FileSaves file ;

    public World(int width, int height ){
        this.width = width ;
        this.height = height ;
        commentator = new Commentator() ;
        this.turn = 1 ;
        this.gameover = false ;
        this.Map = new Field[height][width]  ;
        for(int y = 0 ; y<height ; y++){
            for (int x = 0; x <width ; x++) {
                this.Map[y][x] = new Field(y,x) ;
            }
        }
        this.AmountOfPopulation = 0.1 ;
        file = new FileSaves(this) ;
    }

    public void start() {
        newGame();
    }

    public void saveGame() throws FileNotFoundException {
        file.save();
    }


    public void newGame(){
        positionHero();

        int amountOfOrganism =(int)(width*height*AmountOfPopulation) ;
        int x, y ;
        Organism baby;
        for (int i = 0; i <amountOfOrganism ; i++) {
            y = (int)( Math.random()*height) ;
            while (!isFreeLine(y)){
                y = (int)( Math.random()*height) ;
            }
            x = (int)( Math.random()*width) ;
            while (!isFreeField(y,x)){
                x = (int)( Math.random()*width) ;
            }

            baby = generateRandomOrganism(getField(y,x)) ;
            addNewOrganism(baby);
        }

    }



    public boolean makeTurn(Direction.DirectionBase direction){

        commentator.announceNextTurn(turn);

        for (Organism organism:organisms) {
            if(organism.isAlive()){
                if(organism instanceof Human){ ((Human)organism ).action(direction); }
                else organism.action();
            }
        }
        if(hero.isAlive()){
            for (Organism corpse:toRemove) {
                organisms.remove(corpse);
            }
            toRemove.clear();
            for (Organism baby:toAdd ) {
                addNewOrganism(baby);
            }

            nextTurn();
            organisms.forEach(organism -> System.out.println(organism.Species()+" "+organism.place.x+" "+organism.place.y+" "
                    +organism.getPower()));

            return true ;
        }
        else {
            System.out.println("Hero going die");
            endGame();
            return false ;
        }


    }

    public boolean isGameover() {
        return gameover;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void killOrganism(Organism organism){
        toRemove.add(organism) ;
    }
    public void addChild(Organism organism){
        toAdd.add(organism) ;
    }



    public boolean isFreeField(int y , int x){
        return this.Map[y][x].isFree() ;
    }

    public Field getField(int y , int x){
        return this.Map[y][x];
    }

    public Organism getOrganism(int y,int x){
        return getField(y,x).getOrganism();
    }

    private void endGame(){
        gameover = true ;
    }

    private void positionHero(){
        hero = new Human(this , getField(height -2 , width/2)) ;
        addNewOrganism(hero) ;
    }

    public void addNewOrganism(Organism organism){

        if(organisms.isEmpty()){
            organisms.add(organism);
        }
        else {
            boolean added = false ;
            for (Organism item:organisms) {
                if(organism.getInitiative() > item.getInitiative()){
                    organisms.add(organisms.indexOf(item) ,organism );
                    added = true ;
                    break ;
                }
            }
            if(!added) organisms.add(organism) ;
        }
    }

    public boolean containHuman(){
        return hero != null ;
    }

    public Organism generateOrganism(ListOfOrganism Type , Field place){
        Organism baby = null ;
        switch(Type){
            case GRASS:
                baby = new Grass(this,place );
                break;
            case MILT:
                baby = new Milt(this, place) ;
                break;
            case GUARANA:
                baby = new Guarana(this,place);
                break;
            case WOLFBERRY:
                baby = new WolfBerry(this,place) ;
                break;
            case SOSNOWSKYHOGWEED:
                baby = new SosnowskyHogWeed(this,place) ;
                break;
            case ANTYLOPE:
                baby = new Antylope(this,place) ;
                break;
            case FOX:
                baby = new Fox(this,place) ;
                break;
            case SHEEP:
                baby = new Sheep(this,place) ;
                break ;
            case TURTLE:
                baby = new Turtle(this,place) ;
                break;
            case WOLF:
                baby = new Wolf(this,place) ;
                break ;
            case HUMAN:
                baby = new Human(this,place) ;
                hero = baby ;
            default:
                break;
        }

        return baby ;
    }

    private Organism generateRandomOrganism(Field place){
       ListOfOrganism Type = ListOfOrganism.getRandomSpecies() ;

       return generateOrganism(Type,place);
    }

    private int nextTurn(){
        return ++turn ;
    }

    private boolean isFreeLine(int y){

        for (int i = 0; i <width ; i++) {
            if(isFreeField(y,i)){
                return true ;
            }
        }
        return false ;

    }
}
