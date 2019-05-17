package com.uncg.save.controllers;

import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.input.DragEvent;

/**
 * I intend to use this class to encompass the references and positions of all
 * objects that are currently on the construction area. Though, I can't find a
 * common reference point... (I.e. I don't know how to update a node's position
 * because the references change when I add them to this hashmap???)
 *
 * @author Joshua
 */
public class NodePositionController {

    private HashMap<Object, Point2D> positions;

    public NodePositionController()
    {
        this.positions = new HashMap<Object, Point2D>();
    }

    public void addNode( Object node, Point2D pos )
    {
        if ( !this.positions.containsKey( node ) )
        {
            this.positions.put( node, pos );
        }
    }

    public void updatePosition( Object oldKey, Point2D newPos )
    {
        System.err.println( positions.containsKey( oldKey ) );
        if ( !positions.containsKey( oldKey ) )
        {
            throw new IllegalArgumentException( "Error: Cannot update position; key is not in hashmap. " );
        } else
        {
            this.positions.put( oldKey, newPos );
        }
    }

    public Point2D getPosition( Object key )
    {
        return this.positions.get( key );
    }

    public int getSize()
    {
        return this.positions.size();
    }
}
