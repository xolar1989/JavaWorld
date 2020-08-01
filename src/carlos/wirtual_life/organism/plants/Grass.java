package carlos.wirtual_life.organism.plants;

import carlos.wirtual_life.*;
import carlos.wirtual_life.organism.Plant;

public final class Grass extends Plant {
    private static final int Power = 0;

    public Grass(World world, Field place) {
        super(world, place, Power);
        character = 'G' ;
    }

    @Override
    public double giveProbability() {
        return Probability.GRASS.getProbability();
    }

    @Override
    public String Species() { return ListOfOrganism.GRASS.getName(); }

    @Override
    public Organism generateChild() {
        return new Grass(world,null);
    }
}
