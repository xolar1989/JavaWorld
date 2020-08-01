package carlos.wirtual_life.organism.plants;

import carlos.wirtual_life.*;
import carlos.wirtual_life.organism.Plant;

public final class SosnowskyHogWeed extends Plant {
    private static final int Power = 10;


    public SosnowskyHogWeed(World world, Field place) {
        super(world, place, Power);
        character = 'S' ;
    }

    @Override
    public void action() {
        burning();
        super.action();
    }

    @Override
    public boolean poisonous() { return true ;  }

    @Override
    public double giveProbability() {
        return Probability.SOSNOWSKYHOGWEED.getProbability();
    }

    @Override
    public String Species() {
        return ListOfOrganism.SOSNOWSKYHOGWEED.getName();
    }

    @Override
    public Organism generateChild() {
        return new SosnowskyHogWeed(world,null);
    }
}
