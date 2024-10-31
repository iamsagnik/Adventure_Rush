# Adventure Rush

Adventure Rush is a 2D adventure game where players explore a dynamic world, avoiding obstacles, battling monsters, and progressing through challenging levels. This project is developed in Java using custom graphics and a camera system to handle smooth player movement and rendering.

## Table of Contents

- [Features](#features)
- [Setup](#setup)
- [How to Play](#how-to-play)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Future Improvements](#future-improvements)

## Features

- **Smooth Camera Movement**: The game includes a camera system that follows the player smoothly, allowing the character to move seamlessly across the map.
- **Tile-Based Rendering**: The world is made up of tiles, including grass, sand, trees, water, and more, each loaded and rendered based on player position.
- **Character Animations**: The player has animated movements for walking in all directions.
- **Collision Detection**: The player and other game elements have collision handling to create a realistic game environment.
- **Random Monster Spawning**: Monsters appear at random positions on specific areas of the map, avoiding obstacles and specific tiles like water or trees.

## Setup

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/adventure-rush.git
    ```

2. **Navigate to Project Directory**:
    ```bash
    cd adventure-rush
    ```

3. **Compile and Run**:
   Open the project in your preferred IDE (e.g., IntelliJ, Eclipse) or compile using the command line.

4. **Run the Game**:
   Once compiled, execute the main file to start the game:
   ```bash
   java -cp bin src.main.Main


## How to Play

- **Movement**: Use the arrow keys (up, down, left, right) to move the player character.
- **Objective**: Explore the map, avoid obstacles, and interact with elements in the game world.
- **Goal**: Survive and progress through the levels.


## Project Structure

## Project Structure

- `Adventure_Rush/`
  - `res/` - Resources (images, sounds)
    - `characters/` - Character sprites
    - `maps/` - Map data files
    - `objects/` - Object assets
    - `sound/` - Sound effects
    - `tiles/` - Tile images (grass, water, etc.)
  - `src/` - Source code
    - `main/` - Main game logic
    - `tile/` - Tile management
    - `player/` - Player character handling


## Technologies Used

- **Java**: Primary language for game logic.
- **Swing/Graphics2D**:  Used for rendering the game visuals.
- **Custom Assets**: Tile-based sprites and character animations.
- **File I/O**: Loads game assets from resources.

## Future Improvements

- **Level System**: Introduce multiple levels or areas to explore.
- **Score and Progression**: Track player progress and display scores.

