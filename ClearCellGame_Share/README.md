# ClearCellGame – Java Model Logic

## 🎮 Overview
This repository contains core model logic for the ClearCellGame, a grid-based puzzle game implemented in Java. The game simulates clearing matching cells from a board, with new rows of random cells added over time. Players aim to maximize their score before the board fills up.

This implementation was part of a university project focused on object-oriented design and algorithm development for grid-based games. The classes here represent the *model* layer of an MVC architecture, handling all board state, rules, and game logic.

---

## 🛠️ Features
- Board representation with customizable dimensions
- Cell types with color and name properties
- Random row generation with non-empty cells
- Game-over detection
- Score tracking
- Recursive clearing of adjacent matching cells
- Row collapsing after clears

---

Integrate these classes with a UI or driver for gameplay interaction.

---

## 📂 Folder Structure
- **src/** – Java source files
- `BoardCell.java` – Enum defining cell types and colors
- `GameModel.java` – Abstract class defining the board and core methods
- `ClearCellGameModel.java` – Concrete implementation of game logic

---

## 💡 My Contribution
For this project, I was responsible for designing and implementing the **model layer**:
- `BoardCell.java`: Enum defining cell types with colors and names, with random generation support.
- `GameModel.java`: Abstract base class encapsulating board structure, cell access, and shared utilities.
- `ClearCellGameModel.java`: Concrete subclass implementing game rules including score tracking, game-over checks, random row generation, clearing logic, and row collapsing.

This work demonstrates:
- Strong understanding of object-oriented principles (abstraction, encapsulation, inheritance)
- Designing clear, modular code for reuse and extension
- Implementing algorithms for grid-based state updates and clearing logic
- Careful handling of edge cases and input validation

