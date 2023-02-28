package facade;

import facade.subsystems.DocumentValidator;
import facade.subsystems.FileSystem;
import facade.subsystems.FormValidator;
import facade.subsystems.PhotoValidator;

public class ApplicationFormService {
    
    public void submitForm(Form form) {
        FormValidator formValidator = new FormValidator();
        DocumentValidator documentValidator = new DocumentValidator();
        PhotoValidator photoValidator = new PhotoValidator();
        FileSystem fileSystem = new FileSystem();
        
        if (
            documentValidator.validateDocument(form) 
            && photoValidator.validatePhoto(form)
            && formValidator.validateForm(form)) 
        {
            fileSystem.saveFile(form.getName(), form.getPhoto(), form.getDocuments());
        } else {
            System.out.println("Form is not valid");
        }
    }



    public static void main(String[] args) {
        ApplicationFormService applicationFormService = new ApplicationFormService();
        
        Form form = new Form("John Doe", "photo.jpg", "document.pdf");  
        applicationFormService.submitForm(form);

        Form form2 = new Form("John Doe", null, null);
        applicationFormService.submitForm(form2);
    }
}
