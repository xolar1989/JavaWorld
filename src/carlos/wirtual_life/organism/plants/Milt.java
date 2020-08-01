package carlos.wirtual_life.organism.plants;

import carlos.wirtual_life.*;
import carlos.wirtual_life.organism.Plant;

public final class Milt extends Plant {
    private static final int Power = 0;

    public Milt(World world, Field place) {
        super(world, place, Power);
        character = 'M' ;
    }

    @Override
    public double giveProbability() {
        return Probability.MILT.getProbability();
    }

    @Override
    public String Species() {
        return ListOfOrganism.MILT.getName();
    }

    @Override
    public Organism generateChild() {
        return new Milt(world,null);
    }
}
