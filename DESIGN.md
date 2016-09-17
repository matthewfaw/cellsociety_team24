
## Introduction
***
In this project, our team is attempting to create a flexible, general-purpose cellular automata simulator that can run a simulation defined in an XML document. It should support varied rule sets and graph formats (square, triangle, hexagons). The UI elements for simulation controls will be closed and the rule definitions and grid staring layout will be open.
The architecture will consist of view components to manage the appearance of the screen.  Controllers will construct the scene and manage dataflow. We will possibly use models to keep track of Statisti cs
## Overview
***
![Class Diagram a](/images/ClassDiagram.png?raw=true “Class Diagram”)  
Class:


* GameController: In charge of initialize (using XML Parser) and step functions for the CA game. It owns the UI buttons and controls what happens when they are pressed.
* XMLParser: In charge of parsing the input XML document and building the game’s starting conditions and display.
* GridController: Contains Cells. Is responsible for picking which cells are displayed on the screen and updating cells as the simulation runs. Controls information on neighborhoods.
* Cell: Contains information about it’s cell’s current state.
* Main: Launches the program. Sets up the timeline.
##User Interface
***
![UI Mockup a](/images/UISketch.png?raw=true “UI Mockup”)  


* The user interface will contain the 2D grid as well as several options to Start,Pause,Step forward, and Reset the grid. The user will first select the simulation they want from the file selector and press the “Set” button. The grid will then refresh with correct specifications depending on the file selected. The user can also change various features to how the simulation is run based on the sliding scales. To determine the ratios of each of the different states, there will be a special graphic tool to have the percentages inputted. These percentages will checked so they add up to 100. The tool will allow us the flexibility to add more states if need be. If necessary, the interface will also be able to display any statistics associated with the game as well.
* The erroneous situations that will be reported are if a file that could not be reached is selected. If so, an error message will be displayed that states “Could not load file.” There will also be features to ensure the percentages of different states selected in “State Ratio” add to 100%.
## Design Details
***
* The GameController class will serve as the primary entry point of the CA simulator.  It will define the init() and step() methods that will be called by Main.java.  GameController will have an XMLParser to update the game appearance described in a given XML file, and a GridController to control the simulation. GameController is responsible for handling button presses, and passing data along to the grid controller to handle simulation updates.  
* The XMLParser is responsible for creating a scene node based on an XML description.  It will know about all of the View components, and will be able to create those components based on the file specs.
* Grid controller handles how the simulation updates at a given time step. It will be responsible for accessing neighboring cells, and manipulating the cells.
* The Cell class will encapsulate all of the data necessary for a grid cell.
* The cell class can be extended to handle specially formatted cells. The GridController could be extended to handle controlling different types of grids.  XML Parser could be extended to handle specially formatted XML docs, if necessary, or for parsing XML files for different purposes.

Use Cases:

* Apply rules to middle cell:* GridController calls setNextState(cell), then cell.nextState()
* Apply rules to edge cell:* GridController calls setNextState(cell), then cell.nextState()
* Move to next generation:* GridController calls update(), which calls setNextState(cell) for each cell in the grid, then calls cell.nextState() for each cell in the grid.
* Set a simulation parameter:* When XMLParser builds the game rules and initial conditions, it will read the parameter, which will get passed to GridController when GridController is constructed.

## Design Considerations
***
We discussed how to handle an arbitrary number of neighbors for each cell before realizing that the assignment assumed a regular grid, any of which we can represent with a 2D array. Also, we need to decide how/where any relevant statistics are displayed on the screen and if we want a class that can be written to/read from different classes to transfer these statistics or if we want to GameController to query each step to find these statistics. We think it would probably be simpler to query, removing the need for another class.  
We tried to design the program architecture in a way that makes the data flow make sense, so we don’t have any weird dependencies. One assumption that we made was that our number of buttons would be sufficient for any simulation (that there would only be one parameter per game).  If it is not, we will have to add additional buttons to out UI or dynamically allocate buttons.

##Team Responsibilities
***
Weston - GridController and Cells
Guhan - UI
Matthew - XMLParser
