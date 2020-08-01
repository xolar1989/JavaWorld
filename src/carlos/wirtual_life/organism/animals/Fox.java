package carlos.wirtual_life.organism.animals;

import carlos.wirtual_life.Field;
import carlos.wirtual_life.ListOfOrganism;
import carlos.wirtual_life.Organism;
import carlos.wirtual_life.World;
import carlos.wirtual_life.organism.Animal;

public final class Fox extends Animal {
    private static final int Initiative = 7;
    private static final int Power = 3;

    public Fox(World world, Field place) {
        super(world, place, Power , Initiative);
        character = 'F' ;
    }

    @Override
    public boolean goodSmell(Organism rival) { return rival.getPower() > getPower(); }

    @Override
    public boolean isTheSameSpecies(Organism organism) {
        return organism instanceof Fox;
    }

    @Override
    public String Species() { return ListOfOrganism.FOX.getName(); }

    @Override
    public Organism generateChild() {
        return new Fox(world,null);
    }
}
