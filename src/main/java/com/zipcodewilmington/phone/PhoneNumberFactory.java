package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory
{
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberFactory.class);

    private PhoneNumberFactory()
    {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) throws InvalidPhoneNumberFormatException
    {
         PhoneNumber[] phoneList  = new PhoneNumber[phoneNumberCount];
        for(int i = 0;i < phoneNumberCount; i++)
        {
            PhoneNumber num = createRandomPhoneNumber();
            phoneList[i] = num;
        }
        System.out.println(phoneList.length);
        if(phoneList.length > 0)
        {
            return phoneList;
        }

        return null;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    private static PhoneNumber createRandomPhoneNumber() throws InvalidPhoneNumberFormatException
    {
        int area = ThreadLocalRandom.current().nextInt(100, 1000);
        int central = ThreadLocalRandom.current().nextInt(100, 1000);
        int phone = ThreadLocalRandom.current().nextInt(100, 10000);

        return createPhoneNumberSafely(area, central, phone);
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) throws InvalidPhoneNumberFormatException
    {

        int areaLength = String.valueOf(areaCode).length();
        int centralLength = String.valueOf(centralOfficeCode).length();
        int phoneLength = String.valueOf(phoneLineCode).length();
        String fullNumber = null;

        if((areaLength == 3 && centralLength == 3) && phoneLength == 4)
        {
            fullNumber = "("+areaCode+")-"+centralOfficeCode+"-"+phoneLineCode+"";

            return createPhoneNumber(fullNumber);
        }
        else
        {
            logger.info(fullNumber + " is not a valid phone number");
            return null;
        }
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException
    {

        logger.info("Attempting to create a new phone number "+ phoneNumberString);


        return new PhoneNumber(phoneNumberString);
    }
}