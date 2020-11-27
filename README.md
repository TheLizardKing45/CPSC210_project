# Apocalypse

## Survival shooter game


The application will be a game that has a playable character who can move around the screen.
The player will be able to shoot bullets  at
enemy characters that appear on the screen and follow the player. They will try to touch 
the player. **If the enemies touch the player the game ends.** There will be multiple waves with more
enemies appearing each wave. 

This application is meant for people who are interested in video games such as *Call of Duty*
because this project will be similar to shooter games that involve surviving waves of enemies.
 People that also like *Space Invaders* may enjoy this project as there will be similarities in 
their gameplay. I wanted to make this project because I am a huge fan of video games, and I wanted
to make my own for once. I've always admired people who make amazing games on their own or with a
very small team. I hope to get experience in developing video games for future personal projects.

## User Stories
### Phase 1  
* As a user, I want to be able to move my character
* As a user, I want to be able to shoot zombies
* As a user, I want to be able to see what round I am currently on
* As a user, I want to track and see my current points and how many zombies I have defeated as I play
* As a user, when the game ends, I want to add my game results to a scoreboard that displays
 my name and the points, number of kills, and rounds I survived
* As a user, I want to be able to delete records from the existing scoreboard
 
 ### Phase 2
 * As a user, I want the option to save the current scoreboard to a file
 * As a user, I want the ability to load a saved scoreboard from a file
 
 ### Phase 4: Task 2
 Java construct: I chose to include a type hierarchy with the Zombie, Player, and Bullet classes. Zombie and Player
 implement LivingEntity which extends Entity. Bullet implements Entity.
 
 ### Phase 4: Task 3
 * I could have another class that handles retrieving the data which then passes it in the ScoreboardModel
   constructor as a parameter for better cohesion. Then, ScoreboardModel can focus on building the table based on the 
   data passed in.
 * Instead of updating the scoreboard by turning the 2D array of data into an array list and back into a 2D array,
   I could find a way for the JTable to show the data of the Scoreboard directly
 * I could have a new class for the button action listeners that handle saving and loading from a file 
   instead of the ScoreboardUI also handling that responsibility. One button listener class would have an 
   association with JsonReader and the other would have an association with JsonWriter
 * I think it was a good idea to have a type hierarchy for the objects directly related to the gameplay 
   (Player, Zombie, and Bullet) due to certain properties that they share
 * In the future if I wanted to add more gameplay-related classes, for example walls or other obstacles, 
   I can use the Entity interface to mark some behavior that must be implemented
  


