
**Design Issue I refactored**

Within my design there were plenty of things to be fixed. In my refactoring I eliminated all the protected methods,made a few more methods and really tried to take advantage of the inheritance hierarchy by having all the methods pass in only what must be dynamically provided.
 
**Why the new version is better**
 For one, I feel the code is significantly more readable. I feel it is easier track how the code functions and what it relies on.  In addition, I feel the code after refactoring is much more indicative of how the Grid View truly processes new information and makes it more difficult to be changed from the outside. In addition, I feel if there is the need for future edits, the new code will be much more flexible towards it.

**Link to the commit**
https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team24/commit/a933ee9027330ebddce5d731c3254510f3a80e16

NOTE: The commit when originally made it did not seem to register as my shell crashed, The commit is under a poorly chosen name and I apologize for that. 