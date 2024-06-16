# Cartograph Maps Android service API
The Android service API offers means for writing custom map plugins for Cartograph Maps 3.6.0 (and newer).

# Usage
Add ``maven { url "https://jitpack.io" }`` to the Android project ``settings.gradle`` file:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" } // See: https://github.com/jitpack/jitpack.io/blob/master/ANDROID.md
    }
}
```

Then add ``implementation 'com.github.CartographMaps:cartograph-androidapi:0.6.0'`` to the dependencies section in the project's ``build.gradle``.

# Example code
An example implementation can be found here: https://github.com/CartographMaps/cartograph-androidapi-example
