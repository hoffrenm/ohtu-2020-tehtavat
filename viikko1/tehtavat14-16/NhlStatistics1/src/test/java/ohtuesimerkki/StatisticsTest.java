package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hoffrenm
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchByTeamReturnsCorrectAmountOfPlayers() {
        List<Player> names = stats.team("EDM");

        assertEquals(3, names.size());
    }

    @Test
    public void searchContainsOnlyPlayersFromSearchedTeam() {
        List<String> names = stats.team("EDM").stream().map(player -> player.getName()).collect(Collectors.toList());

        assertTrue(names.contains("Semenko"));
        assertTrue(names.contains("Kurri"));
        assertTrue(names.contains("Gretzky"));
        assertFalse(names.contains("Yzerman"));
    }

    @Test
    public void searchingNonexistingPlayerReturnsNull() {
        Player player = stats.search("NonExistant");

        assertEquals(null, player);
    }

    @Test
    public void playerCanBeSearchedByName() {
        Player player = stats.search("Lemieux");

        assertEquals("Lemieux", player.getName());
        assertEquals("PIT", player.getTeam());
        assertEquals(45, player.getGoals());
    }

    @Test
    public void topScoresOf1ReturnsTopScore() {
        List<Player> players = stats.topScorers(1);
        Player first = players.get(0);

        assertEquals(1, players.size());
        assertEquals("Gretzky", first.getName());
        assertEquals(124, first.getPoints());
    }

}
