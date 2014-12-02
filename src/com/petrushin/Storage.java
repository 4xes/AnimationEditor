package com.petrushin;

import com.petrushin.shape.Anchor;
import com.petrushin.shape.BoundLine;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Petrushin Alexey on 29.11.2014.
 */
public class Storage extends LinkedList<HashSet<Node>>{

    public int currentNumberFrame;

    public int getLastNumberFrame() {
        return this.size()-1;
    }

    public int getCurrentNumberFrame() {
        return currentNumberFrame;
    }

    private void setCurrentNumberFrame(int currentNumberFrame) {
        this.currentNumberFrame = currentNumberFrame;
    }

    public void deleteShapeFromCurrentNode(Object obj){
        get(currentNumberFrame).remove(obj);
    }

    public Storage(){
        super();
        currentNumberFrame = 0;
        this.add(new HashSet<Node>());
    }
    public void saveFrame(ObservableList<Node> childrens){
        this.get(currentNumberFrame).addAll(childrens);
    }

    public HashSet<Node> nextFrame(){
        if(currentNumberFrame == getLastNumberFrame()){
            currentNumberFrame++;
            return new HashSet<Node>();
        }else{
            currentNumberFrame++;
            return get(currentNumberFrame);
        }
    }

    public HashSet<Node> backFrame(){
        if(currentNumberFrame > 0)
            currentNumberFrame--;
        return get(currentNumberFrame);
    }

    public HashSet<Node> deleteFrame(){

        //clear all
        Iterator<Node> iterator = get(currentNumberFrame).iterator();
        while(iterator.hasNext()){
            Node node = iterator.next();
            if(node instanceof BoundLine){
                ((BoundLine)node).unbind();
                iterator.remove();
            }
            else if(node instanceof Anchor){
                iterator.remove();
            }
        }

        if(currentNumberFrame > 0){
            currentNumberFrame--;
            return get(currentNumberFrame);
        }
        return new HashSet<Node>();
    }



}
