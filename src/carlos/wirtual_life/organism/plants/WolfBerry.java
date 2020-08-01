package carlos.wirtual_life.organism.plants;

import carlos.wirtual_life.*;
import carlos.wirtual_life.organism.Plant;


public final class WolfBerry extends Plant {
    private static final int Power = 99;

    public WolfBerry(World world, Field place) {
        super(world, place, Power);
        character = 'B' ;
    }

    @Override
    public boolean poisonous() { return true; }

    @Override
    public double giveProbability() {
        return Probability.WOLFBERRY.getProbability();
    }

    @Override
    public String Species() {
        return ListOfOrganism.WOLFBERRY.getName();
    }

    @Override
    public Organism generateChild() {
        return new WolfBerry(world,null);
    }
}
