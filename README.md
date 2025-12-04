# Toggle Game

A Java-based puzzle game featuring a 3x3 grid of toggleable squares. The objective is to transform an all-white board into a target configuration by clicking squares that flip the colors of the clicked square and its adjacent neighbors.

![Toggle Game Demo](figures/togglegame.gif)

## 🎮 Game Overview

Toggle Game is an interactive puzzle where players manipulate a 3x3 grid of black and white squares. Each square click toggles the color of the clicked square and its four orthogonal neighbors (north, south, east, west), creating a cascading color-flip effect.

### Game Mechanics

The game board consists of 9 squares numbered as follows:

```
0 1 2
3 4 5
6 7 8
```

- **White squares** are represented by `1`
- **Black squares** are represented by `0`
- Every game starts with all white squares: `111111111`

### Example

Clicking the middle square (button 4) transforms the board:

<p float="left">
  <img src="figures/board1.png" width="200" alt="Initial board - all white" />
  <img src="figures/board2.png" width="200" alt="After clicking middle square" />
</p>

The middle square and its four neighbors flip from white to black.

## 🎯 Objective

Players are presented with a random target board configuration. The goal is to reach that configuration in the minimum number of moves.

![Initial Game Screen](figures/initial_game_screen.png)

## ✨ Features

### Core Functionality

- **Interactive Game Board**: Click any square to toggle it and its neighbors
- **Minimum Moves Calculator**: Automatically computes the optimal number of moves needed
- **Path Solver**: Generates the exact sequence of moves to reach the target
- **Hint System**: Reveals the next optimal move with visual highlighting

![Hint Feature](figures/hint_clicked.png)

### Technical Highlights

- **BFS Algorithm**: Uses breadth-first search to find optimal solution paths
- **Binary Operations**: Efficient state management using XOR operations
- **State Tracking**: Maintains parent-child relationships for path reconstruction
- **Complete State Space**: Handles all 512 (2^9) possible board configurations

## 🛠️ Technology Stack

- **Language**: Java
- **Build Tool**: Gradle
- **Frontend**: Java Swing
- **Backend**: Custom game engine with algorithm optimization

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Gradle (included via wrapper)

### Building the Game

```bash
# Using Gradle wrapper (recommended)
./gradlew build

# Run the game
./gradlew run
```

### Running Tests

```bash
# Run all tests
./gradlew test

# View test results
open build/reports/tests/test/index.html
```

## 📁 Project Structure

```
src/
├── main/java/ToggleGame/
│   ├── Driver.java                    # Application entry point
│   ├── backend/
│   │   ├── GameHelper.java            # Utility methods for state conversion
│   │   └── ToggleGameEngine.java      # Core game logic and algorithms
│   └── frontend/
│       ├── GameGrid.java              # UI grid component
│       ├── ToggleGameDisplay.java     # Main display controller
│       └── ToggleGameInteraction.java # Interface definition
└── test/java/backend/
    └── ToggleGameEngineTest.java      # Comprehensive test suite
```

## 🧩 Algorithm Details

### State Representation

Each board state is represented as:
- **String format**: "111111111" (9 characters of '0' or '1')
- **Binary format**: Integer representation for efficient XOR operations

### Move Masks

Each button has a predefined mask that determines which squares flip:

```
Button 0: 110100000  (flips positions 0, 1, 3)
Button 1: 111010000  (flips positions 0, 1, 2, 4)
...
```

### Solving Algorithm

The engine uses **Breadth-First Search (BFS)** to guarantee optimal solutions:

1. Start from the current board state
2. Explore all reachable states (up to 9 per move)
3. Track parent-child relationships to reconstruct the path
4. Return the shortest sequence when target is reached

**Time Complexity**: O(512 × 9) = O(1) since the state space is constant  
**Space Complexity**: O(512) for visited states tracking


## 📊 Test Coverage

The project includes comprehensive test suites:

- **FirstDeliverableTests**: Basic initialization and identity tests
- **BoardStatesTests**: Button click handling and state updates
- **MinimumRequiredMovesTests**: Move counting accuracy
- **PathToTargetTests**: Solution path verification

All tests pass successfully ✅

## 🤝 Contributing

This is an academic project. While contributions are not actively sought, feedback and suggestions are welcome.

## 📝 License

This project is licensed under the terms specified in the [LICENSE](LICENSE) file.

---

**Note**: This implementation demonstrates efficient algorithms for solving combinatorial puzzles and serves as an educational example of search algorithms in action.
