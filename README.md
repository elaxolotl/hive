![bee icon](/static/bee-icon.png)

# Hive: A game of life with aging mechanics

This project extends Conway's Game of Life with a unique aging mechanism that introduces evolutionary dynamics to the classic cellular automaton.

## Overview

The standard Game of Life follows simple rules where cells live or die based on their neighbors that is:

- Any live cell with fewer than two live neighbours dies, as if by underpopulation.
- Any live cell with two or three live neighbours lives on to the next generation.
- Any live cell with more than three live neighbours dies, as if by overpopulation.
- Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

This implementation also adds:

- Each cell has a random lifespan assigned at initialization
- Cells die when they exceed their lifespan, regardless of neighbor count
- When a dead cell becomes alive, it inherits the average lifespan of its living neighbors

This creates an evolutionary system where lifespan characteristics change over generations

## Features

- Classic Game of Life rules with aging mechanics
- Visualization of cell age and lifespan
- Statistics tracking for average lifespan over generations
- Configurable parameters for initial conditions

## Demo
![demo video](/static/demo-gif.gif)

## Interesting Findings

Contrary to initial expectations, average lifespans tend to decrease over generations rather than increase. This counterintuitive result highlights interesting emergent properties:

- Shorter-lived cells may create more dynamic patterns that reproduce more effectively
- The averaging inheritance mechanism tends to favor shorter lifespans
- Spatial dynamics and timing of cell death significantly impact evolution

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 23 or higher

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/hive.git
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

## Contributing

Contributions are welcome! This project offers many possibilities for experimentation:
- Alternative lifespan inheritance mechanisms
- Additional evolutionary attributes beyond lifespan
- Advanced visualization and analysis tools

## License

This project is licensed under the MIT license

## Acknowledgments

- John Conway for the original Game of Life