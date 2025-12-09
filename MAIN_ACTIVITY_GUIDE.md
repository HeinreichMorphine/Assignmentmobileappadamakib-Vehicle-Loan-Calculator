# Guide to MainActivity.java

This document provides a detailed explanation of the `MainActivity.java` file, which is the core of the Vehicle Loan Calculator's functionality. Its primary role is to handle user input, perform loan calculations, and display the results.

## Table of Contents

- [Class Members](#class-members)
- [`onCreate()`](#oncreate)
- [`calculateLoan()`](#calculateloan)
- [Menu Handling](#menu-handling)

---

## Class Members

These variables are declared at the top of the class so they can be accessed from anywhere within `MainActivity`.

```java
private EditText vehiclePrice, downPayment, interestRate, loanPeriod;
private TextView loanAmountResult, totalInterestResult, totalPaymentResult, monthlyPaymentResult;
```

- **`EditText`**: These objects represent the input fields where the user enters the loan details.
- **`TextView`**: These objects are used to display the calculation results back to the user.

---

## `onCreate()`

This is the first method called when the activity is created. It's responsible for setting up the entire screen.

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // 1. Toolbar Setup
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationOnClickListener(v -> onBackPressed());

    // 2. Linking UI Elements
    vehiclePrice = findViewById(R.id.vehiclePrice);
    // ... (other findViewById calls)

    // 3. Button Click Listener
    Button calculateButton = findViewById(R.id.calculateButton);
    calculateButton.setOnClickListener(v -> calculateLoan());
}
```

1.  **Toolbar Setup**: Configures the app's top bar, adds a title, and sets up the back button.
2.  **Linking UI Elements**: The `findViewById()` method connects the Java variables (like `vehiclePrice`) to the corresponding UI elements defined in your `activity_main.xml` layout file.
3.  **Button Click Listener**: This code tells the `calculateButton` to execute the `calculateLoan()` method whenever it is clicked.

---

## `calculateLoan()`

This is the heart of the activity. It runs when the user clicks the "Calculate" button and performs all validation and calculation logic.

### Step 1: Clear Previous Errors

```java
vehiclePrice.setError(null);
downPayment.setError(null);
// ...
```

This ensures that any error messages from a previous calculation are cleared before a new one begins.

### Step 2: Input Validation

The method performs a series of checks to ensure the user's input is valid. It uses a `hasError` flag to track if any validation rule fails.

- **Check for Empty Fields**: Ensures that the user has not left any fields blank.

    ```java
    if (priceStr.isEmpty()) {
        vehiclePrice.setError("Field cannot be empty");
        hasError = true;
    }
    ```

- **Check for Logical Errors**: Ensures the numbers make sense in a real-world scenario.

    ```java
    // Price must be a positive number
    if (price <= 0) { ... }

    // Down payment can't be negative
    if (dp < 0) { ... }

    // Down payment can't exceed the vehicle price
    if (dp > price) { ... }
    ```

If any of these checks fail, it sets an error message directly on the `EditText` field and sets `hasError` to `true`. If `hasError` is true, the method stops before performing the calculation.

### Step 3: Calculation

If all validation checks pass, the code proceeds inside a `try-catch` block.

- **`try` Block**: This block attempts to convert the user's text input into numbers and perform the loan calculation.

    ```java
    double loanAmount = price - dp;
    double totalInterest = loanAmount * (rate / 100) * years;
    double totalPayment = loanAmount + totalInterest;
    double monthlyPayment = totalPayment / (years * 12);
    ```

- **`catch (NumberFormatException e)`**: This is a safety net. If the user enters something that isn't a number (e.g., "abc"), this block "catches" the error and shows a `Toast` message ("Invalid number format") instead of crashing the app.

### Step 4: Displaying Results

The final step is to format the calculated numbers into a currency format and display them in the result `TextView`s.

```java
DecimalFormat df = new DecimalFormat("#.00");
loanAmountResult.setText("Loan Amount: RM" + df.format(loanAmount));
```

---

## Menu Handling

These two methods manage the navigation menu in the toolbar.

- **`onCreateOptionsMenu()`**: This method inflates (creates) the menu from your `res/menu/nav_menu.xml` file.
- **`onOptionsItemSelected()`**: This method handles clicks on menu items. It checks which item was clicked (e.g., Home, About) and uses an `Intent` to navigate to the corresponding activity.

```java
if (id == R.id.nav_home) {
    startActivity(new Intent(this, HomeActivity.class));
    return true;
}
```
