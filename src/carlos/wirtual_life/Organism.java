package carlos.wirtual_life;

import carlos.Application.CharacterPhoto;
import carlos.wirtual_life.organism.Animal;
import carlos.wirtual_life.organism.Plant;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Organism {

    public int power ;
    public int initiative ;
    public Field place ;
    public World world ;
    public boolean dead  ;
    public char character ;
    private final CharacterPhoto picture ;


    public Organism(World world , Field place , int power , int initiative){
        this.world = world ;
        this.place = place ;
        if(place != null){
            place.setField(this);
        }
        this.power = power ;
        this.initiative = initiative ;
        this.dead = false ;
        picture = new CharacterPhoto(Species()) ;
    }


    public abstract void action() ;
    public abstract void collision(Organism attacker) ;
    public abstract String Species() ;
    public abstract Organism generateChild() ;
    public boolean fightOff(Organism attacker){ return false ; }



    public CharacterPhoto character(){ return picture ;}


    public void leaveCurrentField(){
        place.beFree();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) { this.power = power; }

    public int getInitiative() {
        return initiative;
    }

    public boolean isAlive() {
        return !dead;
    }

    public void goingDead(){
        this.dead = true ;
        world.killOrganism(this);
    }

    public void occupyField(int y , int x){
        world.getField(y,x).setField(this);
    }
    public void occupyField(Field place){
        place.setField(this);
    }

    public void propagate(){
        Direction.DirectionSpecific direction = Direction.getRandomDirectionSpecific(this) ;

        Organism baby = generateChild() ;

        switch(direction){
            case LEFTTOP:
                baby.occupyField(place.y-1 , place.x-1) ;
                break;
            case TOP:
                baby.occupyField(place.y-1 , place.x);
                break;
            case RIGHTTOP:
                baby.occupyField(place.y-1 , place.x+1);
                break;
            case LEFT:
                baby.occupyField(place.y, place.x-1);
                break;
            case RIGHT:
                baby.occupyField(place.y , place.x+1);
                break;
            case LEFTBOTTOM:
                baby.occupyField(place.y+1 ,place.x-1 );
                break;
            case BOTTOM:
                baby.occupyField(place.y+1 , place.x);
                break;
            case RIGHTBOTTOM:
                baby.occupyField(place.y+1 , place.x+1);
                break;
            default:
                baby = null ;
                break;
        }

        if(baby != null){
            world.commentator.commentBirth(baby.Species());
            world.addChild(baby);
        }

    }

    public void burning(){
        List<Direction.DirectionSpecific> occupiedFields = Direction.getOccupiedFields(this) ;
        if(occupiedFields.contains(Direction.DirectionSpecific.LEFTTOP)){
            burn(place.y-1, place.x-1);
        }
        if(occupiedFields.contains(Direction.DirectionSpecific.TOP)){
            burn(place.y -1 , place.x);
        }
        if(occupiedFields.contains(Direction.DirectionSpecific.RIGHTTOP)){
            burn(place.y-1 , place.x+1);
        }
        if(occupiedFields.contains(Direction.DirectionSpecific.LEFT)){
            burn(place.y , place.x-1);
        }
        if(occupiedFields.contains(Direction.DirectionSpecific.RIGHT)){
            burn(place.y , place.x+1);
        }
        if(occupiedFields.contains(Direction.DirectionSpecific.LEFTBOTTOM)){
            burn(place.y+1 , place.x-1);
        }
        if(occupiedFields.contains(Direction.DirectionSpecific.BOTTOM)){
            burn(place.y+1 , place.x);
        }
        if(occupiedFields.contains(Direction.DirectionSpecific.RIGHTBOTTOM)){
            burn(place.y+1 , place.x+1);
        }
    }

    public void burn(int y , int x){
        Organism organism = world.getOrganism(y,x) ;
        if(organism.isAnimal()){
            world.commentator.commentBurning(Species() , organism.Species());
            organism.leaveCurrentField();
            organism.goingDead();
        }
    }

    private boolean isAnimal(){ return this instanceof Animal ;  }

}
