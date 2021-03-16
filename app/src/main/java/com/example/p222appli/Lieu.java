package com.example.p222appli;

public class Lieu {
    private static String adresse;
    private static int type;

    public static int getType() {

        return type;
    }

    public String getAdresse() {

        return adresse;
    }

    public static void setType(Integer type) {
        Lieu.type = type;
    }

    public void setAdresse(String adresse) {

        this.adresse = adresse;
    }

    public static String toString(Lieu lieu) {
        String s = adresse;
        return s;
    }
}
