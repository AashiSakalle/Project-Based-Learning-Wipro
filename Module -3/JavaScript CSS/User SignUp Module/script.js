// Global variable to track form start time for the 3-minute alert
let startTime;

/**
 * Initializes the timer and displays the current date/time.
 * Called on body load in index.html.
 */
function startTimeOut() {
    startTime = Date.now();
    displayDateTime();
    // Set up the 3-minute alert
    setTimeout(showAlert, 3 * 60 * 1000); // 3 minutes = 180000 milliseconds
}

/**
 * Displays an alert if the user takes more than 3 minutes.
 */
function showAlert() {
    // Check if the form has been submitted (or a successful validation has occurred)
    // before showing the alert. For simplicity, we'll just show the alert 
    // after 3 minutes regardless of submission status, as requested.
    alert("3 mins past.");
}

/**
 * Updates and displays the current date and time.
 */
function displayDateTime() {
    const dateTimeElement = document.getElementById('currentDateTime');
    const now = new Date();

    // Format for date and time
    const dateOptions = { year: 'numeric', month: 'long', day: 'numeric' };
    const timeOptions = { hour: '2-digit', minute: '2-digit', second: '2-digit' };

    const dateStr = now.toLocaleDateString('en-US', dateOptions);
    const timeStr = now.toLocaleTimeString('en-US', timeOptions);

    dateTimeElement.textContent = ${dateStr} | ${timeStr};

    // Update every second
    setTimeout(displayDateTime, 1000);
}

/**
 * Main validation function, called by the form's onsubmit event.
 * @returns {boolean} True if validation passes, false otherwise.
 */
function validateForm() {
    // Helper function for displaying errors and preventing submission
    const setError = (field, message) => {
        alert(${field}: ${message});
        document.getElementById(field.toLowerCase().replace(/\s/g, '')).focus();
        return false;
    };

    // Get form values
    const firstName = document.getElementById('firstName').value.trim();
    const lastName = document.getElementById('lastName').value.trim();
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const gender = document.querySelector('input[name="gender"]:checked');
    const mobileNumber = document.getElementById('mobileNumber').value.trim();
    const dob = document.getElementById('dob').value.trim();
    const email = document.getElementById('email').value.trim();

    // 1. First Name and Last Name Validation (Must be entered, Must be character)
    const charRegex = /^[A-Za-z]+$/;

    if (!firstName) return setError('First Name', 'Must be entered.');
    if (!charRegex.test(firstName)) return setError('First Name', 'Must be character.');

    if (!lastName) return setError('Last Name', 'Must be entered.');
    if (!charRegex.test(lastName)) return setError('Last Name', 'Must be character.');

    // 2. Password and Confirm Password Validation
    const passwordRegex = /^.{6,20}$/; // Length between 6 to 20 characters

    if (!password) return setError('Password', 'Must be entered.');
    if (!passwordRegex.test(password)) return setError('Password', 'Length should be between 6 to 20 characters.');

    if (!confirmPassword) return setError('Confirm Password', 'Must be entered.');
    if (!passwordRegex.test(confirmPassword)) return setError('Confirm Password', 'Length should be between 6 to 20 characters.');

    if (password !== confirmPassword) return setError('Confirm Password', 'Password and Confirm password should be same.');

    // 3. Gender Validation (Must be selected)
    if (!gender) return setError('Gender', 'Must be selected.');

    // 4. Mobile Number Validation
    // Format: XXX-XXXXXXX or XXXX-XXXXXXX (where X is a digit)
    const mobileRegex = /^(\d{3}-\d{7}|\d{4}-\d{7})$/;

    if (!mobileNumber) return setError('Mobile Number', 'Must be entered.');
    if (!mobileRegex.test(mobileNumber)) return setError('Mobile Number', 'Must be in below format: XXX-XXXXXXX or XXXX-XXXXXXX.');

    // 5. DOB Validation
    // Format: DD-MM-YYYY
    const dobRegex = /^\d{2}-\d{2}-\d{4}$/;

    if (!dob) return setError('DOB', 'Must be entered.');
    if (!dobRegex.test(dob)) return setError('DOB', 'Must be in below format: DD-MM-YYYY.');
    // Note: No advanced date validity check (e.g., leap year, valid day/month numbers) is included as it wasn't specified.

    // 6. Email Address Validation
    // Must contain @ and ., @ not first char, last dot after @, at least one char after @, at least one char after last dot.
    // The regex below covers all requirements in a single pattern:
    // 1. ^[^\s@]+ - Starts with characters that are not @ or whitespace
    // 2. @ - Requires an @ sign
    // 3. [^\s@]+ - At least one character after @
    // 4. \. - Requires a dot (.)
    // 5. [A-Za-z]{2,}$ - At least two characters after the last dot (common TLD length)
    // 6. The requirement "the last dot must at least be one character after the @ sign" is met by requiring [^\s@]+\. before the final part.
    // A simpler regex for the requirements is:
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; 

    // A more precise regex based on the specific rules:
    // It must contain @ and a dot (.)
    // @ must not be the first character: [^@].*
    // At least one character after @: @.+
    // Last dot must be one char after @: ensures a dot exists and is not immediately after @
    // At least one char after last dot: \.[^.]+$

    const preciseEmailRegex = /^[^@\s][^@\s]*@[^\s@]+\.[^\s@]+$/;

    if (!email) return setError('Email Address', 'Must be entered.');
    if (!preciseEmailRegex.test(email)) return setError('Email Address', 'Invalid email format. Must contain @ and a dot (.), @ must not be first char, and there must be characters after @ and the last dot.');
    
    // If all checks pass
    alert("Registration Successful!");
    return true; // Allows form submission (in a real app, you'd submit to a server here)
}