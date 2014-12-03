package com.petrushin;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Petrushin Alexey on 29.11.2014.
 */
public class Storage extends LinkedList<HashSet<Node>>{

    public int current;

    public int getCurrent() {
        return current;
    }

    private void setCurrent(int current) {
        this.current = current;
    }

    public void deleteShapeFromCurrentNode(Object obj){
        get(current).remove(obj);
    }

    public Storage(){
        super();
        add(null);
        current = 0;
    }

    public void back(){
        save();

        if(current > 0){
            shapes().removeAll(shapes());
            shapes().addAll(get(--current));
        }else{

        }
    }

    public void next(){
        save();
        boolean currentLessLast = current < this.size() - 1;
        System.out.println(current + " " + (this.size()-1));
        if(currentLessLast){
            shapes().removeAll(shapes());
            shapes().addAll(get(++current));
        }else{
            add(null);
            shapes().removeAll(shapes());
            current++;
        }
    }

    public void refreshActionButtons(Text textNumberText, Text buttonBack){
        textNumberText.setText("Scene " + (current + 1) + "/" + size());
        if(current < size() - 1)
            buttonBack.setDisable(true);
        else
            buttonBack.setDisable(false);
    }


    public void save(){
        if(this.get(current) == null){
            set(current, new HashSet<Node>(shapes()));
        }else{
            this.get(current).clear();
            this.get(current).addAll(shapes());
        }

    }

    public void delete(){
        save();
    }

    public void copy(){
        save();
    }

    public void print(){
        for(Node node: get(current)){
            System.out.println(node.getClass());
        }
    }

    public ObservableList<Node> shapes(){
        return JavaFxApplication.getInstance().getField().getChildren();
    }



//    public void saveFrame(ObservableList<Node> childrens){
//        this.get(current).addAll(childrens);
//    }

//    public HashSet<Node> nextFrame(){
//        if(current == getLastNumberFrame()){
//            current++;
//            return new HashSet<Node>();
//        }else{
//            current++;
//            return get(current);
//        }
//    }
//
//    public HashSet<Node> backFrame(){
//        if(current > 0)
//            current--;
//        return get(current);
//    }
//
//    public HashSet<Node> deleteFrame(){
//
//        //clear all
//        Iterator<Node> iterator = get(current).iterator();
//        while(iterator.hasNext()){
//            Node node = iterator.next();
//            if(node instanceof BoundLine){
//                ((BoundLine)node).unbind();
//                iterator.remove();
//            }
//            else if(node instanceof Anchor){
//                iterator.remove();
//            }
//        }
//
//        if(current > 0){
//            current--;
//            return get(current);
//        }
//        return new HashSet<Node>();
//    }



}
