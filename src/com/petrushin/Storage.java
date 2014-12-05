package com.petrushin;

import com.petrushin.shape.Anchor;
import com.petrushin.shape.BoundLine;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Text;

import java.lang.reflect.AnnotatedArrayType;
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
        //тоже самое, что и next, но только в другую сторону
        save();

        boolean currentIsFirst = current == 0;
        boolean frameIsOne = this.size() == 1;
        if(frameIsOne){
            return;
        }
        if(currentIsFirst){
            shapes().removeAll(shapes());
            current = size() - 1;
            loadFrame();
        }else{
            shapes().removeAll(shapes());
            current--;
            loadFrame();
        }

        refreshNumFrame();
    }

    public void next(){
        //сохранить, если кадров больше одного, то переходить на кадр вперед или от последнего к первому и так далее.
        save();
        boolean currentIsLast = current == this.size() - 1;
        boolean frameIsOne = this.size() == 1;

        if(frameIsOne){
            return;
        }
        if(currentIsLast){
            shapes().removeAll(shapes());
            current = 0;
            loadFrame();
        }else{
            shapes().removeAll(shapes());
            current++;
            loadFrame();
        }

        refreshNumFrame();
    }

    public void _new(){
        save();
        shapes().removeAll(shapes());
        this.add(++current, null);

        refreshNumFrame();
    }

    public void refreshNumFrame(){
        JavaFxApplication.getInstance().numFrameProperty().set(("Scene " + (current + 1) + "/" + size()));
    }


    public void save(){
        if(this.get(current) == null){
            set(current, new HashSet<Node>(shapes()));
        }else{
            clearStorage();
            this.get(current).addAll(shapes());
        }

    }

    public void loadFrame(){
        HashSet<Node> nodes = this.get(current);
        if(nodes != null){
            loadFrame(nodes);
        }

    }
    public void loadFrame(Collection<Node> c){
        shapes().addAll(c);
        Iterator<Node> it = c.iterator();
        while(it.hasNext()){
            Node node = it.next();
            if(node.getClass().equals(Anchor.class)){
                node.toFront();
            }
        }


    }

    public void delete(){
        clearAll();
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

        refreshNumFrame();
    }


    public void clearStorage(){
        //если элементы из storage не находятся на поле, то освободить от них простарство перед сохранением

        Iterator<Node> it = this.get(current).iterator();
        if(it == null){
            return;
        }
        if(shapes().size() == 0){
             while(it.hasNext()){
                Node obj = it.next();
                if(obj.getClass().equals(BoundLine.class)){
                    ((BoundLine)obj).beFree();
                }
                it.remove();
             }
        }else{
            while(it.hasNext()){
                Node obj = it.next();
                if(obj.getClass().equals(BoundLine.class)){
                    if(!shapes().contains(obj)){
                        ((BoundLine)obj).beFree();
                    }
                }
                it.remove();
            }
        }

    }

    public void clearAll(){
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
        shapes().removeAll(shapes());
        LinkedList<Node> oldList = new LinkedList<Node>();
        HashSet<BoundLine> boundLines = new HashSet<BoundLine>();
        LinkedList<Node> newList = new LinkedList<Node>();

        Iterator<Node> itNodes = this.get(current).iterator();
        while (itNodes.hasNext()){
            Node node = itNodes.next();
            oldList.add(node);

            if(node.getClass().equals(BoundLine.class)){
                boundLines.add((BoundLine)node);
                newList.add(null);
            }
            if(node.getClass().equals(Anchor.class)){
                Anchor oldAnchor = (Anchor)node;
                newList.add(new Anchor(oldAnchor.getCenterX(), oldAnchor.getCenterY()));
            }
        }



        Iterator<BoundLine> itLines = boundLines.iterator();
        while(itLines.hasNext()){
            BoundLine line = itLines.next();
            int indexLine = oldList.indexOf(line);
            int indexStart = oldList.indexOf(line.getStart());
            int indexEnd = oldList.indexOf(line.getEnd());
            newList.set(indexLine, new BoundLine((Anchor)newList.get(indexStart), (Anchor)newList.get(indexEnd)));
        }
        oldList.clear();
        boundLines.clear();
        this.add(++current, null);
        loadFrame(newList);

        refreshNumFrame();
    }


    public ObservableList<Node> shapes(){
        return JavaFxApplication.getInstance().getField().getChildren();
    }

}
