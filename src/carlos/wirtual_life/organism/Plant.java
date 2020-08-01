package carlos.wirtual_life.organism;

import carlos.wirtual_life.Field;
import carlos.wirtual_life.Organism;
import carlos.wirtual_life.World;

public abstract class Plant extends Organism {
    private static final int Initiative = 0;

    public Plant(World world , Field place , int power ){
        super(world, place, power,Initiative);
    }

    @Override
    public void action() {
       if(canPropagate()){
           propagate();
       }
    }

    @Override
    public void collision(Organism attacker) {
        Field fightField = place ;
        leaveCurrentField();

        if(poisonous()){
            world.commentator.commentPoison(attacker.Species(),Species());
            attacker.goingDead();
        }
        else{
            world.commentator.commentEating(attacker.Species(),Species());
            Bonus(attacker);
            attacker.occupyField(fightField);
        }
        goingDead();
    }

    public boolean poisonous(){ return false ;  }

    public void Bonus(Organism organism){}

    public abstract double giveProbability() ;

    public boolean canPropagate(){
        return Math.random() <= giveProbability();
    }

}
