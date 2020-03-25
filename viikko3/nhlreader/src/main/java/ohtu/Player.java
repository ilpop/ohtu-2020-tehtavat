
package ohtu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player implements Comparable <Player>{
    private List <Player> playas = new ArrayList <>();
    private String name;
    private String nationality;
    private int goals;
    private int assists;
    private int points;
    
    

    public void setName(String name) {
        this.name = name;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setGoals(int goals) {
        this.goals = goals;
    }
    
    public int getGoals() {
        return goals;
    }
    
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    public int getAssists() {
        return assists;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public int getPoints() {
        return points;
    }
    
    

    @Override
    public String toString() {
        return name + " " + nationality + " goals: " + goals + " + assists: " + assists +" = " + points;
    }
    @Override
    public int compareTo(Player player) {
       return this.goals - player.goals;

    }
      
}
