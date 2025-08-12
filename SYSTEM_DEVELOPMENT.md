# Health App System Development Document

## 1. Introduction

This document provides a technical overview of the Health App Android project. It is intended for developers who will be working on the project.

The goal of the Health App is to provide a simple and efficient way for chronic patients to manage their health.

## 2. Project Structure

The project follows a standard Android project structure.

-   `/.gitignore`: Specifies files to be ignored by Git.
-   `/build.gradle`: Top-level build file for the entire project.
-   `/gradlew` & `/gradlew.bat`: Gradle wrapper scripts.
-   `/gradle/wrapper/`: Contains the Gradle wrapper jar and properties.
-   `/settings.gradle`: Declares the modules included in the project.
-   `/app`: The main application module.
    -   `/build.gradle`: Build file for the app module.
    -   `/src/main/`: Main source set.
        -   `/AndroidManifest.xml`: Declares the app's components and permissions.
        -   `/java/com/example/healthapp/`: Root package for the app's source code.
            -   `/data/`: Contains the Room database components (DAOs, Database class).
            -   `/model/`: Contains the entity classes for the database.
            -   `/receiver/`: Contains the `BroadcastReceiver` for notifications.
            -   `*Activity.kt`: The activity classes for the app's UI.
        -   `/res/`: Resource files.
            -   `/drawable/`: Images and other drawable resources.
            -   `/layout/`: XML layout files for the activities.
            -   `/values/`: XML files for colors, strings, and themes.
            -   `/xml/`: XML configuration files.
    -   `/src/test/`: Source set for unit tests.

## 3. Architecture

The application is structured to follow a basic Model-View-ViewModel (MVVM) architecture, although the current implementation is a simplified version.

*   **View:** The UI layer, consisting of Activities and XML layouts. The views are responsible for displaying data to the user and capturing user input. They observe ViewModels for data changes.
*   **ViewModel:** (Not yet implemented) The ViewModel would be responsible for holding and managing UI-related data. It would expose data to the View and handle user interactions.
*   **Model:** The data layer, consisting of the Room database, entities, and DAOs. This layer is responsible for all data persistence and retrieval.

This separation of concerns makes the app more modular, testable, and maintainable.

## 4. Database Schema

The app uses the Room Persistence Library to manage its SQLite database. The database schema is defined by the following entities:

*   **User:** Represents a user of the app.
    *   `id` (Int, Primary Key, Auto-generated)
    *   `username` (String)
    *   `passwordHash` (String)
*   **Medication:** Represents a medication.
    *   `id` (Int, Primary Key, Auto-generated)
    *   `userId` (Int, Foreign Key to User)
    *   `name` (String)
    *   `dosage` (String)
    *   `frequency` (String)
    *   `time` (String)
    *   `stock` (Int)
    *   `lowStockThreshold` (Int)
*   **HealthMetric:** Represents a health metric reading.
    *   `id` (Int, Primary Key, Auto-generated)
    *   `userId` (Int, Foreign Key to User)
    *   `date` (Long)
    *   `bloodPressure` (String)
    *   `bloodSugar` (String)
    *   `heartRate` (String)
    *   `notes` (String)
*   **Checkup:** Represents a checkup appointment.
    *   `id` (Int, Primary Key, Auto-generated)
    *   `userId` (Int, Foreign Key to User)
    *   `date` (Long)
    *   `doctorName` (String)
    *   `location` (String)
    *   `notes` (String)

## 5. Dependencies

The project uses the following key dependencies:

*   **AndroidX Libraries:**
    *   `core-ktx`: Provides Kotlin extensions for Android.
    *   `appcompat`: Provides backward compatibility for older Android versions.
    *   `constraintlayout`: For building flexible and responsive layouts.
    *   `material`: Provides Material Design components.
*   **Room Persistence Library:** For managing the local SQLite database.
    *   `room-runtime`: The core Room library.
    *   `room-compiler`: The annotation processor for Room.
    *   `room-ktx`: Provides Kotlin extensions for Room.
*   **Kotlin:** The primary programming language for the app.
*   **JUnit & AndroidX Test:** For unit and instrumentation testing.

## 6. How to Build and Run

1.  **Prerequisites:**
    *   Android Studio (latest stable version recommended).
    *   Android SDK.
2.  **Clone the Repository:**
    *   Clone this repository to your local machine.
3.  **Open in Android Studio:**
    *   Open Android Studio and select "Open an Existing Project".
    *   Navigate to the cloned repository and select it.
4.  **Sync Gradle:**
    *   Android Studio should automatically sync the project with Gradle. If not, click the "Sync Project with Gradle Files" button in the toolbar.
5.  **Run the App:**
    *   Select an emulator or connect a physical device.
    *   Click the "Run" button in the toolbar.

## 7. Future Development

*   **Implement ViewModels:** Introduce `ViewModel`s to better manage UI-related data and separate logic from the Activities.
*   **Implement Repository Pattern:** Create a repository layer to abstract the data source (Room database) from the ViewModels.
*   **Add Hilt for Dependency Injection:** Use Hilt to manage dependencies and make the code more modular and testable.
*   **Improve UI/UX:** Enhance the user interface and user experience with more polished layouts and animations.
*   **Add more features:**
    *   Charts and graphs for visualizing health metrics.
    *   A calendar view for checkups.
    *   The ability to edit and delete existing records.
