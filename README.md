# CocoLib

[![JitPack](https://jitpack.io/v/yooksi/CocoLib.svg)](https://jitpack.io/#yooksi/CocoLib) [![License](https://img.shields.io/github/license/yooksi/CocoLib)](https://www.gnu.org/licenses/) [![Discord](https://img.shields.io/discord/710517912485494794)](https://discord.gg/dKY9xW)

CocoLib is a Minecraft modding library for Forge that introduces simple and clean solutions to complex problems developers usually encounter when creating Minecraft mods.

## Motivation

Creating mods for Minecraft can prove to be a challenging and at times frustrating experience. Developers are required to have a good understanding of both Java and Forge when implementing more complex mod ideas. In addition to this, working on multiple projects that are trying to implement similar concepts or using similar method to accomplish tasks requires a fair amount of code duplication.

CocoLib does a lot of this for you by providing simple and efficient ways of solving many problems without having to spend an unreasonable amount of time researching implementation practices and resort to code duplication. It tries to do this while maintaining best Java and Forge practices.

## Features

- Provides precise methods of converting Minecraft to real time and vice versa.
- Provides a way to keep track of game day time through defined segments.
- Provides a way to store and update sprite render data in object instances.
- Provides support for positioning, aligning and drawing sprites on screen.
- Contains general purpose modding utility methods and classes.
- Comes with a set of useful custom annotations. 

## Where to get it?

CocoLib is hosted on [JitPack](https://jitpack.io/#yooksi/CocoLib) so head over there and get the latest release.

Here is an example of how to implement the library with Gradle:

```groovy
// Definines where Gradle should look for declared dependencies
// Declare this AFTER the buildscript block (first script block)
// and BEFORE MinecraftForge Gradle plugin configuration
repositories {
	...
	maven { url 'https://jitpack.io' }
}

minecraft {
	...
}

dependencies {
    // Specify the version of Minecraft to use
    minecraft "net.minecraftforge:forge:${minecraftVersion}-${forgeVersion}"
    
    // Define 'cocoLibVerion' property in gradle.properties
    // with fully qualified version number you want to use
    // i.e cocoLibVersion=1.15.2-0.1.1
    implementation fg.deobf("com.github.yooksi:CocoLib:${cocoLibVerion}")
}
```

Alternatively you can check the repository [releases](https://github.com/yooksi/CocoLib/releases) and get the latest release from there.  
Each repository  production release contains three `jar` types that you can download:

- `-dev.jar` is a non-obfuscated version of the jar used by developers.
- `-sources.jar` contains project source files used by developers.
- `-.jar` is an obfuscated production-ready jar used (mostly) by players. 

Developers will want either the dev or production jar (optionally) accompanied by sources jar to make reading and understanding the library code easier when working with their mods.

Players will want only the production jar which they should treat as a standard game mod.

 ## How to use?

### Developers

- Browse through project [test module](https://github.com/yooksi/CocoLib/tree/master/src/test/java/io/yooksi/cocolib) to find useful examples.

### Players

- Install the library like any other mod by placing the production jar in `mods` directory. 

  Note that the library will not do anything by itself, it is only used as a dependency by other mods.  
  Use it only if you have an installed mod that depends on this library.

## Running tests

All test classes can be found in the `test` module under `src/test/java`. 


Majority of Minecraft mod tests are integration tests and unfortunately Forge doesn't handle well loading classes from different modules in development environment so before running integration tests you should be aware of a couple of things:

- Running `genIntelliJRuns` gradle task generates run configurations for the test `sourceSet`.
- In addition to generating production classes the`classes` gradle task will also generate test classes. This means that any task that involves `classes` task will also generate test classes (i.e. `build` task).
- After all classes have been generated, production classes will be merged with test classes via `mergeProductionWithTestClasses` gradle task to avoid any complications with Java and Forge like class export errors during runtime.

## License

This library is licensed under [General Public License v3.0](https://www.gnu.org/licenses).

Software that depends on, is built upon or derived from this library is conditioned on making available complete source code of licensed works and modifications, which include larger works using a licensed  work, under the same license. Copyright and license notices must be preserved.
