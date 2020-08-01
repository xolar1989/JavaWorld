package carlos.wirtual_life;

public class Field {

   public int y ;
   public int x ;

   private Organism organism ;

   public Field(int y , int x){
        this.organism = null ;
        this.y = y ;
        this.x = x ;

    }


    public boolean isFree(){
        return organism == null;

    }

    public void setField(Organism organism){
        this.organism = organism ;
        organism.place = this ;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void beFree(){
        this.organism.place = null ;
        this.organism = null ;
    }


}
