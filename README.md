# o Lodjinha

Minimum SDK: API 15</br>
Language: Kotlin</br>
Architeture: MVP
Dependencies: Retrofit2, RxJava, RxAndroid, JUnit, Mockito, Espresso

## Structure:

- `app/` - Main application
- `app/test/` - Unit tests
- `app/androidTest/` - Instrumentation tests

## Running Tests

### Running Instrumentation Tests

#### In Android Studio
- Open a Instrumentation test class like `SplashScreenActivityTest.kt`
- Right click on the class and *Run* as Android Test.

#### From command-line via Gradle
- To run all the Instrumentation tests in the `app` module execute:

``` sh
./gradlew app:connectedAndroidTest
```

### Running Unit Tests
In Android Studio:
- Open a unit/local test unit class like `MainPresenterTest.kt`.
- Right click on the class and *Run* as JUnit test.

From command-line via Gradle:
- To run all the local unit tests in `app` execute:

```
./gradlew app:test
```

