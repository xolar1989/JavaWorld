package carlos.wirtual_life.organism.animals;

import carlos.wirtual_life.Field;
import carlos.wirtual_life.ListOfOrganism;
import carlos.wirtual_life.Organism;
import carlos.wirtual_life.World;
import carlos.wirtual_life.organism.Animal;

public final class Wolf extends Animal {
    private static final int Initiative = 5;
    private static final int Power = 9;

    public Wolf(World world, Field place) {
        super(world, place, Power, Initiative);
        character = 'W' ;
    }

    @Override
    public boolean isTheSameSpecies(Organism organism) {
        return organism instanceof Wolf;
    }

    @Override
    public String Species() {
        return ListOfOrganism.WOLF.getName();
    }

    @Override
    public Organism generateChild() {
        return new Wolf(world,null);
    }
}
