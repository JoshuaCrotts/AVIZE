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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.uncg.save.DataList;
import com.uncg.save.SaveArgScheme;
import com.uncg.save.models.DataModel;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 * FXML controller for the title bar and menu bar of the application
 *
 */
public class TitleAndMenuBarController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu loadMenu;
    @FXML
    private Menu toolsMenu;
    @FXML
    private Menu ethicsMenu;
    @FXML
    private Menu argumentsMenu;

    private RootPaneController parentControl;
    private boolean dataLoaded = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setParentController(RootPaneController control) {
        parentControl = control;

    }

    /**
     * Closes the program.
     */
    @FXML
    private void closeProgram() {
        Stage primaryStage = (Stage) menuBar.getScene().getWindow();
        primaryStage.close();
    }

    /**
     * Removes the last-added object from the pane.
     *
     * @TODO Allow for multi-model objects to be removed with one click
     *
     * @param event
     */
    @FXML
    public void undoAction(ActionEvent event) {
        ConstructionAreaController cac = this.parentControl.getConstructionAreaController();
        Pane mainPane = cac.getMainPane();

        if (mainPane.getChildren().size() > 0) {
//            //undoButton.setDisable(false);
            mainPane.getChildren().remove(mainPane.getChildren().size() - 1);
        }
    }

    /**
     * Clears all currently-existing evidence pieces and arguments from the
     * construction area.
     */
    public void clearDiagram() {
        ConstructionAreaController cac = this.parentControl.getConstructionAreaController();
        if (cac.mainPane.getChildren().isEmpty()) {
            return;
        }

        cac.mainPane.getChildren().clear();
    }

    /**
     * @TODO
     */
    @FXML
    public void feedback() {

    }

    /**
     * @TODO
     */
    @FXML
    public void summary() {

    }

    /**
     * This is simply a tester method to test arbitrary functions during runtime
     * development.
     */
    @FXML
    public void test() {
        ConstructionAreaController cac = this.parentControl.getConstructionAreaController();
        Stack<Object> undos = cac.getActions();
        System.out.println("Stack size: " + undos.size());
    }

    /**
     * Thus far, this only opens the ACM code of ethics pdf for the user.
     *
     * @TODO Add actual codes from an XML file or something.
     */
    @FXML
    public void openEthicsPDF() {
        try {
            Desktop.getDesktop().open(new File("./target/classes/res/acm-code-of-ethics-booklet.pdf/").getAbsoluteFile());
        } catch (IOException e) {
            System.err.println("Error opening ACM ethics booklet: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Saves the current argument scheme to a .sch file. Should use serialized
     * objects so if we load in a new scheme, we know where to place them upon
     * load-in.
     *
     * @TODO
     */
    @FXML
    public void saveArgumentScheme(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Current Scheme");
        this.configureFileChooser(chooser);

        File file = chooser.showSaveDialog(menuBar.getScene().getWindow());
        if (file != null) {
            SaveArgScheme save = new SaveArgScheme(file.getPath());
        }
    }

    /**
     * @TODO
     */
    @FXML
    public void openArgumentScheme() {

    }

    /**
     * Very primitive way of printing the active diagram, but it will take an
     * screen-shot of the current construction area, while minimizing the side
     * bars if they are present.
     */
    @FXML
    public void printArgumentScheme(ActionEvent event) {

        //If either of the two panes are currently active, we need to 
        //temporary disable them so we can get a clean screenshot
        boolean data = false;
        boolean schemes = false;
        if (parentControl.dataShowing()) {
            parentControl.toggleData();
            data = true;
        }
        if (parentControl.schemesShowing()) {
            parentControl.toggleSchemes();
            schemes = true;
        }

        Stage currentScene = (Stage) menuBar.getScene().getWindow();
        WritableImage snapshot = currentScene.getScene().snapshot(null);

        //Flips them back to their original state
        if (data) {
            parentControl.toggleData();
        }
        if (schemes) {
            parentControl.toggleSchemes();
        }

        //Attempts to write the image to a PNG
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save Image");
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            
            chooser.setInitialDirectory(new File(System.getProperty("user.dir")));

            //User-selected path
            File filePath = chooser.showSaveDialog(menuBar.getScene().getWindow());
            if (filePath != null) {
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", filePath.getAbsoluteFile());
            }

        } catch (Exception e) {
            System.err.println("Did you really not expect an error? " + e.getMessage());
        }
    }

    /**
     * Opens an already-existing argument scheme from a .sch file.
     *
     * @TODO
     */
    @FXML
    public void openArgumentSchemePanel() {

    }

    @FXML
    private void loadXMLAppend(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        configureFileChooser(chooser);
        File loadedXML = chooser.showOpenDialog(menuBar.getScene().getWindow());
        if (loadedXML != null) {
            loadData(loadedXML);
        }

        event.consume();
    }

    private void setHandlerForReplaceData(MenuItem item) {
        item.setOnAction(action -> {
            loadXMLReplace(action);
        });
    }

    private void configureFileChooser(FileChooser chooser) {
        chooser.setTitle("Load Data");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML", "*.xml")
        );
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    }

    private void loadData(File file) {
        List<DataModel> dataModelList = new ArrayList<>();
        try {
            DataList dl;
            SchemaFactory sf
                    = SchemaFactory
                            .newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema
                    = sf.newSchema(getClass().getResource("/xml/data.xsd"));

            JAXBContext jaxbContext = JAXBContext.newInstance(DataList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);

            dl = (DataList) jaxbUnmarshaller.unmarshal(file);
            dataModelList = dl.getModels();
            if (!dataLoaded) {
                MenuItem replaceXML = new MenuItem("Replace Data");
                setHandlerForReplaceData(replaceXML);
                loadMenu.getItems().add(1, replaceXML);
                parentControl.toggleData();
            }
            dataLoaded = true;
        } catch (SAXException | JAXBException ex) {
            Logger.getLogger(TitleAndMenuBarController.class.getName()).log(Level.SEVERE, null, ex);
        }

        parentControl.setDataModelList(dataModelList);
    }

    private void loadXMLReplace(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        configureFileChooser(chooser);
        File loadedXML = chooser.showOpenDialog(menuBar.getScene().getWindow());
        if (loadedXML != null) {
            replaceData(loadedXML);
        }
        event.consume();
    }

    private void replaceData(File file) {
        List<DataModel> dataModelList = new ArrayList<>();
        try {
            DataList dl;
            SchemaFactory sf
                    = SchemaFactory
                            .newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema
                    = sf.newSchema(getClass().getResource("/xml/data.xsd"));

            JAXBContext jaxbContext = JAXBContext.newInstance(DataList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);

            dl = (DataList) jaxbUnmarshaller.unmarshal(file);
            dataModelList = dl.getModels();
        } catch (SAXException | JAXBException ex) {
            Logger.getLogger(TitleAndMenuBarController.class.getName()).log(Level.SEVERE, null, ex);
        }

        parentControl.replaceDataModelList(dataModelList);
    }
}
