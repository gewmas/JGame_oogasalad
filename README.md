oogasalad
=========

**Team Members:**
Fabio Berger, Jiaran Hao, Rebecca Lai, Yuhua Mai, Lalita Maraj, Harris Osserman, Wenxin Shi, Susan Zhang, Alex Zhu

**Start Date:** Monday, October 28, 2013

**End Date:** Monday December 9th, 2013

**Hours worked:** Too many, 30h+/person 

**Project Repo:** https://github.com/duke-compsci308-fall2013/oogasalad_FooBar

**Startup Instructions:**

Run MainPanel.java under the gameAuthoring package. MainPanel.java gives the user two options - one to design a game, another to run a game. 

Two game templates have been provided in the root of the branch: hospital.json and towerdefense.json. 

Images and sounds that are packaged with the game are stored in src/resources/img. We are using a .tbl file (mygame.tbl) in this folder to load some default game graphics that are generic to every game.

**Game Authoring Instructions**

The different tabs in the Game Authoring environment organize the different components of game design in a self-explanatory manner. Hovering the cursor over a field in the tab brings up a explanatory popup in case further explanation is required. One can also choose to bring up DuvallClippy, our version of an “Office Assistant” to explain the purpose of each tab by going to Show -> Clippy. Once all fields in all tabs have been complete, save the game out by clicking File -> Save.

In general, images and audio files can be loaded to the User Library for easy access throughout the design process. The User Library is the panel to the right hand side in the Game Authoring environment. Once resources are loaded into the library, one can set images for certain tab fields (e.g. an image for a new enemy type) by first clicking on an image/audio in the library and then clicking on the image label that represents the tab field. The same rule applies for audio files.  

**Game Instructions**

To purchase objects from the store, simply click them, and the cursor will change to that object’s image. Then click on any valid position on the field to place that object. Towers can be validly placed on tiles that are not on the path. Temporary Barriers can be validly placed on tiles that are on the path. To cancel a purchase, click the object again and the cursor will revert back to normal.

To check the status of an object on the field, click on the object, and it’s stats will show in the information panel beneath the store. You can also upgrade or sell that object from this information panel.

At any point in the game, you can press escape, or go to file/end game to transition to the game over state. Selecting file/new game brings you back to the json selection menu. You can also press p to pause the game.

**Cheats**

- win_game (auto-win the game)
- lose_game (auto_lost the game)
- clear_all (clears all foreign pawns from the map)
- add_[gold] AMOUNT (add a specific amount to gold point equivalent)
- add_[lives] AMOUNT (add a specific amount of live point equivalent)

Note: For the last two, the two point type names are defined in the game editor and these names assigned dynamically change the cheat calls for that specific game.
 
**Known Bugs**

The timers for each wave of enemies are done outside of jgame, so changing the frame rate will not change the rate at which enemies spawn. Future work would be to integrate the timers with the frame rate settings.

**Extra Features**

- To set the frame rate of the game, press f at any time in the game to access the frame rate bar. Click anywhere on the bar to set the frame rate (right is higher, center is 30fps).

- To activate the cheat terminal, press “c” on your key board

- To access extra help in the game authoring phase, click on Show -> Clippy to bring up our version of an Office Assistant, DuvallClippy. 

**Our Roles**

**Game Authoring:**

- Rebecca Lai - Worked with Susan to create the GameAuthoringGUI. Worked individually on user library and the "click-and-drag" mechanism of images and audio. 
- Susan Zhang - Worked with Rebecca to create the GameAuthoringGUI. Worked individually on writing game information to JSON. 

**Engine Front End:**

- Lalita Maraj
- Alex Zhu - Built jgame interface, tower status, upgrade and sell, integration with backend, frame rate slider

**Back End:**

- Fabio Berger - built enemy core functionality, interfacing with tile class , enemy factory, cheat code handler and contributed to JSON parser, game end/begin mechanics and game graphics
- Jiaran Hao - Build the Waves to handle generating enemies in game. Detector to help with other team members to make detection. Resources to register images,audio,animation from JSON. Skill lets Enemy to have different behaviors, eg casting magic. Effects and WordEffects to improve the graphics of game. Also, rewrites JGame to support Blending.    
- Yuhua Mai - Built Tower(DefaultTower, MultipleShootingTower, MagicTower, BoostTower), Bullet class. Implement Towerwarehouse, TowerFactory to create Tower. Update PurchaseInfo. Figure out pipeline of Model.
- Harris Osserman - Built JSON parser, tile initialization/path creation (with integration with other model objects and front end), temporary barriers (with refactoring of TowerInfo to PurchaseInfo class), and permanent barriers.  
- Wenxin Shi -Build the magic for both of enemy and tower. Cooperate with creating the skill and the animation for the game. Refactor the code to let the enemy and tower place in the right position. Creates fancy pictures using PS.
