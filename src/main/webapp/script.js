//Application configuration to store neural network settings
var app = {
    inputLayerHeight: 0, //Number of nodes in the input layer
    hiddenLayersCount: 0, //Counter of the hidden layers
    //Number of nodes for the hidden layer
    hiddenLayersDepths: [4, 7, 5, 9, 4, 10, 4, 6, 3, 11, 7, 4, 6, 3, 11, 7, 4, 6, 3, 11, 7, 4, 6, 3, 11, 7, 4, 6, 3, 11, 7, 4, 6, 3, 11, 7, 4, 6, 3, 11, 7, 4, 6, 3, 11, 7],
   //Number of nodes for the output layer
    outputLayerHeight: 0,

};
//Object to store the Network graph data
var networkGraph = {
    nodes: [], //Array to store the nodes of the graph
};
//Variables for SVG dimensions and node size
var width = window.innerWidth - 15; //Width of the SVG container(with some padding)
//Fixed height of the SVG container
var height = 400;
//Diameter of each node in the graph
var nodeSize = 15;
//Color scale using D3.js for node coloring based on their layer
var color = d3.scale.category20();

//Even listener for window resize to adjust the graph width and redraw
window.addEventListener("resize", function () {
    //update width to new window size
    width = window.innerWidth;
    //Redraw the graph with new dimensions
    draw();
});

//Function to build the node graph based on the current configuration
function buildNodeGraph() {
    var newGraph = {
        nodes: [], //Initialize an empty array for nodes
    };

    // Construct input layer
    var newFirstLayer = [];
    //Check if the input layer should have nodes
    if (app.inputLayerHeight > 0) {
        for (var i = 0; i < app.inputLayerHeight; i++) {
            //Create a node for each input node
            var newTempLayer = {label: "i" + i, layer: 1};
            //Add to the first layer node list
            newFirstLayer.push(newTempLayer);
        }
    }

    //Construct the hidden layer
    var hiddenLayers = [];
    for (var hiddenLayerLoop = 0; hiddenLayerLoop < app.hiddenLayersCount; hiddenLayerLoop++) {
        var newhiddenLayers = [];
        for (var i = 0; i < app.hiddenLayersDepths[hiddenLayerLoop]; i++) {
            //create a node for each hidden layer
            var newtempLayer = {label: "h" + hiddenLayerLoop + i, layer: hiddenLayerLoop + 2};
            //Add to the current hidden layer node list
            newhiddenLayers.push(newtempLayer);
        }
        //Add all nodes of the current hidden layer to the hiddenLayers array
        hiddenLayers.push(newhiddenLayers);
    }

    // Construct output layer
    var newOutputLayer = [];
    if (app.outputLayerHeight > 0) {
        //Check if the output layer should have nodes
        for (var i = 0; i < app.outputLayerHeight; i++) {
            //Create a node for each output node
            var newTempLayer = {label: "o" + i, layer: app.hiddenLayersCount + 2};
            //Add to the output layer node list
            newOutputLayer.push(newTempLayer);
        }
    }

    // Combine all nodes into the newGraph.nodes array
    var allMiddle = newGraph.nodes.concat.apply([], hiddenLayers);
    newGraph.nodes = newGraph.nodes.concat(
        newFirstLayer,
        allMiddle,
        newOutputLayer
    );
    //Return the fully constructed graph
    return newGraph;
}


//Function to draw the graph on the SVG element
function drawGraph(networkGraph, svg) {
    //Local reference to the network graph
    var graph = networkGraph;
    //Local reference to the nodes in the graph
    var nodes = graph.nodes;
  //Calculate the distribution of nodes across layers
    var netsize = {};
    nodes.forEach(function (d) {
        if (d.layer in netsize) {
            //Increment node count for the layer
            netsize[d.layer] += 1;
        } else {
            //Initialize node count for the layer
            netsize[d.layer] = 1;
        }
        //Assign a local index to each node within its layer
        d["lidx"] = netsize[d.layer];
    });
    //Determine the size of the largest layer
    var largestLayerSize = Math.max.apply(
        null,
        Object.keys(netsize).map(function (i) {
            return netsize[i];
        })
    );
    //Calculate distances between nodes and layers
    var xdist = width / Object.keys(netsize).length, //Distance between layers
        ydist = (height - 15) / largestLayerSize; //Vertical distance between nodes
    //Position each node
    nodes.map(function (d) {
        //Calculate x position
        d["x"] = (d.layer - 0.5) * xdist;
        //Calculate y position
        d["y"] =
            (d.lidx - 0.5 + (largestLayerSize - netsize[d.layer]) / 2) * ydist + 10;
    });
    //Create links between nodes
    var links = [];
    nodes
        .map(function (d, i) {
            for (var n in nodes) {
                if (d.layer + 1 == nodes[n].layer) {
                    //Create a link if nodes are adjacent layers
                    links.push({source: parseInt(i), target: parseInt(n), value: 1});
                }
            }
        })
        .filter(function (d) {
            return typeof d !== "undefined";
        });
    //Draw links as SVG lines
    var link = svg
        .selectAll(".link")
        .data(links)
        .enter()
        .append("line")
        .attr("class", "link")
        .attr("x1", function (d) {
            return nodes[d.source].x; //Set starting x position
        })
        .attr("y1", function (d) {
            return nodes[d.source].y; //Set starting y position
        })
        .attr("x2", function (d) {
            return nodes[d.target].x; //Set ending x position
        })
        .attr("y2", function (d) {
            return nodes[d.target].y; //Set ending y position
        })
        .style("stroke-width", function (d) {
            //Set line thickness
            return Math.sqrt(d.value);
        });

    // Add text to each link
    var linkText = svg
        .selectAll(".link-text")
        .data(links)
        .enter()
        .append("text")
        .attr("class", "link-text")
        .attr("x", function (d) {
            return (nodes[d.source].x + nodes[d.target].x) / 2;
        })
        .attr("y", function (d) {
            return (nodes[d.source].y + nodes[d.target].y) / 2;
        })

        .attr("dx", function (d, i) {
            return 10 + (i % 3) * 10;
        })
        .attr("dy", function (d, i) {
            return (i % 3 === 0) ? 0 : 10;
        })
        .attr("font-size", "10px")
        .attr("text-anchor", "middle")
        .text(function (d, i) {
            return i + 1;
        });

    //Draw nodes as SVG circles
    var node = svg
        .selectAll(".node")
        .data(nodes)
        .enter()
        .append("g")
        .attr("transform", function (d) {
           // Position the node group
            return "translate(" + d.x + "," + d.y + ")";
        });

    var circle = node
        .append("circle")
        .attr("class", "node")
        .attr("r", nodeSize) //Set radius of the circle
        .style("fill", function (d) {
            return color(d.layer); //Color the node based on its layer
        });
    //Add labels to each node
    node
        .append("text")
        .attr("dx", "-.35em")
        .attr("dy", ".35em")
        .attr("font-size", ".6em")
        .text(function (d) {
            return d.label; //Display the node label
        });
}

//Function to redraw the graph based on current configuration
function draw() {
    //Get the container for the SVG
    var svgContainer = document.getElementById("neuralNet");
    // Remove existing SVG elements if any
    svgContainer.innerHTML = "";
    var svg = d3
        .select("#neuralNet")
        .append("svg")
        .attr("width", width) //Set the width of the SVG
        .attr("height", height); //Set the height of the SVG
     //Build the new graph based on current settings
    networkGraph = buildNodeGraph();
    //Draw the graph on the SVG
    drawGraph(networkGraph, svg);
}
//Function to update the neural network settings based on user input and redraw the graph
function updateAndDraw() {
    app.inputLayerHeight = parseInt(
        document.getElementById("inputLayerHeight").value
    ); //Update the input layer height from user input
    app.hiddenLayersCount = parseInt(
        document.getElementById("hiddenLayerCount").value
    ); //Update the hidden layer count from user input
    app.outputLayerHeight = parseInt(
        document.getElementById("outputLayerHeight").value
    ); //Update the output layer height from user input

    draw();
}
