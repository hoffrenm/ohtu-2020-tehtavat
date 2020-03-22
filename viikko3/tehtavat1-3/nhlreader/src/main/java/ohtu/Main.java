/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author mluukkai
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        Gson mapper = new Gson();
        List<Player> players = Arrays.asList(mapper.fromJson(bodyText, Player[].class));

        String nation = "FIN";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime today = LocalDateTime.now();

        players = players.stream().filter(player -> player.getNationality().equals(nation)).sorted().collect(Collectors.toList());

        System.out.println("Players from " + nation + " " + formatter.format(today) + "\n");
        for (Player player : players) {
            System.out.println(player);
        }
    }

}
