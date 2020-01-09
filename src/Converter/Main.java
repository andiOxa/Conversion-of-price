package Converter;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

       try {
          System.out.println(ConverterLogic.spellPriceWithWords(BigDecimal.valueOf(312.31)));
       }catch (NegativeNumberException ex){
           ex.printStackTrace();
       }
    }

}
