package com.networkgraph;


import com.networkgraph.entity.NetworkGraphData;
import org.zkoss.chart.Charts;
import org.zkoss.chart.Series;
import org.zkoss.chart.plotOptions.DataLabels;
import org.zkoss.chart.plotOptions.NetworkGraphLayoutAlgorithm;
import org.zkoss.chart.plotOptions.TreeGraphLink;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


@VariableResolver(DelegatingVariableResolver.class)
 public class NetworkViewModel extends SelectorComposer<Window> {
    @Wire
    Charts chart;
    @Wire
    Textbox coordinatesTextbox;


   /* public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);

        int[] numbers = {1,1,1};

        chart.setType(Charts.NETWOKRGRAPH);
        chart.setModel(NetworkGraphData.getModel());
        chart.setHeight("100%");

        NetworkGraphLayoutAlgorithm npla = chart.getPlotOptions().getNetworkGraph().getLayoutAlgorithm();
        npla.setEnableSimulation(true);
        npla.setFriction(-0.9);

        Series series = chart.getSeries();
        DataLabels dl = series.getDataLabels();
        dl.setEnabled(true);
        dl.setLinkFormat("");
        series.setId("lang-tree");
    }

    */

    @Listen("onClick=#submitButton")
    public void submitCoordinates() {
        String coordinatesStr = coordinatesTextbox.getValue();
        String[] coordinatesArr = coordinatesStr.split(",");
        int[] coordinates = new int[coordinatesArr.length];
        for (int i = 0; i < coordinatesArr.length; i++) {
            coordinates[i] = Integer.parseInt(coordinatesArr[i].trim());
        }

        chart.setType(Charts.NETWOKRGRAPH);
        chart.setModel(NetworkGraphData.getModel(coordinates));
        chart.setHeight("100%");

        NetworkGraphLayoutAlgorithm npla = chart.getPlotOptions().getNetworkGraph().getLayoutAlgorithm();
        npla.setEnableSimulation(true);
        npla.setFriction(-0.9);


        Series series = chart.getSeries();
        DataLabels dl = series.getDataLabels();
        dl.setEnabled(true);
        dl.setLinkFormat("");
        series.setId("lang-tree");

        updateGraph(coordinates);
    }
    private void updateGraph(int[] coordinates) {
//        chart.setVisible(true);
        chart.setModel(NetworkGraphData.getModel(coordinates));
    }

}
