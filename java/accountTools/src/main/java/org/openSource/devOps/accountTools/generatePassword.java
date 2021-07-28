package org.openSource.devOps.accountTools;

import java.util.*; 
  
public class generatePassword
{ 
    public static void main(String[] args) 
    { 
        int length = Integer.parseInt(args[0]);
        System.out.println(geek_Password(length)); 
    } 
  
    static char[] geek_Password(int len) 
    { 
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
        String specialCharacters = "#_"; 

        Random randomMethod = new Random(); 
  
        char[] password = new char[len]; 
  
        Integer i2 = 0;
        for (Integer i = 0; i < len; i++) 
        { 
            i2++;
            if (i2 > 4)
            {
              i2 = 1;
            }
            switch (i2) { 
              case 1: 
                password[i] = capitalLetters.charAt(randomMethod.nextInt(capitalLetters.length())); 
                break; 
              case 2: 
                password[i] = lowercaseLetters.charAt(randomMethod.nextInt(lowercaseLetters.length())); 
                break; 
              case 3: 
                password[i] = numbers.charAt(randomMethod.nextInt(numbers.length())); 
                break; 
              case 4: 
                password[i] = specialCharacters.charAt(randomMethod.nextInt(specialCharacters.length())); 
                break; 
          } 
        } 
        return password; 
    } 
}