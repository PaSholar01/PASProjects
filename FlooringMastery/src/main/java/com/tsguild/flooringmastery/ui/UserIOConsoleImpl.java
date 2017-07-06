/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class UserIOConsoleImpl implements UserIO {

    private Scanner console = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public void printSameLn(String message){
        System.out.print(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                double inputDouble = Double.parseDouble(input);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct Numerical Value.");
                input = console.nextLine();
            }
        }

        double inputDouble = Double.parseDouble(input);
        return inputDouble;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                double inputDouble = Double.parseDouble(input);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct Numerical Value.");
                input = console.nextLine();
            }
        }

        double inputDouble = Double.parseDouble(input);

        boolean isIt = false;
        while (!isIt) {
            if (inputDouble < max && inputDouble > min) {
                isIt = true;
            } else {
                print("Out of bounds, try again.");
                inputDouble = console.nextDouble();
            }

        }
        return inputDouble;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                float inputFloat = Float.parseFloat(input);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct Numerical Value.");
                input = console.nextLine();
            }
        }

        float inputFloat = Float.parseFloat(input);
        return inputFloat;
    }

    public float readFloat(String prompt, float min, float max) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                float inputFloat = Float.parseFloat(input);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct Numerical Value.");
                input = console.nextLine();
            }
        }

        float inputFloat = Float.parseFloat(input);
        boolean isIt = false;
        while (!isIt) {
            if (inputFloat < max && inputFloat > min) {
                isIt = true;
            } else {
                print("Out of bounds. Try again.");
                inputFloat = console.nextFloat();
            }

        }
        return inputFloat;
    }

    public int readInt(String prompt) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                int inputInteger = Integer.parseInt(input);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct Numerical Value.");
                input = console.nextLine();
            }
        }

        int inputInteger = Integer.parseInt(input);
        return inputInteger;
    }

    public int readInt(String prompt, int min, int max) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                int inputInteger = Integer.parseInt(input);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct Numerical Value.");
                input = console.nextLine();
            }
        }

        int inputInteger = Integer.parseInt(input);
        boolean isIt = false;
        while (!isIt) {
            if (inputInteger <= max && inputInteger >= min) {
                isIt = true;
            } else {
                print("Out of Bounds, Re-try: ");
                inputInteger = console.nextInt();
            }

        }
        return inputInteger;
    }

    public long readLong(String prompt) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                long inputLong = Long.parseLong(input);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct thing");
                input = console.nextLine();
            }
        }

        long inputLong = Long.parseLong(input);
        return inputLong;
    }

    public long readLong(String prompt, long min, long max) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                long inputLong = Long.parseLong(input);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct thing");
                input = console.nextLine();
            }
        }

        long inputLong = Long.parseLong(input);
        boolean isIt = false;
        while (!isIt) {
            if (inputLong < max && inputLong > min) {
                isIt = true;
            } else {
                print("Out of Bounds, Re-try: ");
                inputLong = console.nextLong();
                continue;
            }

        }
        return inputLong;
    }

    public String readString(String prompt) {
        print(prompt);
        String input = console.nextLine();
        return input;
    }

    public char readChar(String prompt) {
        print(prompt);
        char inputChar = ' ';
        while (console.hasNextByte()) {
            inputChar = (char) console.nextByte();
        }
        return inputChar;
    }

    @Override
    public BigDecimal readDoubleReturnBigD(String prompt) {
        print(prompt);
        String input = console.nextLine();
        boolean isTrue = false;

        while (!isTrue) {
            try {
                double inputDouble = Double.parseDouble(input);
                isTrue = true;
                if(inputDouble < 0){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct thing");
                input = console.nextLine();
            }
        }

        double inputDouble = Double.parseDouble(input);
        isTrue = false;

        while (!isTrue) {
            try {
                BigDecimal inputBD = new BigDecimal(inputDouble);
                isTrue = true;
            } catch (NumberFormatException e) {
                isTrue = false;
                System.out.println("Please enter a correct thing");
                input = console.nextLine();
            }
        }
        BigDecimal inputBD = new BigDecimal(inputDouble);
        return inputBD;
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        print(prompt);

        boolean isFalse = false;
        String input = console.nextLine();
        while (!isFalse) {

            try {
                LocalDate ld = LocalDate.parse(input);
                isFalse = true;
            } catch (DateTimeParseException e) {
                isFalse = false;
                System.out.println("Please enter a correct Date Value.\n" +
                                   "The format is YYYY-MM-DD.");
                input = console.nextLine();
            }

        }
        LocalDate ld = LocalDate.parse(input);
        return ld;
    }

    public boolean readStringYesOrNo(String prompt) {

        boolean userResponse = false;

        boolean isValid = false;

        while (!isValid) {

            print(prompt);

            String input = console.nextLine();
            
            if (input.equalsIgnoreCase("yes")) {
                userResponse = true;
                isValid = true;
            } else if (input.equalsIgnoreCase("no")) {
                userResponse = false;
                isValid = true;
            } else {
                System.out.println("Please enter a valid input.(yes/no)" + "\n");
            }
        }

        return userResponse;

    }
}



/*BigDecimal newTaxRate = null;
        boolean canParseTaxRate = false;
        while(!canParseTaxRate){
        String stringNewTaxRate = io.readString("Enter the tax rate (" + editedOrder.getTaxRate() + "): ");
        if (stringNewTaxRate.equals("")) {
            newTaxRate = editedOrder.getTaxRate();
            canParseTaxRate = true;
        } else {
            try {
                newTaxRate = new BigDecimal(stringNewTaxRate);
                canParseTaxRate = true;
            } catch(NumberFormatException e){
                 
                canParseTaxRate = false
            }
        }
        }*/
