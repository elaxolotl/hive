<div align=center>
   
   ![bee icon](/static/bee-icon.png)

   # Hive: A game of life with aging mechanics
   This project extends Conway's Game of Life with a unique aging mechanism that introduces evolutionary dynamics to the classic cellular automaton.

   
</div>

## Overview

The standard Game of Life follows simple rules where cells are placed on a grid and given one of two states: alive or dead that is decided based on their neighbors:

- Any live cell with fewer than two live neighbours dies, as if by underpopulation.
- Any live cell with two or three live neighbours lives on to the next generation.
- Any live cell with more than three live neighbours dies, as if by overpopulation.
- Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

This implementation also adds:

- Each initial cell has a random lifespan assigned at initialization
- Cells die when they exceed their lifespan, regardless of neighbor count
- When a dead cell becomes alive, it inherits the average lifespan of its living neighbors

This creates an evolutionary system where lifespan characteristics change over generations

## Demo
![demo video](/static/demo-gif.gif)

## Features

- Classic Game of Life rules with aging mechanics
- Visualization of cell age and lifespan
- Statistics tracking for average lifespan over generations
- Configurable parameters for initial conditions

## Interesting Findings

Contrary to initial expectations, the average lifespan of cells depends heavily on the number of cells in the grid. When there are fewer cells, the environment favors longer lifespans, as these cells can quickly stabilize and form [still lifes](https://en.wikipedia.org/wiki/Still_life_(cellular_automaton)), [oscillators](https://en.wikipedia.org/wiki/Oscillator_(cellular_automaton)), or [spaceships](https://conwaylife.com/wiki/Spaceship). However, in dense environments, the average lifespan decreases with each generation due to high variation and dynamic interactions, preventing stabilization. Over time, as the population declines, longer lifespans can once again become advantageous.

<div style="display: flex;">
  <div style="flex: 1; padding: 10px; border: 1px solid black;">

      *average lifespan evolution in high density environments* 
      
![graph1](/static/graph-high-density.png)

      
  </div>
  <div style="flex: 1; padding: 10px; border: 1px solid black;">
     
    *average lifespan evolution in low density environments* 
     
![graph1](/static/graph-low-density.png)

  </div>
   
</div>


# Project Structure

The project is organized in the classes shown in the entity relationship diagram below:
![er-diagram-hive](/static/diagram-hive.png)

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 23 or higher

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/elaxolotl/hive.git
   ```
2. Navigate to the project directory:
   ```sh
   cd hive
   ```
3. Compile the project:
   ```sh
   javac -d out src/**/*.java
   ```
4. Run the project:
   ```sh
   java -cp out BoardGUI
   ```

### Usage
1. Adjust the simulation speed and lifespan using the sliders.
2. Click "Start" to begin the simulation.
3. Click "Reset" to reset the board.
4. Click "Chart" to view the lifespan statistics over generations.
5. Click "Clear Board" to clear the board
   <br/>
*You can click anywhere on the grid to kill or create a new cell*

## Contributing

Contributions are welcome! This project offers many possibilities for experimentation:
- Alternative lifespan inheritance mechanisms
- Additional evolutionary attributes beyond lifespan
- Advanced visualization and analysis tools

## License

This project is licensed under the MIT license

## Acknowledgments

- John Conway for the original Game of Life