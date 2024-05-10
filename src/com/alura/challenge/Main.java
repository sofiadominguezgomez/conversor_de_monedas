package com.alura.challenge;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultRates rates = new ConsultRates();

        System.out.println("//////////////////////////////////////////");
        System.out.println("¡Bienvenido al conversor de monedas!");

        // Display supported currency codes
        System.out.println("Códigos de moneda soportados:");
        String[][] supportedCodes = rates.getSupportedCodes();
        for (int i = 0; i < supportedCodes.length; i++) {
            System.out.println((i + 1) + ". " + supportedCodes[i][0] + " - " + supportedCodes[i][1]);
        }

        // Prompt user to select base currency
        System.out.println("Ingrese el número correspondiente al código de la moneda base:");
        int baseIndex = readValidIndex(scanner, supportedCodes.length);

        // Prompt user to select target currency
        System.out.println("Ingrese el número correspondiente al código de la moneda a la que desea convertir:");
        int targetIndex = readValidIndex(scanner, supportedCodes.length);

        // Convert indices to currency codes
        String base = supportedCodes[baseIndex - 1][0];
        String target = supportedCodes[targetIndex - 1][0];

        Rate rate = rates.consultRateExchange(base, target);
        System.out.println("La conversión de " + base + " a " + target + " es igual a " + rate.conversion_rate());
    }

    private static int readValidIndex(Scanner scanner, int maxIndex) {
        int index;
        do {
            System.out.print("Ingrese un número válido: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Ingrese un número válido: ");
                scanner.next(); // consume the invalid input
            }
            index = scanner.nextInt();
        } while (index < 1 || index > maxIndex);
        return index;
    }
}



// System.out.println("La conversión de " + base + " a " + target + " es igual a " + rate.conversion_rate());