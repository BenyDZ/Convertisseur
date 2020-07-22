package com.example.convertisseur;

public class Convertisseur {

    public static double enFahrenheit(double tempCelsius){

        double tempFahr = 0;

        tempFahr = ((9 * tempCelsius)/5) + 32;
        tempFahr = arrondi(tempFahr,2);
        return tempFahr;
    }

    public static double enCelsius(double tempFahrenheit){

        double tempCelsius = 0;

        tempCelsius = ((tempFahrenheit - 32) * 5)/9;
        tempCelsius = arrondi(tempCelsius,2);
        return tempCelsius;
    }

    public static double enMiles(double km){

        double miles = 0;

        miles = km * 0.621371;
        miles = arrondi(miles, 2);
        return miles;
    }

    public static double enKm(double miles){

        double km = 0;

        km = miles * 1.609344;
        km = arrondi(km, 2);
        return km;
    }

    private static double arrondi(double A, int B) {
        return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
    }
}
