# CellSociety Code Review Refactoring

### Weston Carvalho

|Commit|Problem|Changes|
|------|-------|-------|
|[Change grid to know neighbors and rules to ask about it instead of doing it itself.](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team24/commit/08e6c18ab9f1181ca9901643bca9deeea2a02201)|Rules was keeping track of neighbors, which felt like a GridModel responsibility. Also, Rules had to know GridModel was storing in a 2D Cell array.|Made GridModel Iterable, so classes looking to iterate over the cells in a GridModel don't need to know about GridModel's implementation.|
| | | Give the rules classes a GridModel to update, instead of a 2D Cell array.|
| | | Make neighbors the GridModel's responsibility instead of the Rule's.|
|[Redo magic ID numbers in rules classes.](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team24/commit/a49d6354051dc74354ebcd92c68b6c0958d6ff0a)|Previously, each Rule subclass would compare a cell's state integer to a hardcoded integer to determine the cell's state|After this commit, it compares the cell's integer to an integer specified a static, final integer. If we later wish to make it dependent on the contents of the XML file, it will be easy.|
|[Remove magic strings from RuleFish. Deprecate the previously used RuleFish constructor.](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team24/commit/add3bcb773872a0f6eb9f15d2ca4a3654d6fa12b)|Previously, was using magic strings to retrieve different cell attributes from a Map.|Replaces the magic strings with final strings that can be set on construction of the RuleFish object, or are initialized to default values if nothing else is provided.|
| | | Marks the previously used constructor as deprecated, so teammates using it will know to fix it, even if I forget to say anything about it.|
