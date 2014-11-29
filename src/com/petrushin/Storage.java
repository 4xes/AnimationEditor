package com.petrushin;

import javafx.scene.shape.Shape;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Petrushin Alexey on 29.11.2014.
 */
public class Storage {
    private LinkedList<HashSet<Shape>> storage = new LinkedList<HashSet<Shape>>();

    public Storage(){
    }

    public int size(){
        return storage.size();
    }

}
