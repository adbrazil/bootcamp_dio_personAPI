package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.entity.Phone;
import one.digitalinnovation.personapi.enums.PhoneType;

public class PhoneUtils {

    public static  final String  PHONE_NUMBER ="1199999-9999";
    public static  final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    public static  final long  PHONE_ID =1L;


    public static PhoneDTO createFakeDTO(){
         return PhoneDTO
                 .builder()
                 .number(PHONE_NUMBER)
                 .type(PhoneType.MOBILE)
                 .build();
    }

    public static Phone createFakeEntity(){

       return  Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PhoneType.MOBILE)
                .build();

    }

}
