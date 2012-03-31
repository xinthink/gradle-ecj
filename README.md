＃ Gradle ECJ Plugin
This a plugin for using the Eclipse JDT core batch compler (ECJ) in [Gradle](http://gradle.org) builds.

The very first version of gradle-ecj (1.0-Beta):
  - requires Gradle 1.0-milestone-8a and later
  - integrates [ECJ 3.7.1](http://mvnrepository.com/artifact/org.eclipse.jdt.core.compiler/ecj)

## Usage
### Apply the plugin
To use the ECJ plugin, you must include and apply it in your build scripts:

```groovy
apply plugin: 'ecj'

buildscript {
    repositories {
        add(new org.apache.ivy.plugins.resolver.URLResolver()) {
            name = 'GitHub'
            addArtifactPattern 'http://cloud.github.com/downloads/[organisation]/[module]/[module]-[revision].[ext]'
        }
    }

    dependencies {
        classpath 'xinthink:gradle-ecj:1.0-Beta'
    }
}
```

### Configuration
By default, ecj plugin use the following compilation options or flags:

  - UTF-8 encoding
  - source and target compatibility, same as [Gradle Compile Spec](http://gradle.org/docs/current/dsl/org.gradle.api.tasks.compile.Compile.html)
  - ecj warning and error flags, please refer to [Eclipse JDT Help](http://help.eclipse.org/), this section: `Java development user guid / Tasks / Compiling Java code / Using batch compiler`, gradle-ecj uses same default values

You can override these default settings, by providing an `ecj` configuration closure, like this:

```groovy

ecj {
    encoding = 'utf-8'                  // default is utf-8

    warn << 'emptyBlock'                // enable a single warning, in addition to the defaults
    warn << 'enumSwitch' << 'unused'    // enable multiple warnings, in addition to the defaults
    warn -= 'typeHiding'                // suppress the given warning

    // warn = [ 'none' ]                // suppress all warnings

    //err << 'unused'                   // convert some warnings to errors
}
```

In addition, you can specify source and target compatiblity as normal gradle builds:

```groovy
sourceCompatibility = 1.6
targetCompatibility = 1.6
```
