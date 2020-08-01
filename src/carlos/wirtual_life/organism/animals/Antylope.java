package carlos.wirtual_life.organism.animals;

import carlos.wirtual_life.Field;
import carlos.wirtual_life.ListOfOrganism;
import carlos.wirtual_life.Organism;
import carlos.wirtual_life.World;
import carlos.wirtual_life.organism.Animal;

public final class Antylope extends Animal {
    private static final int Initiative = 4;
    private static final int Power = 4;


    public Antylope(World world , Field place ){
        super(world, place, Power, Initiative);
        range = 2 ;
        character = 'A' ;
    }

    @Override
    public boolean dodge() { return Math.random() >= 0.5; }

    @Override
    public boolean isTheSameSpecies(Organism organism) {
        return organism instanceof Antylope;
    }

    @Override
    public String Species() { return ListOfOrganism.ANTYLOPE.getName(); }

    @Override
    public Organism generateChild() {
        return new Antylope(world,null);
    }
}
