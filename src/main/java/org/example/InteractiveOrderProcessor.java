package org.example;

import java.util.Scanner;

public class InteractiveOrderProcessor {
    public static double calculateSubTotal(double unitPrice, int quantity){
        return unitPrice * quantity;
    }

    public static void interactiveStringEquality(String firstStr, String secondStr){
        System.out.println("String 1: \"" + firstStr +"\"");
        System.out.println("String 2: \"" + secondStr +"\"");

        boolean doubleEqualResult = firstStr==secondStr;
        System.out.print("String 1 == String 2: " + doubleEqualResult);
        if(doubleEqualResult){
            System.out.print(" (Strings 1 and 2 point to the same memory location; same objects)");
        } else{
            System.out.println(" (Compares references, which are different for user input strings)");
        }

        boolean equalsResult = firstStr.equals(secondStr);
        System.out.print("String 1 .equals() String 2: " + equalsResult);
        if(equalsResult){
            System.out.print(" (Strings 1 and 2 have the same content)");
        } else{
            System.out.println(" (Content is different due to case or characters)");
        }

        boolean equalsIgnoreCaseResult = firstStr.equalsIgnoreCase(secondStr);
        System.out.print("String 1 .equalsIgnoreCase() String 2: " + equalsIgnoreCaseResult);
        if(equalsIgnoreCaseResult){
            System.out.print(" (Content is identical, ignoring case)");
        } else{
            System.out.println(" (Content is not identical)");
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Interactive Order Processor!");

        System.out.println("\n--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Is customer a member (true/false)?: ");
        boolean isMember = scanner.nextBoolean();
        scanner.nextLine();
        if(!isMember){
            System.out.println("Only members can order.");
        } else{
            System.out.print("Enter customer tier (Regular, Silver, Gold): ");
            String customerTier = scanner.nextLine();

            System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
            String shippingZone = scanner.nextLine();

            System.out.print("Enter discount code (SAVE10, FREESHIP, or \"\" for none): ");
            String discountCode = scanner.nextLine();

            System.out.println("\n--- Order Details ---");
            System.out.printf("Unit Price: $%.2f%n", unitPrice);
            System.out.println("Quantity: " + quantity);
            System.out.println("Is Member: " + isMember);
            System.out.println("Customer Tier: " + customerTier);
            System.out.println("Shipping Zone: " + shippingZone);
            System.out.println("Discount Code: " + discountCode);

            double subTotal = calculateSubTotal(unitPrice, quantity);
            System.out.println("\n--- Calculation Steps ---");
            System.out.printf("Initial Subtotal: $%.2f%n", subTotal);

            // Tier-Based Discount:
            double tierTotal;
            if(customerTier.equalsIgnoreCase("Gold")){
                tierTotal = subTotal * 0.85;
                System.out.printf("After Tier Discount (Gold -15%%): $%.2f%n", tierTotal);
            } else if (customerTier.equalsIgnoreCase("Silver")) {
                tierTotal = subTotal * 0.90;
                System.out.printf("After Tier Discount (Silver -10%%): $%.2f%n", tierTotal);
            } else {
                tierTotal = subTotal;
                System.out.println("No Tier Discount: $" + tierTotal);
            }

            // Quantity Discount:
            double quantityTotal;
            if(quantity >= 5){
                quantityTotal = tierTotal * 0.95;
                System.out.printf("After Quantity Discount (5%% for >=5 items): $%.2f%n", quantityTotal);
            } else {
                quantityTotal = tierTotal;
                System.out.printf("No Quantity Discount: $%.2f%n", quantityTotal);
            }

            double shippingCost = 0;
            // Promotional Code Application:
            double promoTotal;
            if(discountCode.equals("SAVE10") && quantityTotal>75){
                promoTotal = quantityTotal - 10.00;
                System.out.printf("After Promotional Code (SAVE10 for >$75): $%.2f%n", promoTotal);
            } else if(discountCode.equalsIgnoreCase("FREESHIP")){
                promoTotal = quantityTotal;
                System.out.printf("After Promotional Code (FREESHIP) shipping cost is $%.2f%n", shippingCost);
                System.out.printf("Total: $%.2f%n", promoTotal);
            } else{
                promoTotal = quantityTotal;
                System.out.printf("No Promotional Code! Total: $%.2f%n", promoTotal);
            }

            // Small Order Surcharge:
            double result = (promoTotal<25) ? (promoTotal+3) : (promoTotal);
            String surcharge = (promoTotal < 25) ? "" : " (No surcharge)";
            System.out.printf("After Small Order Surcharge (if applicable): $%.2f%s%n", result, surcharge);

            // Shipping Cost Calculation:
            if(!discountCode.equalsIgnoreCase("FREESHIP")){
                switch (shippingZone){
                    case "ZoneA":
                        shippingCost = 5.00;
                        System.out.printf("\nShipping Cost: $%.2f %s%n", shippingCost, "(ZoneA)");
                        break;
                    case "ZoneB":
                        shippingCost = 12.50;
                        System.out.printf("\nShipping Cost: $%.2f %s%n", shippingCost, "(ZoneB)");
                        break;
                    case "ZoneC":
                        shippingCost = 20.00;
                        System.out.printf("\nShipping Cost: $%.2f %s%n", shippingCost, "(ZoneC)");
                        break;
                    default:
                        shippingCost = 25.00;
                        System.out.printf("\nShipping Cost: $%.2f%n", shippingCost);
                        break;
                }
            }

            double finalOrderTotal = result + shippingCost;
            System.out.printf("\nFinal Order Total: $%.2f%n", finalOrderTotal);
        }

        // String Equality Demo:
        System.out.println("\n--- String Equality Demo ---");
        System.out.print("Enter first string for comparison: ");
        String firstStr = scanner.nextLine();

        System.out.print("Enter second string for comparison: ");
        String secondStr = scanner.nextLine();

        interactiveStringEquality(firstStr, secondStr);

        scanner.close();
    }
}