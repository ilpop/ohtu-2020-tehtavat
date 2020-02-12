/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iilkka
 */


public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
                        
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            
            return players;
            
        }
            
        };
    
    Statistics stats;
    
    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }
    

    @Test
    public void PlayersSize(){
        
        assertEquals(5, readerStub.getPlayers().size());
    }
    
    @Test
    public void team() {
        
        assertEquals(25, stats.team("EDM").size());
    }
    
    @Test
    public void topScores() {
        assertEquals(1, stats.topScorers(0).size());
    }
    
      
    @Test
    public void getName() {
        assertEquals("Semenko" , readerStub.getPlayers().get(0).getName());
    }
    
    @Test 
    public void getTeam() {
        assertEquals("EDM", readerStub.getPlayers().get(4).getTeam());
        
    }
    
    @Test 
    public void getGoals() {
        assertEquals(35, readerStub.getPlayers().get(4).getGoals());
    }
    
    @Test
    public void getAssist() {
        assertEquals(89, readerStub.getPlayers().get(4).getAssists());
    }
    
    @Test
    public void setTeam() {
        Player player = readerStub.getPlayers().get(4);
        player.setTeam("CAN");
        assertEquals("CAN", player.getTeam());
    }
    
    @Test
    public void setName() {
        Player player = readerStub.getPlayers().get(4);
        player.setName("Wayne Gretzky");
        assertEquals("Wayne Gretzky", player.getName()); 
    }

}
