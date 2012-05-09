/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator.gui.listeners;

import cz.cvut.generator.core.Generator;
import cz.cvut.generator.graph.Edge;
import cz.cvut.generator.graph.Graph;
import cz.cvut.generator.graph.GraphType;
import cz.cvut.generator.graph.OutputParser;
import cz.cvut.generator.graph.OutputType;
import cz.cvut.generator.gui.util.Components;
import cz.cvut.generator.gui.util.DoubleRequiredValidator;
import cz.cvut.generator.gui.util.DoubleValidatorException;
import cz.cvut.generator.gui.util.GInputSettings;
import cz.cvut.generator.gui.util.IntegerRequiredValidator;
import cz.cvut.generator.gui.util.IntegerValidatorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ZDENEK
 */
public class GenerateActionListener implements ActionListener {

    private GInputSettings inputSettings;
    private Generator g;
    private JTextArea ta;
    private JButton generateButton;
    private JButton stopButton;
    private OutputType outputFormat;
    private String dir;

    @Override
    public void actionPerformed(ActionEvent e) {
        ta = (JTextArea) Components.component.get("LogTextArea");
        generateButton = (JButton) Components.component.get("generateButton");
        stopButton = (JButton) Components.component.get("stopButton");

        /* checking input settings*/
        if (!createInputSettingsModel()) {
            ta.setText("");
            return;
        }

        /*checking output settings*/
        JTextField fileNameField = (JTextField) Components.component.get("fileName");
        JTextField dirField = (JTextField) Components.component.get("dir");

        /* generate graph*/
        generateButton.setEnabled(false);
        stopButton.setVisible(true);
        ta.setText("In progress...");

        g = new Generator();
        g.setNodesCount(inputSettings.getNodeCount());
        g.setMaxEdgeWeight(inputSettings.getMaxWeight());
        g.setMinEdgeWeight(inputSettings.getMinWeight());

        g.setProperties(inputSettings.getProperties());
        g.initialize();

        if (inputSettings.getType() == GraphType.SIMPLE) {
            g.generateSimple();
        } else if (inputSettings.getType() == GraphType.DISCRETE) {
            g.generate();
        } else if (inputSettings.getType() == GraphType.BIPARTITE) {
            g.generateBipartite();
        } else if (inputSettings.getType() == GraphType.COMPLETE) {
            g.generateComplete();
        } else if (inputSettings.getType() == GraphType.TREE) {
            g.generateTree();
        } else if (inputSettings.getType() == GraphType.CYCLIC) {
            g.generateCyclic();
        } else if (inputSettings.getType() == GraphType.ACYCLIC) {
            g.generateAcyclic();
        }


        dir = dirField.getText();
        dir = dir.trim();

        if ( dir.compareTo("") == 0) {
            ((JLabel) Components.component.get("filePathErrMsg")).setVisible(true);
            return;
        }

        
        JComboBox fileFormatField = ((JComboBox) Components.component.get("fileFormat"));
        if (fileFormatField.getSelectedItem().toString().compareTo("Trivial Graph Format") == 0) {
            outputFormat = OutputType.TRIVIAL;
        } else if (fileFormatField.getSelectedItem().toString().compareTo("DOT") == 0) {
            outputFormat = OutputType.DOT;
        } else if (fileFormatField.getSelectedItem().toString().compareTo("XML") == 0) {
            outputFormat = OutputType.XML;
        } else if (fileFormatField.getSelectedItem().toString().compareTo("Incidence Matrix") == 0) {
            outputFormat = OutputType.INCIDENCE;
        } else {
            outputFormat = OutputType.ADJACENCY;
        }


        // TODO Thread

//        Runnable doWorkRunnable = new Runnable() {
//
//            @Override
//            public void run() {
                Graph graf = g.getGraph();
                for (Edge edge : graf.getEdges()) {
                    System.out.println(edge);
                }

                OutputParser op = new OutputParser(g, outputFormat, dir);
                try {
                    op.generateOutput();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GenerateActionListener.class.getName()).log(Level.SEVERE, null, ex);
                }

                ta.setText("In progress...                            [ 100% ]\n"
                        + "Graph complete...             in file: [ " + dir + " ]");
                generateButton.setEnabled(true);
                stopButton.setVisible(false);
//            }
//        };

    }

    private boolean createInputSettingsModel() {
        boolean status = true;
        GraphType type = getGraphType();
        int vertexCount = 0;
        inputSettings = new GInputSettings(type, getProperties());

        if (type != GraphType.BIPARTITE) {
            JLabel vertexCountErrMsg = (JLabel) Components.component.get("VertexCountErrMsg");
            try {
                vertexCount = IntegerRequiredValidator.isValid((JTextField) Components.component.get("VertexCount"));
                vertexCountErrMsg.setVisible(false);
                inputSettings.setNodeCount(vertexCount);
            } catch (IntegerValidatorException ex) {
                vertexCountErrMsg.setVisible(true);
                status = false;
            }
        } else {
            JLabel nVertexCountErrMsg = (JLabel) Components.component.get("nVertexCountErrMsg");
            JLabel mVertexCountErrMsg = (JLabel) Components.component.get("mVertexCountErrMsg");
            int n = 0;
            int m = 0;
            try {
                n = IntegerRequiredValidator.isValid((JTextField) Components.component.get("nVertexCount"));
                nVertexCountErrMsg.setVisible(false);
                inputSettings.setN(n);
            } catch (IntegerValidatorException ex) {
                nVertexCountErrMsg.setVisible(true);
                status = false;
            }

            try {
                m = IntegerRequiredValidator.isValid((JTextField) Components.component.get("mVertexCount"));
                mVertexCountErrMsg.setVisible(false);
                inputSettings.setM(m);
            } catch (IntegerValidatorException ex) {
                mVertexCountErrMsg.setVisible(true);
                status = false;
            }
        }


        if (((JCheckBox) Components.component.get("weighted")).isSelected()) {
            JLabel minWeightErrMsg = (JLabel) Components.component.get("minWeightErrMsg");
            JLabel maxWeightErrMsg = (JLabel) Components.component.get("maxWeightErrMsg");
            double minWeight = 0;
            double maxWeight = 0;
            try {
                minWeight = DoubleRequiredValidator.isValid((JTextField) Components.component.get("minWeight"));
                minWeightErrMsg.setVisible(false);
                inputSettings.setMinWeight(minWeight);
            } catch (DoubleValidatorException ex) {
                minWeightErrMsg.setVisible(true);
                status = false;
            }

            try {
                maxWeight = DoubleRequiredValidator.isValid((JTextField) Components.component.get("maxWeight"));
                maxWeightErrMsg.setVisible(false);
                inputSettings.setMaxWeight(maxWeight);
            } catch (DoubleValidatorException ex) {
                maxWeightErrMsg.setVisible(true);
                status = false;
            }
        }
        return status;
    }

    private List<GraphType> getProperties() {
        List<GraphType> properties = new ArrayList<GraphType>();
        if (((JCheckBox) Components.component.get("directed")).isSelected()) {
            properties.add(GraphType.DIRECTED);
        }
        if (((JCheckBox) Components.component.get("weighted")).isSelected()) {
            properties.add(GraphType.WEIGHTED);
        }
        return properties;
    }

    private GraphType getGraphType() {
        if (((JRadioButton) Components.component.get("simpleGraphType")).isSelected()) {
            return GraphType.SIMPLE;
        }
        if (((JRadioButton) Components.component.get("discreteGraphType")).isSelected()) {
            return GraphType.DISCRETE;
        }
        if (((JRadioButton) Components.component.get("treeGraphType")).isSelected()) {
            return GraphType.TREE;
        }
        if (((JRadioButton) Components.component.get("cyclicGraphType")).isSelected()) {
            return GraphType.CYCLIC;
        }
        if (((JRadioButton) Components.component.get("bipartiteGraphType")).isSelected()) {
            return GraphType.BIPARTITE;
        }
        if (((JRadioButton) Components.component.get("acyclicGraphType")).isSelected()) {
            return GraphType.ACYCLIC;
        }
        if (((JRadioButton) Components.component.get("simpleGraphType")).isSelected()) {
            return GraphType.SIMPLE;
        }
        return null;
    }
}
