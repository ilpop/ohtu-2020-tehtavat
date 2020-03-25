/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author mluukkai
 */
public class Main {
        
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
                
        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        ArrayList <Player> pelaajat = new ArrayList<>();
        
        System.out.println("Players from FIN: " + date + time);
        int yht = 0;
        for (Player player : players) {
            if(player.getNationality().equals("FIN")) {
                yht = player.getGoals()+player.getAssists();
                player.setPoints(yht);
              
                System.out.println(player);
                
            }
            
        }  
        

        
    }
}
