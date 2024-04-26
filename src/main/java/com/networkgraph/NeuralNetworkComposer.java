package com.networkgraph;

import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.json.JSONValue;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.util.Notification;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class NeuralNetworkComposer extends GenericForwardComposer<Component> {
    @Wire
    private Component neuralNet;
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        loadData(); // Load data for the neural network visualization
    }

    private void loadData() {
//        try {
//            // Load JSON data from a static file
//            InputStream inputStream = getClass().getResourceAsStream("/data.json");
//            JSONValue jsonValue = JSONParser.parse(new InputStreamReader(inputStream));
//
//            // Extract nodes array from JSON data
//            JSONArray nodesArray = (JSONArray) jsonValue;
//
//            // Process each node in the nodes array
//            for (Object obj : nodesArray) {
//                JSONObject nodeObj = (JSONObject) obj;
//                String label = (String) nodeObj.get("label");
//                int layer = ((Long) nodeObj.get("layer")).intValue();
//
//                // Perform any necessary processing with the node data
//                // For demonstration, we can print the node information
//                System.out.println("Label: " + label + ", Layer: " + layer);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}