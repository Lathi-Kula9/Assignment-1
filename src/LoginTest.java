
public class LoginTest {

    public void testUserName_CorrectlyFormatted() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        assertTrue(login.checkUserName());
    }

    public void testUserName_IncorrectlyFormatted() {
        Login login = new Login("Lathitha", "Kula", "kula!!!!!!",
                "Lathitha@9", "+27838968976");

        assertFalse(login.checkUserName());
    }

    public void testUserName_UnderscoreAtStartStillWorks() {
        Login login = new Login("Lathitha", "Kula", "_kula",
                "Lathitha@9", "+27838968976");

        assertTrue(login.checkUserName());
    }

    public void testUserName_HasUnderscoreButTooLong() {
        Login login = new Login("Lathitha", "Kula", "ab_cdef",
                "Lathitha@9", "+27838968976");

        assertFalse(login.checkUserName());
    }

    public void testPassword_MeetsComplexityRequirements() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        assertTrue(login.checkPasswordComplexity());
    }

    public void testPassword_DoesNotMeetComplexityRequirements() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "password", "+27838968976");

        assertFalse(login.checkPasswordComplexity());
    }

    public void testPassword_ExactlyEightCharactersAndValid() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Abcde1!x", "+27838968976");

        assertTrue(login.checkPasswordComplexity());
    }

    public void testPassword_MissingOnlySpecialCharacter() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Abcdefg1", "+27838968976");

        assertFalse(login.checkPasswordComplexity());
    }

    public void testPassword_HasEveryCharacterTypeButTooShort() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "A1!bcd", "+27838968976");

        assertFalse(login.checkPasswordComplexity());
    }

    public void testCellPhoneNumber_CorrectlyFormatted() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        assertTrue(login.checkCellPhoneNumber());
    }

    public void testCellPhoneNumber_IncorrectlyFormatted() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "0896655");

        assertFalse(login.checkCellPhoneNumber());
    }

    public void testCellPhoneNumber_ExactlyTenDigitsAfterCode() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+271234567890");

        assertTrue(login.checkCellPhoneNumber());
    }

    public void testCellPhoneNumber_ElevenDigitsAfterCodeFails() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+2712345678901");

        assertFalse(login.checkCellPhoneNumber());
    }

    public void testCellPhoneNumber_LettersMixedInFails() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27abc123456");

        assertFalse(login.checkCellPhoneNumber());
    }

    public void testRegisterUser_AllDetailsValid() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        String expected = "Username successfully captured. Password successfully "
                + "captured. Cell phone number successfully added.";

        assertEquals(expected, login.registerUser());
    }

    public void testRegisterUser_FailsOnUsername() {
        Login login = new Login("Lathitha", "Kula", "lathithakula",
                "Lathitha@9", "+27838968976");

        String expected = "Username is not correctly formatted; please ensure that "
                + "your username contains an underscore and is no more than five "
                + "characters in length.";

        assertEquals(expected, login.registerUser());
    }

    public void testRegisterUser_FailsOnPassword() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "password", "+27838968976");

        String expected = "Password is not correctly formatted; please ensure that "
                + "the password contains at least eight characters, a capital "
                + "letter, a number, and a special character.";

        assertEquals(expected, login.registerUser());
    }

    public void testRegisterUser_FailsOnCellPhoneNumber() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "0896655");

        String expected = "Cell phone number incorrectly formatted or does not "
                + "contain international code.";

        assertEquals(expected, login.registerUser());
    }

    public void testRegisterUser_DifferentValidDetailsStillSucceed() {
        Login login = new Login("Lathitha", "Kula", "ab_1",
                "Str0ng#Pass", "+27711234567");

        String expected = "Username successfully captured. Password successfully "
                + "captured. Cell phone number successfully added.";

        assertEquals(expected, login.registerUser());
    }

    public void testRegisterUser_PasswordCheckedBeforeCellPhoneNumber() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "short", "0821234567");

        String expected = "Password is not correctly formatted; please ensure that "
                + "the password contains at least eight characters, a capital "
                + "letter, a number, and a special character.";

        assertEquals(expected, login.registerUser());
    }

    public void testLoginUser_Successful() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        assertTrue(login.loginUser("L_kul", "Lathitha@9"));
    }

    public void testLoginUser_Failed() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        assertFalse(login.loginUser("L_kul", "wrongPassword1!"));
    }

    public void testLoginUser_WrongCaseUsernameFails() {
        Login login = new Login("Lathitha", "Kula", "l_kul",
                "Lathitha@9", "+27838968976");

        assertFalse(login.loginUser("L_kul", "Lathitha@9"));
    }

    public void testLoginUser_EmptyCredentialsFail() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        assertFalse(login.loginUser("", ""));
    }

    public void testReturnLoginStatus_Successful() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        String expected = "Welcome Lathitha, Kula it is great to see you again.";

        assertEquals(expected, login.returnLoginStatus("L_kul", "Lathitha@9"));
    }

    public void testReturnLoginStatus_Failed() {
        Login login = new Login("Lathitha", "Kula", "L_kul",
                "Lathitha@9", "+27838968976");

        String expected = "Username or password incorrect, please try again.";

        assertEquals(expected, login.returnLoginStatus("L_kul", "wrongPassword1!"));
    }

    public void testReturnLoginStatus_SuccessfulWithDifferentUser() {
        Login login = new Login("Naledi", "Dlamini", "nal_2",
                "Secure1@Pass", "+27821234567");

        String expected = "Welcome Naledi, Dlamini it is great to see you again.";

        assertEquals(expected, login.returnLoginStatus("nal_2", "Secure1@Pass"));
    }

    private void assertFalse(boolean checkUserName) {
        throw new UnsupportedOperationException("Not supported yet."); 

    }

    private void assertEquals(String expected, String returnLoginStatus) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void assertTrue(boolean loginUser) {
        throw new UnsupportedOperationException("Not supported yet."); }
}