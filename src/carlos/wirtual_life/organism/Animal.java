package carlos.wirtual_life.organism;

import carlos.wirtual_life.Direction;
import carlos.wirtual_life.Field;
import carlos.wirtual_life.Organism;
import carlos.wirtual_life.World;

public abstract class Animal extends Organism {
    public int range ;

    public Animal(World world , Field place , int power , int initiative){
        super(world, place, power, initiative);
        range = 1 ;
    }

    public int PositionX(){
        if(place != null){
            return place.x ;
        }
        return -10 ;
    }

    public int PositionY(){
        if(place != null){
            return place.y ;
        }
        return -10 ;
    }
    public boolean dodge(){ return false ; }
    public boolean goodSmell(Organism rival){ return false ; }



    @Override
    public void action() {
        Direction.DirectionBase direction = Direction.getRandomDirection() ;
        while (!canMove(direction)){
            direction = Direction.getRandomDirection() ;
        }
        goDirection(direction);
    }

    @Override
    public void collision(Organism attacker) {
        Field fightField = place ;
        if(defense(attacker)){
            world.commentator.commentDefense(Species(),attacker.Species());
            attacker.goingDead();
        }
        else if(dodge()){
            world.commentator.commentDodge(attacker.Species(),Species());
            action();
            attacker.occupyField(fightField);
        }
        else{
            world.commentator.commentKilling(attacker.Species(),Species());
            leaveCurrentField();
            goingDead();
            attacker.occupyField(fightField);

        }
    }

    public void goDirection(Direction.DirectionBase direction){
        switch(direction){
            case TOP:
                goToField(PositionY() -range , PositionX() );
                break;
            case LEFT:
                goToField(PositionY() , PositionX()-range);
                break;
            case RIGHT:
                goToField(PositionY(),PositionX()+range);
                break;
            case BOTTOM:
                goToField(PositionY()+range , PositionX());
                break;
            default:
                break;

        }
    }

    public abstract boolean isTheSameSpecies(Organism organism) ;

    private boolean defense(Organism attacker){
        return this.getPower() > attacker.getPower() ;
    }

    protected boolean canMove(Direction.DirectionBase direction){
        switch(direction){
            case TOP:
                return PositionY() > range - 1;
            case LEFT:
                return PositionX() > range - 1;
            case RIGHT:
                return PositionX() < world.getWidth() - range;
            case BOTTOM:
                return PositionY() < world.getHeight() - range;
            default:
                return false ;

        }

    }




    private void goToField(int y , int x){
        int currentX = PositionX() ;
        int currentY = PositionY() ;

        leaveCurrentField();

        if(world.isFreeField(y,x)){
            occupyField(y,x);
        }
        else {
            Organism rival = world.getOrganism(y,x) ;
            if(isTheSameSpecies(rival)){
                occupyField(currentY,currentX);
            }
            else if(goodSmell(rival) || rival.fightOff(this) ){ occupyField(currentY,currentX); }
            else { rival.collision(this);}
        }
    }



}
