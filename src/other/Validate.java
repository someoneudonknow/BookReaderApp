package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Validate {

    private String errorMessage;
    private boolean isValid;
    private List<Rules> rulesList;

    public Validate(String value, Rules[] rulesList) {
        this.rulesList = new ArrayList<>();
        this.rulesList.addAll(Arrays.asList(rulesList));
        this.errorMessage = "";
        this.isValid = false;
        this.validate(value);
    }

    public boolean isValid() {
        return this.isValid;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    private void validate(String value) {
        boolean isValidateValid = false;

        for (Rules r : this.rulesList) {
            isValidateValid = r.validateRule(value);
            this.errorMessage = isValidateValid ? "" : r.getCustomMessage();
            if (!isValidateValid) {
                break;
            }
        }

        this.isValid = isValidateValid;
    }
}
