package carlos.wirtual_life;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Direction {
    public enum DirectionBase {
        TOP,LEFT,RIGHT,BOTTOM
    }

    public enum DirectionSpecific {
        LEFTTOP,TOP,RIGHTTOP,LEFT,
        RIGHT,LEFTBOTTOM,BOTTOM,RIGHTBOTTOM,
        LACK
    }

    public static DirectionBase getRandomDirection(){
        int number =(int) (Math.random()*4+1) ;
        return Direction.getDirection(number);
    }

    public static DirectionSpecific getRandomDirectionSpecific(Organism organism){
        List<DirectionSpecific> freeFieldsAround = possibleDirectionsSpecific(organism).get("freeFieldsAround") ;
        int number = (int)(Math.random()*freeFieldsAround.size()) ;
        return freeFieldsAround.get(number) ;
    }

    public static List<DirectionSpecific> getOccupiedFields(Organism organism){
        return possibleDirectionsSpecific(organism).get("occupiedFieldsAround") ;
    }

    private static Map<String , List<DirectionSpecific> > possibleDirectionsSpecific(Organism organism){
        List<DirectionSpecific> freeFieldsAround = new LinkedList<>() ;
        List<DirectionSpecific> occupiedFieldsAround = new LinkedList<>() ;

        Map<String , List<DirectionSpecific>> fieldAround = new HashMap<>() ;
        fieldAround.put("freeFieldsAround" , freeFieldsAround ) ;
        fieldAround.put("occupiedFieldsAround" , occupiedFieldsAround) ;

        int PositionY = organism.place.y ;
        int PositionX = organism.place.x ;

        if(PositionY > 0){
            if(organism.world.getField(PositionY-1,PositionX).isFree()){
                freeFieldsAround.add(DirectionSpecific.TOP) ;
            }
            else { occupiedFieldsAround.add(DirectionSpecific.TOP); }
            if(PositionX >0){
                if(organism.world.getField(PositionY-1 , PositionX-1).isFree() ){
                    freeFieldsAround.add(DirectionSpecific.LEFTTOP) ;
                }
                else { occupiedFieldsAround.add(DirectionSpecific.LEFTTOP) ;}
            }
            if(PositionX < organism.world.getWidth() -1 ){
                if(organism.world.getField(PositionY-1 , PositionX+1).isFree() ){
                    freeFieldsAround.add(DirectionSpecific.RIGHTTOP) ;
                }
                else { occupiedFieldsAround.add(DirectionSpecific.RIGHTTOP) ;  }
            }
        }
        if(PositionY < organism.world.getHeight() - 1){
            if(organism.world.getField(PositionY+1 , PositionX).isFree()){
                freeFieldsAround.add(DirectionSpecific.BOTTOM) ;
            }
            else { occupiedFieldsAround.add(DirectionSpecific.BOTTOM) ; }
            if(PositionX>0){
                if(organism.world.getField(PositionY+1 , PositionX-1).isFree() ){
                    freeFieldsAround.add(DirectionSpecific.LEFTBOTTOM) ;
                }
                else { occupiedFieldsAround.add(DirectionSpecific.LEFTBOTTOM) ; }
            }
            if(PositionX < organism.world.getWidth() -1){
                if(organism.world.getField(PositionY+1 , PositionX+1).isFree()){
                    freeFieldsAround.add(DirectionSpecific.RIGHTBOTTOM) ;
                }
                else { occupiedFieldsAround.add(DirectionSpecific.RIGHTBOTTOM) ; }
            }
        }
        if(PositionX>0){
            if(organism.world.getField(PositionY , PositionX-1).isFree()){
                freeFieldsAround.add(DirectionSpecific.LEFT);
            }
            else { occupiedFieldsAround.add(DirectionSpecific.LEFT) ; }
        }
        if(PositionX < organism.world.getWidth() - 1){
            if(organism.world.getField(PositionY , PositionX+1).isFree()){
                freeFieldsAround.add(DirectionSpecific.RIGHT);
            }
            else { occupiedFieldsAround.add(DirectionSpecific.RIGHT) ; }
        }

        if(freeFieldsAround.isEmpty()){ freeFieldsAround.add(DirectionSpecific.LACK) ;}
        if(occupiedFieldsAround.isEmpty()){ occupiedFieldsAround.add(DirectionSpecific.LACK) ;}

        return fieldAround ;
    }

    private static DirectionBase getDirection(int n){
        DirectionBase result = null ;
        for (DirectionBase direction: DirectionBase.values()) {
            if (n == direction.ordinal()+1){
               result = direction ;
            }
        }

        return result ;

    }

}
