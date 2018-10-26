package com.example.publikakun.trialapp.utils;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formats {
    private static final String PASSWORD_PATTERN = "[A-z][A-z][A-z][0-9][0-9][0-9]";


    public static String create(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,###,###.##");
        return formatter.format(amount);
    }

    public static String create(int amount) {
        DecimalFormat formatter = new DecimalFormat("#,###,###.##");
        return formatter.format(amount);
    }

    public static String phone(String phoneNumber) {
        if (phoneNumber.startsWith("0")) {
            return "+62" + phoneNumber.substring(1);
        } else if (!phoneNumber.startsWith("+")) {
            return "+" + phoneNumber;
        }
        else
            return phoneNumber;
    }
    public static int getProvider(String phoneNumber)
    {
        if (phoneNumber.startsWith("0811")||phoneNumber.startsWith("0812")||phoneNumber.startsWith("0813")||
                phoneNumber.startsWith("0852")||phoneNumber.startsWith("0853")||phoneNumber.startsWith("0821")||
                phoneNumber.startsWith("0822")||phoneNumber.startsWith("0823"))
        {
            return 0;
            //telkomsel
        }
        else if (phoneNumber.startsWith("0814")||phoneNumber.startsWith("0815")||phoneNumber.startsWith("0816")||
                phoneNumber.startsWith("0855")||phoneNumber.startsWith("0856")||phoneNumber.startsWith("0857")||
                phoneNumber.startsWith("0858"))
        {
            //indosat
            return 5;
        }
        else if (phoneNumber.startsWith("0817")||phoneNumber.startsWith("0818")||phoneNumber.startsWith("0819")||
                phoneNumber.startsWith("0859")||phoneNumber.startsWith("0877")||phoneNumber.startsWith("0878")||
                phoneNumber.startsWith("0831")|| phoneNumber.startsWith("0832")||phoneNumber.startsWith("0838"))
        {
            //xl & axis
            return 2;
        }
        else if (phoneNumber.startsWith("0881")|| phoneNumber.startsWith("0882")||phoneNumber.startsWith("0888"))
        {
            //smartfren
            return 4;
        }
        else if (phoneNumber.startsWith("0896")||phoneNumber.startsWith("0897")||phoneNumber.startsWith("0898"))
        {
            //tri
            return 6;
        }
        else
            return 1;
    }

    public static String removeTrailing(double d) {
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(d);
    }

    public static boolean password(String password1) {
        Pattern p = Pattern.compile(PASSWORD_PATTERN);
        Matcher m = p.matcher(password1);
        return m.find();
    }

    public static String getCurrency() {
        if (Locale.getDefault().getLanguage().equals(Locale.FRANCE.getLanguage())) {
            return "EUR";
        } else {
            return "IDR";
        }
    }

    public static boolean isName(String fullName) {
        return fullName.matches("[a-zA-Z ]+");
//        return fullName.matches("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}");
//        return fullName.matches("^\pL+[\pL\pZ\pP]{0,}$");
    }

    public static boolean isCharacter(String fullName) {
        return fullName.matches("[a-zA-Z]+");
    }

    public static boolean isCharacterAndSpace(String fullName) {
        return fullName.matches("[a-zA-Z ]+");
    }

    public String decimal(String number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(number);
    }
}
