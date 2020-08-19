package carlos.wirtual_life.organism.animals;

import carlos.wirtual_life.Field;
import carlos.wirtual_life.ListOfOrganism;
import carlos.wirtual_life.Organism;
import carlos.wirtual_life.World;
import carlos.wirtual_life.organism.Animal;

public final class Turtle extends Animal {
    private static final int Initiative = 1;
    private static final int Power = 2;

    public Turtle(World world, Field place) {
        super(world, place, Power, Initiative);
        character = 'T' ;
    }

    @Override
    public void action() {
        if(Math.random() < 0.25) { super.action(); }
    }

    @Override
    public boolean fightOff(Organism attacker) { return attacker.getPower() <= 5; }

    @Override
    public boolean isTheSameSpecies(Organism organism) { return organism instanceof Turtle; }

    @Override
    public String Species() { return ListOfOrganism.TURTLE.getName(); }

    @Override
    public Organism generateChild() { return new Turtle(world,null); }
}
