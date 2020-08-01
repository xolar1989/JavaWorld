package carlos.wirtual_life;

import java.util.LinkedList;
import java.util.List;

public class Commentator {
    private static final String kill = " kill ";
    private static final String eat = " eat ";
    private static final String avoid = " avoid fight with ";
    private static final String poison = " poison ";
    private static final String burn = " burn ";
    private static final String born = " has been born ";

    private List<String> comments = new LinkedList<>() ;

    public Commentator() {}

    public void commentKilling(String killer , String victim){
        comments.add(killer+kill+victim) ;
    }
    public void commentEating(String animal , String plant){
        comments.add(animal+eat+plant);
    }
    public void commentDodge(String attacker , String refugee){
        comments.add(refugee+avoid+attacker) ;
    }
    public void commentPoison(String animal , String plant){
        comments.add(plant+poison+animal) ;
    }
    public void commentBurning(String offender , String victim){
        comments.add(offender+burn+victim);
    }
    public void commentBirth(String baby){
        comments.add(baby+born) ;
    }

    public void commentDefense(String defender ,String attacker){
        comments.add(defender+" push back the "+attacker+"'s attack" );
    }

    public void announceNextTurn(int turn){
        comments.add("Turn has just started , number of turn : "+ turn) ;
    }

    public void clearComments(){ comments.clear(); }








}
