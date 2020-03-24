/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author hoffrenm
 */
public class QueryBuilder {

    Stack<Matcher> pino;

    public QueryBuilder() {
        this.pino = new Stack<>();
    }

    public Matcher build() {
        if (pino.isEmpty()) {
            return new All();
        }
        
        Matcher[] args = Arrays.copyOf(pino.toArray(), pino.size(), Matcher[].class);
        
        pino.clear();
        
        return new And(args);
    }

    public QueryBuilder playsIn(String team) {
        this.pino.add(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.pino.add(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.pino.add(new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.pino.add(new Or(m1, m2));
        return this;
    }

}
