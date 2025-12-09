# Coding Guide

This document provides guidelines and best practices for developing and maintaining the Vehicle Loan Calculator application. Following these guidelines will help ensure code quality, consistency, and maintainability.

## Table of Contents

- [Code Style and Formatting](#code-style-and-formatting)
- [Architectural Pattern](#architectural-pattern)
- [UI and UX Best Practices](#ui-and-ux-best-practices)
- [Dependency Management](#dependency-management)
- [Version Control with Git](#version-control-with-git)

## Code Style and Formatting

To maintain a consistent and readable codebase, please adhere to the following code style and formatting guidelines:

- **Formatting**: Follow the default Android Studio code style for Java. You can reformat your code at any time by pressing `Ctrl+Alt+L` (or `Cmd+Option+L` on Mac).
- **Naming Conventions**: Use clear and descriptive names for variables, functions, and classes. Follow standard Java naming conventions, such as using camelCase for variables and methods, and PascalCase for class names.

    *Example from `MainActivity.java`*:

    ```java
    // Variables are in camelCase
    private EditText vehiclePrice;
    private TextView loanAmountResult;

    // Methods are in camelCase
    private void calculateLoan() {
        // ...
    }
    ```

- **XML Naming**: For XML layouts, use snake_case for resource IDs (e.g., `loan_amount_input`).

    *Example from `activity_main.xml`*:

    ```xml
    <EditText
        android:id="@+id/vehicle_price"
        ...
    />
    ```

## Architectural Pattern

This project follows a simple Model-View-Controller (MVC) pattern, where:

- **Model**: Represents the data and business logic (e.g., loan calculation).
- **View**: The UI components that display the data (e.g., `activity_main.xml`).
- **Controller**: The `Activity` or `Fragment` that handles user input and updates the model and view.

*Example of MVC in practice*:

- **View (`activity_main.xml`)**: Defines the layout with `EditText` fields for input and `TextView`s for results.
- **Controller (`MainActivity.java`)**: In the `calculateLoan()` method, the controller reads user input from the view, performs the loan calculation (the model logic), and then updates the view with the results.

## UI and UX Best Practices

To ensure a consistent and user-friendly experience, follow these UI and UX best practices:

- **Use String Resources**: Avoid hardcoding text in your layouts. Instead, define all user-facing strings in `res/values/strings.xml` to support localization and make your code easier to maintain.

    *Before (Hardcoded string)*:

    ```xml
    <Button
        android:text="Calculate"
        ...
    />
    ```

    *After (Using a string resource)*:

    ```xml
    <!-- In res/values/strings.xml -->
    <string name="calculate_button_text">Calculate</string>

    <!-- In activity_main.xml -->
    <Button
        android:text="@string/calculate_button_text"
        ...
    />
    ```

- **Accessibility**: Provide content descriptions for all `ImageView` and `ImageButton` elements to support screen readers. Use the `android:contentDescription` attribute for this purpose.

    *Example from `activity_main.xml`*:

    ```xml
    <ImageView
        android:id="@+id/appIcon"
        android:src="@drawable/carpic"
        android:contentDescription="@string/car_icon_description"
        ...
    />
    ```

- **Responsive Layouts**: Use `ConstraintLayout` to create flexible and responsive layouts that adapt to different screen sizes and orientations.

## Dependency Management

This project uses Gradle to manage dependencies. When adding new libraries:

- **Use the latest stable versions** whenever possible.
- **Sync your project with the Gradle files** after making changes to dependencies.
- **Review the library's documentation** to ensure it is compatible with the project's minimum SDK version.

## Version Control with Git

To keep the project history clean and easy to follow, please adhere to these Git best practices:

- **Create descriptive commit messages**: Your commit messages should clearly explain the changes you made and why.
- **Use feature branches**: Create a new branch for each new feature or bug fix. This helps keep the `main` branch stable and makes it easier to review changes.
- **Push regularly**: Push your changes to the remote repository frequently to avoid losing work and to keep your branch up to date with the latest changes.
