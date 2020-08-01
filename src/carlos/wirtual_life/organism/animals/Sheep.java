package carlos.wirtual_life.organism.animals;

import carlos.wirtual_life.Field;
import carlos.wirtual_life.ListOfOrganism;
import carlos.wirtual_life.Organism;
import carlos.wirtual_life.World;
import carlos.wirtual_life.organism.Animal;

public final class Sheep extends Animal {
    private static final int Initiative = 4;
    private static final int Power = 4;


    public Sheep(World world, Field place) {
        super(world, place, Power, Initiative);
        character = 'H' ;
    }

    @Override
    public boolean isTheSameSpecies(Organism organism)
    {
        return organism instanceof Sheep;
    }

    @Override
    public String Species() {
        return ListOfOrganism.SHEEP.getName();
    }

    @Override
    public Organism generateChild() {
        return new Sheep(world,null);
    }
}
