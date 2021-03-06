<div align="center"><p><a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#commandpotions">(<strong>Top</strong>)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#main-info">(Main Info)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#commands">(Commands)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#permissions">(Permissions)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#configuration">(<strong>Configuration</strong>)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#main-configuration-info">(Main Configuration Info)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#configuration-explained">(Configuration Explained)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#miscellaneous">(<strong>Miscellaneous</strong>)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#creditsthanks">(Credits/Thanks)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#to-do">(To-Do)</a> <a href="https://github.com/RageRoringTiger/CommandPotions/blob/master/README.md#downloads">(Downloads)</a></p>
</div>

# CommandPotions
**Created by RageRoringTiger**


## Main Info
This plugin let's you create potions that when they are drank by a player, run command(s). Then after a certain amount of time, the potion will run out and more command(s) will be run. During the time the potion is active specified particles will spawn around the player. This is all done through the config.

## Commands
There are only 2 commands you need to know:
> /cpot *potion* *player*

If *player* is not set, then the potion will be given to the command sender (player who typed the command)
> /cpot reload

Reload the plugin config.

## Permissions
To spawn in a potion a player need the permission *commandpotions.createpotion*. __Any__ player can drink a potion to run commands.

# Configuration
## Main Configuration Info
All of the plugin configuration is done through a simple config:

![Config Image](/images/config.png/)


## Configuration Explained
```
 main-config: #Main settings
   show-time-left: #If true gives the player the luck effect (which does nothing) to show how long until the potion will run out. (Will only show the latest potion)
   ambient: #If false will show particles from the luck effect

 particle-config: #Change particle offset/intensity/rate. (Only works with the particle from the potion, not the luck effect)
   offset-x: #Particle offset x
   offset-y: #Particle offset y
   offset-z: #Particle offset z
   count: #How intense is the particle effect?
   rate: #In ticks, how fast do particles spawn?

 potions: #This is where you make command potions.
   DO-NOT-REMOVE: #This is help the plugin locate your custom potions
   potion-name: #Potion name (Used in game -> /cpot potion-name )
     display-name: #What the potion name looks like in game.
     color: #What color your potion will be.
     custom-model-data: #Set the potion custom model data, disabled when set to -1
     particle: #The particles that spawn around the player during the duration of the potion.
     empty-after-drink: #If this is set to false the potion can be drunk a infinite amount of times, otherwise the potion will run out.
     time: #In ticks, how long the potions lasts.
     command-on-drink: #The command(s) that are run when a player drinks the potion. {player} is replaced with the player who drank the potion.
       (EX) say You have drank a potion
       (EX) msg {player} This is a message.
     command-on-expire: #The command(s) that are run when the potion runs out. {player} is replaced with the player who drank the potion.
       (EX) say The potion has run out
       (EX) kill {player}
```      

# Miscellaneous
## Credits/Thanks
This plugin was made by RageRoringTiger and suggested by Xp10d3 on the [bukkit forms!](https://bukkit.org/threads/command-potions.487593/)

Thanks for checking out my plugin :)

## To-Do
1. ~~Add lore to potions that shows what commands are run.~~
1. Create in game command potion creator GUI.
1. Add more command variables (like {player}).
1. FIX BUGS.

# Downloads
[RELEASE 1.1](https://github.com/RageRoringTiger/CommandPotions/raw/master/compiled/CommandPotions1-4.jar)

[RELEASE 1.0](https://github.com/RageRoringTiger/CommandPotions/raw/master/compiled/CommandPotions1-3.jar)

[PRE-RELEASE 0.9](https://github.com/RageRoringTiger/CommandPotions/raw/master/compiled/CommandPotions1-2.jar)

[PRE-RELEASE 0.8](https://github.com/RageRoringTiger/CommandPotions/raw/master/compiled/CommandPotions1-1.jar)

[PRE-RELEASE 0.7](https://github.com/RageRoringTiger/CommandPotions/raw/master/compiled/CommandPotions.jar)
