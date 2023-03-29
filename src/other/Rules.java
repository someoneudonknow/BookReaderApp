/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package other;

public final class Rules {
    public static final int IS_REQUIRED = 0;
    public static final int IS_NUMBER = 1;
    public static final int IS_MIN = 2;
    public static final int IS_MAX = 3;
    public static final int IS_PHONE_NUMBER = 4;
    public static final int IS_EMAIL = 5;
    public static final int IS_PASSWORD_CONFIRM = 6;
    public static final int IS_CONTAINS_WHITE_SPACE = 7;
    
    private int currentRule;
    private int limit = -1;
    private String customMessage;
    private String confirmValue;
    
    public Rules(String customMessage, int rule) {
        this.currentRule = rule;
        this.customMessage = customMessage;
    }

    public Rules(String customMessage, int rule, int limit) {
        this.currentRule = rule;
        this.limit = limit;
        this.customMessage = customMessage;
    }

    public Rules(String customMessage, int rule, String valueToConfirm) {
//        if(rule != Rules.IS_PASSWORD_CONFIRM) {
//            throw new Exception("This rule 3rd parameter is not supported string format");
//        }
        this.currentRule = rule;
        this.customMessage = customMessage;
        this.confirmValue = valueToConfirm;
    }
    
    public final int getCurrentRule() {
        return currentRule;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public final boolean isRequired(String value) {
        return (!value.isBlank() || !value.isEmpty());
    }

    public final boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public final boolean min(String value) {
        return (value.strip().length() >= this.limit);
    }

    public final boolean max(String value) {
        return (value.strip().length() <= this.limit);
    }

    public final boolean isPhoneNumber(String value) {
        String regex = "^(0\\d{1,2}[ -]?)?\\d{7,8}$";
        return value.strip().matches(regex);
    }

    public final boolean isEmail(String value) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return value.strip().matches(regex);
    }

    public final boolean isPasswordConfirm(String value1, String value2) {
        return value1.strip().equals(value2.strip());
    }
    
    public final boolean isContainWhiteSpace(String value) {
        return !value.strip().contains(" ");
    }
    
    public boolean validateRule(String value){
        return switch (this.currentRule) {
            case Rules.IS_REQUIRED -> this.isRequired(value);
            case Rules.IS_NUMBER -> this.isNumber(value);
            case Rules.IS_MAX -> this.max(value);
            case Rules.IS_MIN -> this.min(value);
            case Rules.IS_PHONE_NUMBER -> this.isPhoneNumber(value);
            case Rules.IS_EMAIL -> this.isEmail(value);
            case Rules.IS_PASSWORD_CONFIRM -> this.isPasswordConfirm(value, this.confirmValue);
            case Rules.IS_CONTAINS_WHITE_SPACE -> this.isContainWhiteSpace(value);
            default -> false;
        };
    }

    @Override
    public String toString() {
        return "Rules{" + "currentRule=" + currentRule + ", limit=" + limit + ", customMessage=" + customMessage + '}';
    }
}
