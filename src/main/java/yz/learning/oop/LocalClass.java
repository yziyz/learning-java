package yz.learning.oop;

/**
 * 局部类
 *
 * @author 袁臻
 * 2017/08/29 19:47
 */
public class LocalClass {
    static String regularExpression = "[^0-9]";

    private static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {
        final int numberLength = 10;

        class PhoneNumber {
            String formattedPhoneNumber = null;

            //Valid in JDK8 and later;
            PhoneNumber(String phoneNumber) {
                String currentNumber = phoneNumber.replaceAll(regularExpression, "");
                if (currentNumber.length() == numberLength) {
                    formattedPhoneNumber = currentNumber;
                } else {
                    formattedPhoneNumber = null;
                }
            }

            private String getNumber() {
                return formattedPhoneNumber;
            }

            //Valid in JDK 8 and later;
            private void printOriginalNumbers() {
                System.out.println("Original numbers are:" + phoneNumber1 + " and " + phoneNumber2);
            }
        }

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

        myNumber1.printOriginalNumbers();

        if (myNumber1.getNumber() == null) {
            System.out.println("Number1 is invalid.");
        } else {
            System.out.println("Number2 is " + myNumber1.getNumber());
        }

        if (myNumber2.getNumber() == null) {
            System.out.println("Number2 is invalid.");
        } else {
            System.out.println("Number2 is " + myNumber2.getNumber());
        }
    }

    public static void main(String[] args) {
        validatePhoneNumber("123-456-7890", "456-7890");
    }
}
