# Simulated Annealing Demo of Travelling Salesman Problem

## Sample
![](https://github.com/danielbatchford/TravellingSalesmanVisualisation/blob/master/sample.gif)
## About
This is a demo of how simulated annealing combined with 2-opt swaps can be used to efficiently calculate 
short paths in a map of nodes and edges. Updates are made every frame and displayed using Processing for Java.

#### Logic
At each step, 2 indexes are chosen - the first is iterated from 0 and the second is a random index in the node
list where secondIndex > firstIndex. A sublist of nodes is chosen [firstIndex, secondIndex] and this sublist is reversed.
If the cost of this new permutation is lower than the current cost, a swap is made. Else, with a probability
 P = e^((newCost-currentCost)/temperature), the swap is made. If a swap is made, the temperature is updated using temperature*=alpha.
 Otherwise, the first index is incremented and the second index is again chosen at random, until a swap is made.
 
Learning rates can be changed in `src/danielbatchford/map/LearningParameters`.
#### Cost Function
Currently, the cost is calculated as the average distance (in pixels) of all connected edges.

## How to Run
Run `java -jar TSalesman.jar` from the main directory inside the console. If program parameters have been changed, 
instead run `java src/danielbatchford/gui.Renderer.java` or rebuild the jar file. 

## Controls
- `P` - Pause.
- `R` - Reset.

## Credits
- [Processing for Java](https://processing.org/) for GUI rendering. 
- [GiCentre Utils](https://www.gicentre.net/software#/utils/) for graph plotting inside of Processing.