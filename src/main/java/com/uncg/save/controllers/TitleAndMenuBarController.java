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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    @FXML
    /**
     * Displays a window displaying buttons which lead to tutorials
     * for the user to follow in case they are confused on some 
     * functionality of the program. 
     * 
     * @TODO Replace with actual tutorial as opposed to copy-and-paste
     *       from documentation
     */
    public void showTutorial() {
        //Main VBox environment
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        //VBox specifically for the workspace
        //because of how much relevant information
        //must exist for the tutorial
        VBox workspaceBox = new VBox();
        workspaceBox.setPadding(new Insets(10));
        workspaceBox.setSpacing(10);

        /**
         * Button information
         */
        Button dataPanelButton = new Button("1. Data Panel");
        Button argSchemeButton = new Button("2. Argument Scheme Panel");
        Button workspaceButton = new Button("3. Workspace");

        //Data panel button information/handler
        dataPanelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String info = "1. What is data?  Text that has been imported into AVIZE for use as support in arguments. Each item has been annotated with metadata such as source, reliability, and likelihood.\n"
                        + "2.	Actions:\n"
                        + "•	Select file: using File menu at upper left, select which data file to open\n"
                        + "•	View/hide metadata: click “Metadata” label to open/close metadata view\n"
                        + "•	Scroll up/down panel; Slide panel open/closed (click border)\n"
                        + "•	Drag and drop data item onto Workspace";
                Alert alert = new Alert(Alert.AlertType.INFORMATION, info);
                alert.setTitle("Data Panel Tutorial - Left Pane");
                alert.setHeaderText("How to Use Evidence/Metadata");
                alert.showAndWait();
            }
        });

        //Argument scheme panel button information/handler
        argSchemeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String info = "1. What is a scheme?  A basic pattern of reasoning.  Each scheme has\n"
                        + "•	One or more premises: starting point of reasoning.  If all of the premises are (or likely to be) true, then the conclusion may be true.\n"
                        + "•	Conclusion: What follows from the premises.\n"
                        + "•	Critical questions: One or more ways of challenging the argument.\n"
                        + "•	Example:  Example of argument \n"
                        + "•	Note: a “generic” scheme is provided if you wish to define your own scheme (with any number of premises and your own list of critical questions).\n"
                        + "2.	Actions:\n"
                        + "•	Scroll up/down panel; Slide panel open/closed (click border)\n"
                        + "•	Open/close scheme Description, Example, or Critical questions: click labels\n"
                        + "•	Drag and drop scheme onto central Workspace to create argument template";
                Alert alert = new Alert(Alert.AlertType.INFORMATION, info);
                alert.setTitle("Argument Scheme Panel Tutorial - Right Pane");
                alert.setHeaderText("How to Create Argument Schemes");
                alert.showAndWait();
            }
        });

        /**
         * Workspace panel button information/handler, needs a separate VBox to
         * handle all of the relevant info as aforesaid
         */
        workspaceButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String info = "";
                Button boxes = new Button("Main Functions");
                Button actions = new Button("Actions");
                Button combineSupportArguments
                        = new Button("Combining Supporting Arguments");
                Button criticalQuestions
                        = new Button("Critical Questions");
                Button counterArguments
                        = new Button("Counter Arguments");

                /**
                 * Now to create the relevant handlers*
                 */
                //Button for the boxes (proposition, arg template, evidence and connectors)
                boxes.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        String infoWorkSpace
                                = "     What is it?  The place for constructing argument diagrams made up of proposition boxes, evidence, argument templates, and connectors.\n"
                                + "•	Proposition box:  Box containing sentence to be used as premise, conclusion, or answer to critical question\n"
                                + "•	Argument template: When scheme (in panel on right-hand side) is dragged and dropped onto workspace, its premises and conclusion are displayed in box and arrow format\n"
                                + "•	Evidence: Items from data panel used as evidence for premise\n"
                                + "•	Connectors: Lines connecting multiple arguments for the same conclusion, counter-arguments, and critical questions (see Actions for Combining Arguments)";

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoWorkSpace);
                        alert.setTitle("Workspace");
                        alert.setHeaderText("Description of Workspace");
                        alert.showAndWait();
                    }
                });

                //Button for the actions
                actions.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        String infoWorkSpace
                                = "•	Create/edit/delete proposition box (Right-click on workspace to create new box; type inside box to edit; right-click inside box then select “Delete proposition” to delete)\n"
                                + "•	Drag and drop data from data panel to workspace; drag and drop data onto other data to make a “clump”; drag out to detach data from clump; drag and drop data or clump onto proposition box or premise to create support (click on “Evidence” label to open/close view)\n"
                                + "•	Drag and drop proposition box onto premise or conclusion of argument scheme template to add to argument; or left-click on empty box in template to type directly into it.\n"
                                + "•	Show/edit/hide comment: Right-click on proposition box to show/hide; Edit by typing in box; move comment around by dragging. \n"
                                + "•	Move argument (Drag and drop conclusion to desired location)\n"
                                + "•	Detach proposition from argument (Right-click, then select “Detach proposition”)\n"
                                + "•	Delete argument (Right-click conclusion, then select “Delete argument”)\n"
                                + "•	View/hide certainty annotation (Right-click, then select on/off); type over to edit";

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoWorkSpace);
                        alert.setTitle("Workspace");
                        alert.setHeaderText("Simple Actions to Do:");
                        alert.showAndWait();
                    }
                });

                //Button for combining supporting arguments
                combineSupportArguments.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String infoWorkSpace
                                = "•	Chained Argument (when conclusion of one argument -- the “subargument” -- is to be used as premise of another argument):  Drag and drop conclusion of subargument onto premise of the other argument and AVIZE will connect the two arguments.  \n"
                                + "•	Multiple Arguments/Same Conclusion (when there is more than one independent argument for the same conclusion): Drag one conclusion onto the conclusion of the other argument. (If the wording of the conclusions is not the same, you are required to choose which wording to use for the combined argument.)  AVIZE will then link the two arguments with the label “Multiple arguments for the same conclusion” with a circle icon above each independent argument. Right-click on the circle icon for the menu of actions specific to that argument. ";

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoWorkSpace);
                        alert.setTitle("Workspace");
                        alert.setHeaderText("Actions for Combining Supporting Arguments");
                        alert.showAndWait();
                    }
                });

                //Button for critical questions
                criticalQuestions.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String infoWorkSpace
                                = "•	Right-click on the name of an argument scheme in an argument diagram in the workspace to see a menu of critical questions; select one and AVIZE will link it to that argument with a “Critical Question” icon\n"
                                + "•	Right-click the critical question icon for an option to delete it.\n"
                                + "•	Multiple critical questions can be added to an argument diagram\n"
                                + "•	Provide an answer for the critical question with a single proposition, or drag an argument onto it.";

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoWorkSpace);
                        alert.setTitle("Workspace");
                        alert.setHeaderText("Actions for Challenging an Argument: Critical Questions");
                        alert.showAndWait();
                    }
                });

                //Button for counter arguments
                counterArguments.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String infoWorkSpace
                                = "•	The conclusion of a counter-argument can simply be the negation of the conclusion of the other argument, or a conclusion that is not compatible with it.\n"
                                + "•	To create: Right click the conclusion of an argument and select “Add counter argument” to add a counter argument, and AVIZE will link it to that argument with a counter-argument icon. \n"
                                + "•	Multiple counter-arguments can be given to the same argument.";

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, infoWorkSpace);
                        alert.setTitle("Workspace");
                        alert.setHeaderText("Actions for Challenging an Argument: Counter Arguments");
                        alert.showAndWait();
                    }
                });
                
                //Creates the new VBox environment for the 
                //workspace tutorial
                workspaceBox.setAlignment(Pos.CENTER);
                workspaceBox.getChildren().addAll(boxes, actions, combineSupportArguments, criticalQuestions, counterArguments);
                
                Scene scene = new Scene(workspaceBox, 450, 250);
                Stage stage = new Stage();
                stage.setTitle("Workspace Tutorial");
                stage.setScene(scene);
                
                stage.show();
            }

        });
        //Adds the buttons to the root (initial VBox)
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(dataPanelButton, argSchemeButton, workspaceButton);

        //Creates a new scene with 
        //the tutorial information
        Scene scene = new Scene(root, 450, 250);
        Stage stage = new Stage();
        stage.setTitle("Tutorial on AIED");
        stage.setScene(scene);

        stage.show();
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
     * 
     * @TODO Make it better lol
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
                argumentsMenu.getItems().add(1, replaceXML);
                argumentsMenu.getItems().get(0).setDisable(true);
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
