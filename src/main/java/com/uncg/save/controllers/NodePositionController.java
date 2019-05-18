/*
 * Copyright 2019 Nancy Green
 * This file is part of AVIZE.
 *
 * AVIZE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AVIZE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AVIZE.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.uncg.save.controllers;

import com.uncg.save.argumentviewtree.ArgumentViewTree;
import com.uncg.save.util.Vec2D;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * Edit 05/17/19: The main problem is that JavaFX files are NOT serializable.
 * Therefore, I can't write them to a file and load them back up into the
 * project. I don't have that aforesaid common reference point to save them
 * from. What do I do?
 *
 * @author Joshua Crotts
 */
public class NodePositionController {

    private HashMap<KeyObjectPair, Vec2D> positions;

    public NodePositionController()
    {
        this.positions = new HashMap<KeyObjectPair, Vec2D>();
    }

    /**
     * Adds the node object with the respective position to the hashmap. If it
     * currently exists in the map, its value (pos) is updated to the new
     * position.
     *
     * @param node
     * @param pos
     */
    public void add( Object key, Object type, Vec2D pos )
    {
        //If we are adding a string and a tree, w
        if ( key instanceof String && type instanceof ArgumentViewTree )
        {
            this.positions.put( new KeyObjectPair( ( String ) key, ( ArgumentViewTree ) type ), pos );
        }
    }

    public Point2D getPosition( Object key )
    {
        return ( Point2D ) this.positions.get( key );
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
     * Essentially what I'm trying to do is serialize the objects so they can be
     * written to the file. Unfortunately, Pane cannot be serialized since it's
     * native JavaFX so I'm not really sure what to do about that.
     * 
     * Update 05/17/19: I think what I'm gonna try to do first is serialize everything
     * that I can, and transient everything else that doesn't necessarily NEED to be 
     * serialized. Maybe this will work? Honestly, I have no idea. 
     *
     * @param filePath
     */
    private final void writeObject( String filePath )
    {
        ObjectOutputStream oos = null;

        try
        {
            oos = new ObjectOutputStream( new FileOutputStream( filePath ) );
        } catch ( IOException e )
        {
            e.printStackTrace();
        }

        Iterator it = this.positions.entrySet().iterator();

        while ( it.hasNext() )
        {
            
            //Grabs the KeyObjectPair and Vec2D object pair
            Map.Entry pair = ( Map.Entry ) it.next();
            
            //The key in the hashmap is the KeyObjectPair object
            KeyObjectPair o = ( KeyObjectPair ) pair.getKey();

            try
            {
                //If it's a key, then we know the respective value is an ArgViewTree
                if ( o.key instanceof String )
                {
                    //The key should be a KeyObjectPair 
                    oos.writeObject( ( ArgumentViewTree ) o.obj );
                    oos.writeObject( ( Point2D ) pair.getValue() );
                }
                System.out.println( "Done" );

            } catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * A very basic class to represent a key/value pairing for 
     * objects and their respective ID's. This is primarily used for 
     * ArgumentViewTrees since they have treeIDs and then the AVT themselves,
     * but I'm sure I can implement it in the other objects.
     */
    private final class KeyObjectPair {

        private final Object key;
        private final Object obj;

        public KeyObjectPair( Object k, Object o )
        {
            this.key = k;
            this.obj = o;
        }

        public Object getKey()
        {
            return this.key;
        }

        public Object getObject()
        {
            return this.obj;
        }
    }
}
