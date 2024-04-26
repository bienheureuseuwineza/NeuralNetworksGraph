package com.networkgraph;

 import org.zkoss.chart.*;
import org.zkoss.chart.plotOptions.DataLabels;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.*;


@VariableResolver(DelegatingVariableResolver.class)
public class TreeGraphComposer extends SelectorComposer<Window> {
   /* @Wire
    Charts chart;
    @Wire
    Textbox coordinatesTextbox;

    //    public  void test(Point.ArrayPoint[] mydata) {
//        mydata.
//    }
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);

        Point.ArrayPoint[] mydata = new Point.ArrayPoint[]{
                new Point.ArrayPoint<>("", "Africa"),
                new Point.ArrayPoint<>("Africa", "East Africa"),
                new Point.ArrayPoint<>("Africa", "West Africa"),
                new Point.ArrayPoint<>("Africa", "South Africa"),
                new Point.ArrayPoint<>("East Africa", "Rwanda"),
                new Point.ArrayPoint<>("East Africa", "Kenya"),
                new Point.ArrayPoint<>("East Africa", "Burundi"),
                new Point.ArrayPoint<>("East Africa", "Uganda"),
                new Point.ArrayPoint<>("East Africa", "Tanzania"),
                new Point.ArrayPoint<>("West Africa", "Ghana"),
                new Point.ArrayPoint<>("West Africa", "Cameroon"),
                new Point.ArrayPoint<>("West Africa", "Togo"),
                new Point.ArrayPoint<>("South Africa", "Botswana"),
                new Point.ArrayPoint<>("South Africa", "Eswatini"),
        };


        chart.getChart().setSpacingBottom(30);
        chart.getChart().setMarginRight(120);

        Series series = chart.getSeries();
        series.setType("treegraph");
        series.setKeys("parent", "id", "levels");
        series.setClip(false);
        series.setData(mydata);

        Marker marker = series.getMarker();
        marker.setSymbol("circle");
        marker.setRadius(6);
        marker.setFillColor("#ffffff");
        marker.setLineWidth(3);

        DataLabels dataLabels = series.getDataLabels();
        dataLabels.setAlign("left");
        dataLabels.setPointFormat("{point.id}");
        dataLabels.setStyle("color: '#000000'; textOutline: '3px #ffffff'; whiteSpace: 'nowrap'");
        dataLabels.setX(24);
        dataLabels.setCrop(false);
        dataLabels.setOverflow("none");

        Level[] levels = new Level[]{new Level(), new Level(), new Level(), new Level(), new Level()};
        levels[0].setLevel(1);
        levels[0].setLevelIsConstant(false);
        levels[1].setLevel(2);
        levels[1].setColorByPoint(true);
        levels[2].setLevel(3);
        levels[2].getColorVariation().setKey("brightness");
        levels[2].getColorVariation().setTo(-0.5);
        levels[3].setLevel(4);
        levels[3].getColorVariation().setKey("brightness");
        levels[3].getColorVariation().setTo(0.5);
        levels[4].setLevel(6);
        levels[4].getDataLabels().setX(10);
        levels[4].getMarker().setRadius(4);
        series.setLevels(levels);
    }

    private static Point.ArrayPoint[] treegraphChartData = new Point.ArrayPoint[]{
            new Point.ArrayPoint("", "Proto Indo-European"),
            new Point.ArrayPoint("Proto Indo-European", "Balto-Slavic"),
            new Point.ArrayPoint("Proto Indo-European", "Germanic"),
            new Point.ArrayPoint("Proto Indo-European", "Celtic"),
            new Point.ArrayPoint("Proto Indo-European", "Italic"),
            new Point.ArrayPoint("Proto Indo-European", "Hellenic"),
            new Point.ArrayPoint("Proto Indo-European", "Anatolian"),
            new Point.ArrayPoint("Proto Indo-European", "Indo-Iranian"),
            new Point.ArrayPoint("Proto Indo-European", "Tocharian"),
            new Point.ArrayPoint("Indo-Iranian", "Dardic"),
            new Point.ArrayPoint("Indo-Iranian", "Indic"),
            new Point.ArrayPoint("Indo-Iranian", "Iranian"),
            new Point.ArrayPoint("Iranian", "Italic", "Test two"),
            new Point.ArrayPoint("Iranian", "Old Persian"),
            new Point.ArrayPoint("Old Persian", "Middle Persian"),
            new Point.ArrayPoint("Indic", "Sanskrit"),
            new Point.ArrayPoint("Italic", "Osco-Umbrian"),
            new Point.ArrayPoint("Italic", "Latino-Faliscan"),
            new Point.ArrayPoint("Latino-Faliscan", "Latin"),
            new Point.ArrayPoint("Celtic", "Brythonic"),
            new Point.ArrayPoint("Celtic", "Goidelic"),
            new Point.ArrayPoint("Germanic", "North Germanic"),
            new Point.ArrayPoint("Germanic", "West Germanic"),
            new Point.ArrayPoint("Germanic", "East Germanic"),
            new Point.ArrayPoint("North Germanic", "Old Norse"),
            new Point.ArrayPoint("North Germanic", "Old Swedish"),
            new Point.ArrayPoint("North Germanic", "Old Danish"),
            new Point.ArrayPoint("West Germanic", "Old English"),
            new Point.ArrayPoint("West Germanic", "Old Frisian"),
            new Point.ArrayPoint("West Germanic", "Old Dutch"),
            new Point.ArrayPoint("West Germanic", "Old Low German"),
            new Point.ArrayPoint("West Germanic", "Old High German"),
            new Point.ArrayPoint("Old Norse", "Old Icelandic"),
            new Point.ArrayPoint("Old Norse", "Old Norwegian"),
            new Point.ArrayPoint("Old Swedish", "Middle Swedish"),
            new Point.ArrayPoint("Old Danish", "Middle Danish"),
            new Point.ArrayPoint("Old English", "Middle English"),
            new Point.ArrayPoint("Old Dutch", "Middle Dutch"),
            new Point.ArrayPoint("Old Low German", "Middle Low German"),
            new Point.ArrayPoint("Old High German", "Middle High German"),
            new Point.ArrayPoint("Balto-Slavic", "Baltic"),
            new Point.ArrayPoint("Balto-Slavic", "Slavic"),
            new Point.ArrayPoint("Slavic", "East Slavic"),
            new Point.ArrayPoint("Slavic", "West Slavic"),
            new Point.ArrayPoint("Slavic", "South Slavic"),
            new Point.ArrayPoint("Proto Indo-European", "Phrygian", 6),
            new Point.ArrayPoint("Proto Indo-European", "Armenian", 6),
            new Point.ArrayPoint("Proto Indo-European", "Albanian", 6),
            new Point.ArrayPoint("Proto Indo-European", "Thracian", 6),
            new Point.ArrayPoint("Tocharian", "Tocharian A", 6),
            new Point.ArrayPoint("Tocharian", "Tocharian B", 6),
            new Point.ArrayPoint("Anatolian", "Hittite", 6),
            new Point.ArrayPoint("Anatolian", "Palaic", 6),
            new Point.ArrayPoint("Anatolian", "Luwic", 6),
            new Point.ArrayPoint("Anatolian", "Lydian", 6),
            new Point.ArrayPoint("Iranian", "Balochi", 6),
            new Point.ArrayPoint("Iranian", "Kurdish", 6),
            new Point.ArrayPoint("Iranian", "Pashto", 6),
            new Point.ArrayPoint("Iranian", "Sogdian", 6),
            new Point.ArrayPoint("Old Persian", "Pahlavi", 6),
            new Point.ArrayPoint("Middle Persian", "Persian", 6),
            new Point.ArrayPoint("Hellenic", "Greek", 6),
            new Point.ArrayPoint("Dardic", "Dard", 6),
            new Point.ArrayPoint("Sanskrit", "Sindhi", 6),
            new Point.ArrayPoint("Sanskrit", "Romani", 6),
            new Point.ArrayPoint("Sanskrit", "Urdu", 6),
            new Point.ArrayPoint("Sanskrit", "Hindi", 6),
            new Point.ArrayPoint("Sanskrit", "Bihari", 6),
            new Point.ArrayPoint("Sanskrit", "Assamese", 6),
            new Point.ArrayPoint("Sanskrit", "Bengali", 6),
            new Point.ArrayPoint("Sanskrit", "Marathi", 6),
            new Point.ArrayPoint("Sanskrit", "Gujarati", 6),
            new Point.ArrayPoint("Sanskrit", "Punjabi", 6),
            new Point.ArrayPoint("Sanskrit", "Sinhalese", 6),
            new Point.ArrayPoint("Osco-Umbrian", "Umbrian", 6),
            new Point.ArrayPoint("Osco-Umbrian", "Oscan", 6),
            new Point.ArrayPoint("Latino-Faliscan", "Faliscan", 6),
            new Point.ArrayPoint("Latin", "Portugese", 6),
            new Point.ArrayPoint("Latin", "Spanish", 6),
            new Point.ArrayPoint("Latin", "French", 6),
            new Point.ArrayPoint("Latin", "Romanian", 6),
            new Point.ArrayPoint("Latin", "Italian", 6),
            new Point.ArrayPoint("Latin", "Catalan", 6),
            new Point.ArrayPoint("Latin", "Franco-Provençal", 6),
            new Point.ArrayPoint("Latin", "Rhaeto-Romance", 6),
            new Point.ArrayPoint("Brythonic", "Welsh", 6),
            new Point.ArrayPoint("Brythonic", "Breton", 6),
            new Point.ArrayPoint("Brythonic", "Cornish", 6),
            new Point.ArrayPoint("Brythonic", "Cuymbric", 6),
            new Point.ArrayPoint("Goidelic", "Modern Irish", 6),
            new Point.ArrayPoint("Goidelic", "Scottish Gaelic", 6),
            new Point.ArrayPoint("Goidelic", "Manx", 6),
            new Point.ArrayPoint("East Germanic", "Gothic", 6),
            new Point.ArrayPoint("Middle Low German", "Low German", 6),
            new Point.ArrayPoint("Middle High German", "(High) German", 6),
            new Point.ArrayPoint("Middle High German", "Yiddish", 6),
            new Point.ArrayPoint("Middle English", "English", 6),
            new Point.ArrayPoint("Middle Dutch", "Hollandic", 6),
            new Point.ArrayPoint("Middle Dutch", "Flemish", 6),
            new Point.ArrayPoint("Middle Dutch", "Dutch", 6),
            new Point.ArrayPoint("Middle Dutch", "Limburgish", 6),
            new Point.ArrayPoint("Middle Dutch", "Brabantian", 6),
            new Point.ArrayPoint("Middle Dutch", "Rhinelandic", 6),
            new Point.ArrayPoint("Old Frisian", "Frisian", 6),
            new Point.ArrayPoint("Middle Danish", "Danish", 6),
            new Point.ArrayPoint("Middle Swedish", "Swedish", 6),
            new Point.ArrayPoint("Old Norwegian", "Norwegian", 6),
            new Point.ArrayPoint("Old Norse", "Faroese", 6),
            new Point.ArrayPoint("Old Icelandic", "Icelandic", 6),
            new Point.ArrayPoint("Baltic", "Old Prussian", 6),
            new Point.ArrayPoint("Baltic", "Lithuanian", 6),
            new Point.ArrayPoint("Baltic", "Latvian", 6),
            new Point.ArrayPoint("West Slavic", "Polish", 6),
            new Point.ArrayPoint("West Slavic", "Slovak", 6),
            new Point.ArrayPoint("West Slavic", "Czech", 6),
            new Point.ArrayPoint("West Slavic", "Wendish", 6),
            new Point.ArrayPoint("East Slavic", "Bulgarian", 6),
            new Point.ArrayPoint("East Slavic", "Old Church Slavonic", 6),
            new Point.ArrayPoint("East Slavic", "Macedonian", 6),
            new Point.ArrayPoint("East Slavic", "Serbo-Croatian", 6),
            new Point.ArrayPoint("East Slavic", "Slovene", 6),
            new Point.ArrayPoint("South Slavic", "Russian", 6),
            new Point.ArrayPoint("South Slavic", "Ukrainian", 6),
            new Point.ArrayPoint("South Slavic", "Belarusian", 6),
            new Point.ArrayPoint("South Slavic", "Rusyn", 6)
    };

    */


    @Wire
    Charts chart;
    @Wire
    Textbox coordinatesTextbox;
    @Wire
    Button generateButton;

    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
//        generateButton.addEventListener("onClick", event -> generateTreeGraph());
        generateTreeGraph();
    }

    private void generateTreeGraph() {
//         Split the input text into an array of levels
        String[] levels = coordinatesTextbox.getValue().split(",");
        System.out.println(Arrays.toString(levels));
        Series series = chart.getSeries();
        series.setData(new Point[]{});

        List<Point> lastLevelNodes = new ArrayList<>();

        for (int i = 0; i < levels.length; i++) {
            // Convert the string representation of the level into an integer
            int number = Integer.parseInt(levels[i]);
            System.out.println("number is: " + number);
            List<Point> currentLevelNodes = new ArrayList<>();
            System.out.println(currentLevelNodes);
            for (int j = 0; j < i + 1; j++) {
                Point point = new Point();
                String currentId = "Level " + i + "Node " + j;
                // Assign a unique identifier and name to the current point
                point.setId(currentId);
                point.setName(currentId);

                // No need to connect the first level nodes to a parent
                if (i == 0) {
                    // Add the point directly to the series
                    series.addPoint(point);
                    System.out.println("point" + point);
                    // Add the point to the list of current level nodes
                    currentLevelNodes.add(point);
                    continue;
                }

                // Connect the current node to every parent node from the last level
                for (Point parentPoint : lastLevelNodes) {
                    Point newPoint = new Point();
                    // Assign a unique identifier and name to the new point
                    newPoint.setId(currentId + "To" + parentPoint.getId());
                    newPoint.setName(currentId);
                    // Set the parent of the new point to the identifier of the parent point
                    newPoint.setParent(parentPoint.getId());
                    // Add the new point to the list of current level nodes
                    currentLevelNodes.add(newPoint);
                    // Add the new point to the series
                    series.addPoint(newPoint);
                }
            }
            // Update the list of last level nodes to the current level nodes
            lastLevelNodes = currentLevelNodes;
        }

        // Set the type of the series to treegraph
        series.setType("treegraph");
        // Set the keys used for identifying parent and child nodes
        series.setKeys("parent", "id");
        // Disable clipping to ensure all nodes are visible
        series.setClip(false);

        // Configure data labels for the series
        DataLabels dataLabels = series.getDataLabels();
        dataLabels.setAlign("left");
        dataLabels.setPointFormat("{point.name}");
        dataLabels.setStyle("color: '#000000'; textOutline: '3px #ffffff'; whiteSpace: 'nowrap'");
        dataLabels.setX(24);
        dataLabels.setCrop(false);
        dataLabels.setOverflow("none");



    }



}