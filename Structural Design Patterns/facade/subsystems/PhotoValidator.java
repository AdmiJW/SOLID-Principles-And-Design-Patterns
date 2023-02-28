package facade.subsystems;

import facade.Form;

public class PhotoValidator {
    public boolean validatePhoto(Form form) {
        if (form.getPhoto() == null) {
            return false;
        }
        
        return true;
    }    
}
