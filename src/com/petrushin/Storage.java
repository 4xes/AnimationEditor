package com.petrushin;

import com.petrushin.shape.BoundLine;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Text;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
        free();
        if(current <= (this.size()-1)){
             this.remove(current);
             if(current > 0) {
                current--;
             }
            if(this.size() == 0){
                this.add(null);
            }
            if(this.get(current) != null){
                shapes().addAll(this.get(current));
            }
        }


    }

    public void free(){
        if(get(current) != null){
            Iterator<Node> it = this.get(current).iterator();
            while(it.hasNext()){
                Node obj = it.next();
                if(obj.getClass().equals(BoundLine.class)){
                    ((BoundLine)obj).beFree();
                }
                it.remove();
                shapes().remove(obj);

            }
        }
        shapes().removeAll(shapes());
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

}
