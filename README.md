# Learn from Wikipedia

* Updates The Library event to grab a random article from Wikipedia and present the introduction.
  * Note: this could end up displaying very little content for some articles
  * Note: this appears to have a minor, but noticeable effect on The Library's load time
 
## Ideas

 * Open random article from Wikipedia after Lesson Learned
 * Add relic for Cursed Tome Event that does _something_ related to random knowledge gain.

## How to build from source

1. Create an environment variable named `STEAMAPPS_PATH`
    * On Windows, open a Command Prompt and run `setx STEAMAPPS_PATH "C:\Program Files (x86)\Steam\steamapps"`
    * On Mac, open `~/.bash_profile` and add a line like `export STEAMAPPS_PATH="~/Library/Application Support/Steam/steamapps"`
    * Adjust the paths accordingly if you have installed Steam to a different location 
1. Install the [Oracle Java 8 JDK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) (*not* Java 9+)
1. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/) (the free Community edition is fine)
1. Through Steam, install Slay the Spire (stable branch)
1. From the Steam Workshop, install "Mod the Spire", "BaseMod", and "StSLib"
1. Clone the repository
1. Open the project in IntelliJ IDEA:
    1. Choose "Import Project"
    1. Choose the repository folder
    1. Select "Maven"
    1. Press next a few times, all other settings can be left at defaults 
1. Follow the [these instructions from the StS-DefaultModBase wiki](https://github.com/Gremious/StS-DefaultModBase/wiki/Step-3:-Packaging-and-Playing-the-Default;-Writing-Your-First-Mod!) to build the mod package and debug it