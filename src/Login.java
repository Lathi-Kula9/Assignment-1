import java.util.Scanner;

public class Login {

    // Public member variables to store user details
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String cellPhoneNumber;

    // Constructor to set up user details when creating a Login object
    public Login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Ensures username has an underscore and stays within 5 characters
    public boolean checkUserName() {
        boolean containsUnderscore = username != null && username.contains("_");
        boolean isShortEnough = username != null && username.length() <= 5;

        return containsUnderscore && isShortEnough;
    }

    // Checks that password is at least 8 characters with capital, number, and special character
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        // Loop through characters to verify requirements safely without regex bugs
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapitalLetter = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialCharacter = true;
            }
        }

        return hasCapitalLetter && hasNumber && hasSpecialCharacter;
    }

    // Validates that phone number starts with +27 and has valid remaining digits
    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber == null || !cellPhoneNumber.startsWith("+27")) {
            return false;
        }
        String numberPart = cellPhoneNumber.substring(3);
        return numberPart.length() >= 1 && numberPart.length() <= 10 && numberPart.matches("\\d+");
    }

    // Runs all validation checks and returns the matching outcome message
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your "
                    + "username contains an underscore and is no more than five "
                    + "characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital "
                    + "letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain "
                    + "international code.";
        }

        return "Username successfully captured. Password successfully captured. "
                + "Cell phone number successfully added.";
    }

    // Checks entered login details against saved credentials
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username != null && this.password != null
                && this.username.equals(enteredUsername)
                && this.password.equals(enteredPassword);
    }

    // Generates a status response for the user during login
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Getter methods to access fields
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    // Interactive interface using Scanner
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            
            System.out.print("Enter Cell Phone Number: ");
            String cellPhoneNumber = scanner.nextLine();
            
            Login user = new Login(firstName, lastName, username, password, cellPhoneNumber);
            
            String result = user.registerUser();
            System.out.println("\n" + result);
        }
    }

}