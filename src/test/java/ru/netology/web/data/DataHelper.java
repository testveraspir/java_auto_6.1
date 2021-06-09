package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    public static final String ID_FIRST_CARD = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    public static final String ID_SECOND_CARD = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";


    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {

        return new AuthInfo("vasya", "qwerty123");
    }


    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor
            (AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static VerificationCode invalidGetVerificationCodeFor
            (AuthInfo authInfo) {
        return new VerificationCode("1234");
    }

    @Value
    public static class TransferMoney {
        private String number;

        public static TransferMoney transferOnCardFirst() {
            return new TransferMoney("5559 0000 0000 0002");
        }

        public static TransferMoney transferOnCardSecond() {
            return new TransferMoney("5559 0000 0000 0001");
        }
    }

    @Value
    public static class Amount {
        private int sum;

        public static Amount transferSum() {
            int sum = (int) (Math.random() * 9998 + 1);
            return new Amount(sum);
        }

        public static Amount transferBigSum() {
            return new Amount(100000);
        }
    }

    @Value
    public static class AmountDouble {
        private double sumDouble;

        public static AmountDouble transferSumDouble() {
            double sumDouble = Math.random() * 9998 + 1;
            return new AmountDouble(sumDouble);
        }
    }


}
