# Hello, welcome to HACK

#### Last Updated: *February 22, 2022*

### SUMMARY:
HACK is a (mostly) text-based game where random enemies spawn in a dungeon and the user has
multiple actions they can use. The game ends when user health reaches 0!

### PROJECT TITLE:
It is called HACK.3 or HACK version 3.

### INSPIRATION:
HACK is inspired by old arcade games, and some pixel RPG games. I didn't realize it until recently, 
but HACK is kind of like the super over-simplified version of "Slay the Spire," which was also created in Java! 
Of course, HACK doesn't have a card-based system like "Slay the Spire" does, but the stat-saving and playing until 
you die is similar in concept! 

If you want to play "Slay the Spire," here is a link:

https://store.steampowered.com/app/646570/Slay_the_Spire/

### INSTALLATION:
To play HACK, you need to load the HACK.3 file it into an IDE such as Intellij or Eclipse.

If you are using Intellij, refer to this link:
https://www.jetbrains.com/help/idea/getting-started.html

If you are using Eclipse, refer to this link:
https://www.eclipse.org/getting_started/

To use the database within HACK properly, you will have to download this sqlite driver:
https://github.com/xerial/sqlite-jdbc/releases

Include this in your external libraries on Intellij by going to:

`File > File > Project Structure > Modules > Dependencies`

Please see this link for visual aide:
https://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project

OR if you are using Eclipse:
https://stackoverflow.com/questions/3280353/how-to-import-a-jar-in-eclipse

**_PLEASE SEE FEATURES!!!_**

### MOTIVATION:
I created this project to learn java. I am also interested in creating more video 
games in the future. 

### BUILD STATUS:
HACK now runs with JavaFX! To run this interface, you must launch the game through the
Launcher in the Game class:

`HACK.3 > src > main > java > com > game > hack3 > Game`

Hack.3 is unfinished right now (meaning, it runs and everything works as it is right now,
but more things will be implemented in the coming weeks). Stay tuned!

### TESTS:
If you want to run JUnit tests on current methods, they are found:
`Game_Test > src > test > java`

### FEATURES:
To get the file output at the end of your game, it should be stored in:

`HACK.3 > HACK_Stats.txt`

The database that is used to call upon specific information is called HACKDatabase.sqlite. It is found:

`HACK.3 > HACKDatabase.sqlite`

The graphics that you see used within the game were actually created by me! I made them in a program called
PyxelEdit. If you would like to check out that program for yourself, please see this link:

https://pyxeledit.com/

I am still learning how to use PyxelEdit, and thought that putting teeny tiny little graphics into
the game would give it something that other text-based games don't have. I am working up to more graphically intense 
games, and I am proud that I could create these tiny cute graphics for HACK!

JavaFx and Junit tests were used to make HACK.3. Unfortunately, my .jar files are not cooperating with me, 
so I do not believe that this application will run outside an IDE without JavaFx installed 
(but if you want, you can certainly try!). The .jar file and artifacts have been created, and that file path is:

`HACK.3 > out > artifacts > HACK3_jar`

Note: the application .jar file is called HACK5.jar for some reason, and I cannot figure out why it is exporting 
like that. But it is actually HACK.3 that would run from that file. The sqlite .jar is also in this file!

To install JavaFx, please see the link below:
https://docs.oracle.com/javafx/2/installation/jfxpub-installation.htm

If you are using Intellij, you can install JavaFx libraries this way:
https://www.jetbrains.com/help/idea/javafx.html

If you are using Eclipse, please refer to this link:
https://download.java.net/general/javafx/eclipse/tutorial.html

_Enjoy!_ 