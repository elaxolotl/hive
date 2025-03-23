# Hive: A game of life with aging mechanics

This project extends Conway's Game of Life with a unique aging mechanism that introduces evolutionary dynamics to the classic cellular automaton.

## Overview

The standard Game of Life follows simple rules where cells live or die based on their neighbors. This implementation adds:

- Each cell has a random lifespan assigned at initialization
- Cells die when they exceed their lifespan, regardless of neighbor count
- When a dead cell becomes alive, it inherits the average lifespan of its living neighbors
- This creates an evolutionary system where lifespan characteristics change over generations

## Project Structure

The project consists of the following components as shown in the entity relationship diagram:


## Features

- Classic Game of Life rules with aging mechanics
- Visualization of cell age and lifespan
- Statistics tracking for average lifespan over generations
- Configurable parameters for initial conditions

## Interesting Findings

Contrary to initial expectations, average lifespans tend to decrease over generations rather than increase. This counterintuitive result highlights interesting emergent properties:

- Shorter-lived cells may create more dynamic patterns that reproduce more effectively
- The averaging inheritance mechanism tends to favor shorter lifespans
- Spatial dynamics and timing of cell death significantly impact evolution

## Getting Started

### Prerequisites
- [List required dependencies/libraries]

### Installation
```
[Installation instructions]
```

### Usage
```
[Example commands or code to run the simulation]
```

## Configuration Options

- `GRID_SIZE`: Dimensions of the simulation grid
- `INITIAL_LIFESPAN_RANGE`: Range for randomly assigned initial lifespans
- `SIMULATION_SPEED`: Controls how fast generations progress
- `VISUALIZATION_MODE`: Options for displaying cell age and status

## Contributing

Contributions are welcome! This project offers many possibilities for experimentation:
- Alternative lifespan inheritance mechanisms
- Additional evolutionary attributes beyond lifespan
- Advanced visualization and analysis tools

## License

[Your chosen license]

## Acknowledgments

- John Conway for the original Game of Life
- [Any other acknowledgments]