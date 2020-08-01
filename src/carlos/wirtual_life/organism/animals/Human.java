package carlos.wirtual_life.organism.animals;

import carlos.wirtual_life.*;
import carlos.wirtual_life.organism.Animal;

public final class Human extends Animal {
    private static final int Initiative = 4;
    private static final int Power = 5;
    private boolean superPower ;


    public Human(World world, Field place) {
        super(world, place, Power, Initiative);
        superPower = false ;
    }

    public void action(Direction.DirectionBase direction){

        if(isAlive() && superPower ) burning();
        if(canMove(direction)){
            goDirection(direction);
        }
        else {
            System.out.println("Human has gone out of board");
            leaveCurrentField();
            goingDead();
        }


        if(isAlive() && superPower ) burning();


    }

    public boolean isSuperPower() {
        return superPower;
    }

    public void setSuperPower(boolean superPower) {
        this.superPower = superPower;
    }

    public void switchSuperPower(){
        if(!superPower){
            System.out.println("SuperPower is activated");
        }
        else {
            System.out.println("SuperPower is disabled");
        }
        this.superPower = !this.superPower ;
    }

    @Override
    public void burn(int y, int x) {
        Organism organism = world.getOrganism(y,x) ;

        world.commentator.commentBurning(Species() , organism.Species());
        organism.leaveCurrentField();
        organism.goingDead();

    }

    @Override
    public boolean isTheSameSpecies(Organism organism) { return false; }

    @Override
    public String Species() { return ListOfOrganism.HUMAN.getName(); }

    @Override
    public Organism generateChild() { return null; }
}
