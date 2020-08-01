package carlos.wirtual_life.organism.plants;

import carlos.wirtual_life.*;
import carlos.wirtual_life.organism.Plant;

public final class Guarana extends Plant {

    private static final int Power = 0;

    public Guarana(World world, Field place) {
        super(world, place, Power);
        character = 'G' ;
    }

    @Override
    public void Bonus(Organism organism) { organism.setPower(organism.getPower()+3); }

    @Override
    public double giveProbability() {
        return Probability.GUARANA.getProbability();
    }

    @Override
    public String Species() {
        return ListOfOrganism.GUARANA.getName();
    }

    @Override
    public Organism generateChild() {
        return new Guarana(world,null);
    }



}
