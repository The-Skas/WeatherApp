/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity.State;

/**
 *
 * @author skas
 */
public abstract class State<T> {
    public abstract void enter(T obj);
    
    public abstract void execute(T obj);
    
    public abstract void exit(T obj);
    
}
