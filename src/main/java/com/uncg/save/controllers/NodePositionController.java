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
 * Edit 05/17/19: The main problem is that JavaFX files are NOT serializable.
 * Therefore, I can't write them to a file and load them back up into the
 * project. I don't have that aforesaid common reference point to save them
 * from. What do I do?
 *
 * @author Joshua Crotts
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
     * Essentially what I'm trying to do is serialize the objects so they can be
     * written to the file. Unfortunately, Pane cannot be serialized since it's
     * native JavaFX so I'm not really sure what to do about that.
     *
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
