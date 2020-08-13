package carlos.wirtual_life;

public enum ListOfOrganism {
    GRASS("Grass" ) ,
    MILT("Milt") ,
    GUARANA("Guarana"),
    WOLFBERRY("WolfBerry" ),
    SOSNOWSKYHOGWEED("SosnowskyHogWeed"),
    WOLF("Wolf"),
    SHEEP("Sheep"),
    FOX("Fox"),
    TURTLE("Turtle"),
    ANTYLOPE("Antylope"),
    HUMAN("Human");

    private final String name ;
    public static int Size(){
        return values().length-1 ;
    }

    ListOfOrganism(String name){
        this.name = name ;
    }

    public String getName() {
        return name;
    }

    public static ListOfOrganism getRandomSpecies(){
        int los = (int)(Math.random()*Size()+1) ;
        return getSpecies(los) ;
    }

    public static ListOfOrganism getSpecies(String type){
        for (ListOfOrganism item :ListOfOrganism.values()) {
            if(item.getName().equals(type)){
                return item ;
            }
        }

        return null ;
    }

    public static ListOfOrganism getSpecies(int los){
        ListOfOrganism item = null ;
        for (ListOfOrganism species: ListOfOrganism.values()) {
            if (los == species.ordinal()+1){
                item = species ;
            }
        }
        
        return item ;
    }


}
