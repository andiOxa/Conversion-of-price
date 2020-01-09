package Converter;

import java.math.BigDecimal;

public class ConverterLogic {

    private static final int NJE_QIND_MILION = 1000000000;
    private static final int NJE_MILION = 1000000;
    private static final int NJE_QIND_MIJE = 100000;
    private static final int NJE_MIJE = 1000;
    private static final int NJE_QIND = 100;
    private static String [] NJESHET = {"nje","dy","tre","kater","pese","gjashte","shtate","tete","nente"};
    private static final String [] DHJETESHET = {"njembedhjete","dymbedhjete","trembedhjete","katermbedhjete",
            "pesembedhjete","gjashtembedhjete","shtatembedhjete","tetembedhjete","nentembedhjete"};
    private static final String [] DHJETESHET_E_PLOTA = {
            "dhjete","njezet","tredhjete","katerdhjete","pesedhjete","gjashtedhjete","shtatedhjete","tetedhjete","nentedhjete"
    };

    /**
     * @param price -> number (price) which the user wants to convert and it's required to be as input parameter from user
     * @return -> this method will calculate each case and will get the quotient which will pass as an argument
     *          -> to the priceToWords() method and after that it's going to concatenate all the words into
     *          a single sentence(result).
     * @throws NegativeNumberException
     */
    public static String spellPriceWithWords(BigDecimal price) throws NegativeNumberException{
        StringBuilder word = new StringBuilder();
        String priceToString = price.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
        if(priceToString.startsWith("-")){
            throw new NegativeNumberException("Negative number was given, please check it again");
        }
        double number = Double.parseDouble(priceToString);
        number = number % NJE_QIND_MILION;

        int quotient = (int) (number / NJE_MILION);
        if (quotient > 0 && (number % NJE_MILION) != 0) {
            word.append(priceToWords(quotient) + " milion e ");
        }if(quotient > 0 && (number % NJE_MILION) ==0){
            word.append(priceToWords(quotient) + " milion Euro");
        }

        number = number % NJE_MILION;
        quotient = (int) (number / NJE_QIND_MIJE);
        if (quotient > 0 && (number % NJE_QIND_MIJE) !=0) {
            word.append(priceToWords(quotient) + " qind e ");
        }if(quotient > 0 && (number % NJE_QIND_MIJE) ==0){
            word.append(priceToWords(quotient) + " qind mije Euro ");
        }

        number = number % NJE_QIND_MIJE;
        quotient = (int) (number / NJE_MIJE);
        if (quotient > 0 && (number % NJE_MIJE) != 0) {
            word.append(priceToWords(quotient) + " mije e ");
        }if(quotient > 0 && (number % NJE_MIJE) == 0){
            word.append(priceToWords(quotient) + " mije Euro ");
        }

        number = number % NJE_MIJE;
        quotient = (int) (number / NJE_QIND);
        if (quotient > 0 && (number % NJE_QIND) != 0) {
            word.append(priceToWords(quotient) + " qind e ");
        }if(quotient > 0 && (number % NJE_QIND) == 0){
            word.append(priceToWords(quotient) + " qind Euro");
        }

        number = number % NJE_QIND;
        if (number != 0) {
            word.append(priceToWords((int) number) + " Euro");
        }

        int decimal = 0;
        if (number % 1 != 0) {
            BigDecimal bigDecimal = BigDecimal.valueOf(number);
            String decimalInWords = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).toPlainString();
            decimalInWords = decimalInWords.substring(decimalInWords.indexOf(".") + 1);
            decimal = Integer.parseInt(decimalInWords);
            if (decimalInWords.length() == 1) {
                decimal *= 10;
            }
            if (word.toString().trim().length() > 0) {
                word.append(" dhe ");
            }
            word.append(priceToWords(decimal));
            if (decimal >= 1) {
                word.append(" Cent ");
            }
        }

        if(word.toString().trim().length() == 0){
            return " Zero ";
        }
        return word.toString();
    }

    /**
     * @param number -> it's the residue(reminder) which was found
     *               when the given number in spellPriceWithWords()
     *               was devided from final static fields  depending from case.
     * @return -> this method will convert every single number into an albanian word.
     */
    public static String priceToWords(int number){
        int intQuotient = (int) (number / 10);
        StringBuilder word = new StringBuilder();

        if(intQuotient > 0){
            if(intQuotient == 1 && (number % 10) > 0){
                word.append(DHJETESHET[(number % 10) - 1]);
                return word.toString();
            }
            word.append(DHJETESHET_E_PLOTA[intQuotient - 1]);
        }
        int reminder = number % 10;
        if(reminder > 0){
            if(word.length() > 0){
                word.append(" e ");
            }
            word.append(NJESHET[reminder - 1]);
        }
        return word.toString();
    }
}
