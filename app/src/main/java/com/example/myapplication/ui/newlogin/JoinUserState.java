package com.example.myapplication.ui.newlogin;

import com.example.myapplication.R;

public class JoinUserState {
    private String email;
    private String password;
    private String rePassword;

    public boolean isEmailValid() {
        if (email == null || !email.contains("@")) {
            return false;
        } else {
            return !email.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    public boolean isPasswordValid() {
        return password != null && password.trim().length() > 5 && isAlphaOrDigit(password);
    }

    public boolean isPasswordSame(){
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
}
