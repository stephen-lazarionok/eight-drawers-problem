# Eight drawers problem

### Description
You have a chest of 8 drawers. With probability 1/2, you put a letter in one of the drawers.
With probability 1/2, you don't put a letter in any drawer.
I open the first 7 drawers, all are empty. What is the probability there is a letter in the 8th drawer?

### Solutions

##### A solution based on Bayes' theorem
[View solution](maths_explanation.jpg)
##### A solution based on method Monte Carlo
```console
mvn install exec:java -Dexec.mainClass=MonteCarloSolution
```
