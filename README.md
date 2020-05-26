<p align="center">
    <img src="./assets/banner_light.png">
</p>
<p align="center">
    <a href="https://jitpack.io/#yooksi/cocolib">
        <img src="https://jitpack.io/v/yooksi/cocolib.svg">
    </a>
    <a href="https://curse.nikky.moe/api/url/384336">
		<img src="https://curse.nikky.moe/api/img/384336?logo">
	</a>
    <a href="https://www.gnu.org/licenses/">
        <img src="https://img.shields.io/github/license/yooksi/cocolib">
    </a>
    <a href="https://discord.gg/dKY9xW">
        <img src="https://img.shields.io/discord/710517912485494794">
    </a>
</p>

## About

CocoLib is a Minecraft modding library for Forge that introduces simple and clean solutions to complex problems developers usually encounter when creating Minecraft mods.

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

This depends on what you want to use the mod for.

- **Players** will want only the production jar which can be download from [CurseForge](#curseforge). 
- **Developers** will want either the dev or production jar (optionally) accompanied by sources jar to make reading and understanding the library code easier when working with their mods.
- Each production release contains three `jar` types that you can download:
  - `-dev.jar` is a non-obfuscated version of the jar used by developers.
  - `-sources.jar` contains project source files used by developers.
  - `-.jar` is an obfuscated production-ready jar mostly used by players.

Note that the mod can always be downloaded from [Github](#github).

### CurseForge

This is the **recommended** way of obtaining a production jar for library users.

Head on over to the [CurseForge](https://www.curseforge.com/minecraft/mc-mods/cocolib/files) page and download the mod from there.

### Maven

CocoLib is hosted on [JitPack](https://jitpack.io/#yooksi/CocoLib) so head over there and get the latest release.

Here is the **recommended** way of getting the library in your project:

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

     // Improve my modding experience!
    implementation "com.github.yooksi:CocoLib:${cocoLibVersion}:dev"
}
```

*Note that the `cocoLibVersion` property in this example was defined in `gradle.properties` to make accessing and reading version numbers easier. You should update the property (or just replace the variable) to a fully qualified version of the library you want to use.*

The build will try to resolve the *deobfuscated* version of the library built for use by developers (indicated by the `dev` classifier) and add it to `implementation` configuration. This is by far the simplest way of making the library available to your mod during compile and runtime.

Another way to get the library would be to use `fg.deobf` right after declaring the configuration type to indicate that the production jar should be *deobfuscated* after being resolved. This is not necessary and just adds extra work during build phase, this is why the project provides the `dev` jar. Besides, this way you need to manually attach source files since the created jar ends up in Forge cache folder.

### Github

This is the **alternative** way to obtain mod jar files. You should only obtain the mod this way if you were instructed to, know what you're doing or are unable to download from both CurseForge and JitPack.

Check the [releases](https://github.com/yooksi/CocoLib/releases) section in project repository page to get the latest release.

 ## How to use it?

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