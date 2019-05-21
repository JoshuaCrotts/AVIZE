package com.uncg.save.util;

import java.io.Serializable;
import javafx.geometry.Point2D;

/**
 * This class is an extension upon the JavaFX implementation of 
 * Point2D. The only difference is that, because JavaFX objects
 * aren't serializable, the only way around it is to create a subclass
 * which implements Serializable.
 *
 * @author Joshua Crotts
 */
public class Vec2D extends Point2D implements Serializable {
    
    public Vec2D( double x, double y )
    {
        super( x, y );
    }
}
