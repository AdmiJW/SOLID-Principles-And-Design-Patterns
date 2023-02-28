package facade.subsystems;

import facade.Form;

public class FormValidator {
    public boolean validateForm(Form form) {
        if (form.getName() == null || form.getName().isEmpty()) {
            return false;
        }
        
        return true;
    }
}