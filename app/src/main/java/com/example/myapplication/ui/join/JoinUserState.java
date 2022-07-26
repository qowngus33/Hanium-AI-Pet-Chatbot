package com.example.myapplication.ui.join;

public class JoinUserState {

    private String email = null;
    private String password = null;
    private String rePassword = null;

    public boolean isEmailValid() {
        if (email == null || !email.contains("@")) {
            return false;
        } else {
            return !email.trim().isEmpty();
        }
    }

    public boolean isPasswordValid() {
        return password != null && password.trim().length() > 5 && isAlphaOrDigit(password);
    }

    public boolean isPasswordSame(){
        if(password == null || rePassword == null)
            return false;
        return password.equals(rePassword);
    }

    public boolean isValidData() {
        return isPasswordSame() &&  isPasswordValid() && isEmailValid();
    }


    private boolean isAlphaOrDigit(String password) {
        for(int i=0;i<password.length();i++) {
            if (!Character.isLetterOrDigit(password.charAt(i)))
                return false;
        }
        return true;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

}
