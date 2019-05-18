package com.uncg.save.controllers;

import com.uncg.save.argumentviewtree.ArgumentViewTree;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.geometry.Point2D;

/**
 * I intend to use this class to encompass the references and positions of all
 * objects that are currently on the construction area. Though, I can't find a
 * common reference point... (I.e. I don't know how to update a node's position
 * because the references change when I add them to this hashmap???)
 *
 * @author Joshua
 */
public class NodePositionController {

    private HashMap<HashMap<Object, Object>, Point2D> positions;
    private HashMap<Object, Object> keyTypePair;

    public NodePositionController()
    {
        this.positions = new HashMap<HashMap<Object, Object>, Point2D>();
        this.keyTypePair = new HashMap<Object, Object>();
    }

    /**
     * Adds the node object with the respective position to the hashmap. If it
     * currently exists in the map, its value (pos) is updated to the new
     * position.
     *
     * @param node
     * @param pos
     */
    public void add( Object key, Object type, Point2D pos )
    {
        if ( key instanceof String && type instanceof ArgumentViewTree )
        {
            this.keyTypePair.put( ( String ) key, ( ArgumentViewTree ) type );
            this.positions.put( this.keyTypePair, pos );
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

    public void writeObjects( String filePath )
    {
        this.writeObject( filePath );
    }

    /**
     * Essentially what I'm trying to do is serialize the 
     * objects so they can be written to the file. Unfortunately,
     * Pane cannot be serialized since it's native JavaFX so
     * I'm not really sure what to do about that.
     * @param filePath 
     */
    private void writeObject( String filePath )
    {
        Iterator it = this.keyTypePair.entrySet().iterator();
        while ( it.hasNext() )
        {
            Map.Entry pair = ( Map.Entry ) it.next();
            Object o = pair.getKey();

            try ( ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( "target/classes/res/temp.sch" ) ) )
            {

                //If it's a key, then we know the respective value is an ArgViewTree
                if ( o instanceof String )
                {
                    oos.writeObject( ( ArgumentViewTree ) this.keyTypePair.get( ( String ) o ) );
                }
                System.out.println( "Done" );

            } catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }
}
