package carlos.wirtual_life;

public enum ListOfOrganism {
    GRASS("Grass") ,
    MILT("Milt") ,
    GUARANA("Guarana"),
    WOLFBERRY("WolfBerry"),
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
        switch (los){
            case 1:
                item=GRASS;
                break;
            case 2:
                item=MILT;
                break;
            case 3:
                item=GUARANA;
                break;
            case 4:
                item=WOLFBERRY;
                break;
            case 5:
                item=SOSNOWSKYHOGWEED;
                break;
            case 6:
                item=WOLF;
                break;
            case 7:
                item=SHEEP;
                break;
            case 8:
                item=FOX;
                break;
            case 9:
                item=TURTLE;
                break;
            case 10:
                item=ANTYLOPE;
                break;
            default:
                break;
        }

        return item ;
    }


}
