## Design issue I refactored##

In the previous version of our code, the GridModel was being constructed with too much knowledge of other classes.  We were passing the GridModel entire classes of GridSettings and CellSettings, but the grid model only needed some of that information.  Additionally, the GridModel HASA Rule, but was also using some of the information from GridModel to construct its own rule upon construction of the Grid. As if these weren't problem enough, the GridModel was constructing its own grid.  Thus, if we wanted to change the way the grid was constructed, we'd have to go into the GridModel constructor and change things.

## Why the new version is better ##

Thus, I created a GridFactory class to deal with the construction of the GridModel.  Having the factory deal with the construction has several advantages.  The first is that the GridModel no longer needs to have any knowledge of the GridSettings or CellSettings.  It only needs to know about the classes directly related to it. Another advantage of having the factory deal with the construction is that we can easily extend the functionality of the factory to deal with different types of grid construction.  Additionally, having the GridFactory allows us to write lines of code like this:

    GridModel gridModel = GridFactory.createGridModel(...);

Thus, based on the inputs of the createGridModel function, we can dynamically create different types of grid models (i.e. subclasses of GridModel that all have the same API) so that we can have different GridModels, but work with them in the same way.

## The link to the commit: ##

https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team24/commit/82befc7effb227066b041af8cf20553ab8df68b7