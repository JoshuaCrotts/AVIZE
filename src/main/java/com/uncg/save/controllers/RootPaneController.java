/*
 * Copyright 2017 Nancy Green
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

import com.uncg.save.models.DataModel;
import com.uncg.save.models.SchemeModel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

/**
 * FXML controller for the main underlying pane of the application.
 *
 */
public class RootPaneController implements Initializable {

    @FXML
    private ScrollPane mainScrollPane;
    @FXML
    private TitleAndMenuBarController titleAndMenuBarController;
    @FXML
    private DataListController dataListController;
    @FXML
    protected SchemeListController schemeListController;
    @FXML
    private ConstructionAreaController constructionAreaController;
    @FXML
    private AnchorPane dataAnchorPane;
    @FXML
    private AnchorPane caseStudyAnchorPane;
    @FXML
    private AnchorPane schemesAnchorPane;
    @FXML
    private Button hideDataButton;
    @FXML
    private Button hideSchemesButton;

    private List<DataModel> dataModelList;

    private List<SchemeModel> schemeModelList;

    private boolean dataButtonHidden = true;
    private boolean dataUp = false;
    private boolean schemesUp = true;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double screenWidth;
    
    private final int INIT_SCHEME_PANE_WIDTH = 350;
    private final int INIT_DATA_PANE_WIDTH = 350;
    private int prevSchemeWidth = INIT_SCHEME_PANE_WIDTH;

    @Override
    public void initialize( URL url, ResourceBundle rb )
    {
        hideDataButton.setDisable( true );

        javafx.stage.Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D rectangle2D = screen.getVisualBounds();
        screenWidth = rectangle2D.getWidth();

        dataAnchorPane.setPrefWidth( 0 );

        schemesAnchorPane.setPrefWidth( INIT_SCHEME_PANE_WIDTH );
        hideDataButton.setMaxWidth( 0 );
        hideDataButton.setMinWidth( 0 );
        hideSchemesButton.setMaxWidth( 25 );
        hideSchemesButton.setMinWidth( 25 );
        mainScrollPane.setMaxWidth( screenWidth - dataAnchorPane.getPrefWidth() - schemesAnchorPane.getPrefWidth() - 25 );
        mainScrollPane.setMinWidth( screenWidth - dataAnchorPane.getPrefWidth() - schemesAnchorPane.getPrefWidth() - 25 );
        constructionAreaController.getMainPane().setMinWidth( mainScrollPane.getMaxWidth() );
        titleAndMenuBarController.setParentController( this );
    }

    public void setDataModelList( List<DataModel> modelList )
    {
        this.dataModelList = modelList;
        dataListController.setDataModelElements( dataModelList );
    }

    public void replaceDataModelList( List<DataModel> modelList )
    {
        this.dataModelList = modelList;
        dataListController.replaceDataModelElements( dataModelList );
    }

    public void setSchemeModelList( List<SchemeModel> modelList )
    {
        this.schemeModelList = modelList;
        schemeListController.setSchemeModelElements( schemeModelList );
    }

    private void enableDataButton()
    {
        hideDataButton.setMaxWidth( 25 );
        hideDataButton.setMinWidth( 25 );
        mainScrollPane.setMaxWidth( mainScrollPane.getMaxWidth() - 25 );
        mainScrollPane.setMinWidth( mainScrollPane.getMinWidth() - 25 );
        hideDataButton.setDisable( false );
        dataButtonHidden = false;
    }
    

    @FXML
    public void toggleData()
    {
        if ( dataButtonHidden )
        {
            enableDataButton();
        }
        
        int width = INIT_DATA_PANE_WIDTH;
        
        if (  ! dataUp )
        {
            showData( width );
            dataUp =  ! dataUp;
        } else
        {
            hideData( width );
            dataUp =  ! dataUp;
        }
    }

    private void showData( int width )
    {
        System.out.println( "Showing!" );
        dataAnchorPane.setMinWidth( width );
        dataAnchorPane.setMaxWidth( width );
        mainScrollPane.setMaxWidth( mainScrollPane.getMaxWidth() - width );
        mainScrollPane.setMinWidth( mainScrollPane.getMinWidth() - width );
        hideDataButton.setText( "←←←←←←←←←←" );
        constructionAreaController.getMainPane().setMinWidth( mainScrollPane.getMinWidth() );
        constructionAreaController.getMainPane().setPrefWidth( constructionAreaController.getMainPane().getWidth() - width );
    }

    private void hideData( int width )
    {
        System.out.println( "Hiding!" );
        dataAnchorPane.setMinWidth( 0 );
        dataAnchorPane.setMaxWidth( 0 );
        mainScrollPane.setMaxWidth( mainScrollPane.getMaxWidth() + width );
        mainScrollPane.setMinWidth( mainScrollPane.getMinWidth() + width );
        hideDataButton.setText( "→→→→→→→→→→" );
        constructionAreaController.getMainPane().setMinWidth( mainScrollPane.getMinWidth() );
        constructionAreaController.getMainPane().setPrefWidth( constructionAreaController.getMainPane().getWidth() + width );
    }

    /**
     * Attempting to experiment with dynamic window resizing. According to
     * various online sources, AnchorPanes are NOT the way to go; SplitPanes
     * may be necessary.
     */
    @FXML
    public void toggleSchemes()
    {
        if (  ! schemesUp )
        {
            //int width = this.getSchemeSize();
            //this.schemeListController.getMainScrollPane().setPrefWidth( width );
            //this.prevSchemeWidth = width;
            //showSchemes( width );
            showSchemes( this.prevSchemeWidth );
            
            schemesUp =  ! schemesUp;
        } else
        {
            hideSchemes( this.prevSchemeWidth );
            schemesUp =  ! schemesUp;
        }
    }

    private void showSchemes( int width )
    {
        
        schemesAnchorPane.setMinWidth( width );
        schemesAnchorPane.setMaxWidth( width );
        mainScrollPane.setMaxWidth( mainScrollPane.getMaxWidth() - width );
        mainScrollPane.setMinWidth( mainScrollPane.getMinWidth() - width );
        constructionAreaController.constructionAreaSizeCheck();
        hideSchemesButton.setText( "→→→→→→→→→→" );
        constructionAreaController.getMainPane().setMinWidth( mainScrollPane.getMinWidth() );
        constructionAreaController.getMainPane().setPrefWidth( constructionAreaController.getMainPane().getWidth() + width );
        

    }

    private void hideSchemes( int width )
    {
        schemesAnchorPane.setMinWidth( 0 );
        schemesAnchorPane.setMaxWidth( 0 );
        mainScrollPane.setMaxWidth( mainScrollPane.getMaxWidth() + width );
        mainScrollPane.setMinWidth( mainScrollPane.getMinWidth() + width );
        constructionAreaController.constructionAreaSizeCheck();
        hideSchemesButton.setText( "←←←←←←←←←←" );
        constructionAreaController.getMainPane().setMinWidth( mainScrollPane.getMinWidth() );
        constructionAreaController.getMainPane().setPrefWidth( constructionAreaController.getMainPane().getWidth() - width );
        
    }

    private int getSchemeSize()
    {
        TextInputDialog dialog = new TextInputDialog( "Scheme Window Size" );
        dialog.setTitle( "Scheme Width" );
        dialog.setHeaderText( "Input Scheme Width, or Blank For Default (350):" );

        Optional<String> pixelSize = dialog.showAndWait();

        int width = -1;

        if ( pixelSize.isPresent() )
        {
            try
            {
                width = Integer.parseInt( pixelSize.get() );

            } catch ( NumberFormatException ex )
            {
                System.err.println( "Cannot cast " + pixelSize + "to Integer." );
                System.exit( 1 );
            }
        } else
        {
            return ( width = 350 );
        }

        return width;
    }

    public ConstructionAreaController getConstructionAreaController()
    {
        return this.constructionAreaController;
    }

    public boolean schemesShowing()
    {
        return this.schemesUp;
    }

    public boolean dataShowing()
    {
        return this.dataUp;
    }

    public List<SchemeModel> getSchemeModels()
    {
        return this.schemeModelList;
    }

    public List<DataModel> getDataModels()
    {
        return this.dataModelList;
    }
}
