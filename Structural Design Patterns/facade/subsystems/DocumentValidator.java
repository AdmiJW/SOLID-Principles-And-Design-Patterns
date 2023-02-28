package facade.subsystems;

import facade.Form;

public class DocumentValidator {
    public boolean validateDocument(Form form) {
        if (form.getDocuments() == null) {
            return false;
        }
        
        return true;
    }
}
