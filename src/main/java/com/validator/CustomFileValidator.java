package com.validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
 

 
@Component
public class CustomFileValidator implements Validator{
public static final String MIME_TYPE="text/plain";
public static final String FILE_EXTENTION=".TXT";
public static final int FILE_NAME_LENGHT=200;
public static final long TEN_MB_IN_BYTES = 10485760;
   
 
    
    public void validate(MultipartFile file, Errors errors) {
        if(file.isEmpty()){
            errors.rejectValue("file", "upload.file.required");
        }
        else if(!MIME_TYPE.equalsIgnoreCase(file.getContentType())){
            errors.rejectValue("file", "upload.invalid.file.type");
        }
       
        else if(file.getSize() > TEN_MB_IN_BYTES){
            errors.rejectValue("file", "upload.exceeded.file.size");
        }
        else if(file.getName().toUpperCase().endsWith(FILE_EXTENTION)){
            errors.rejectValue("file", "upload.exceeded.file.size");
        }
        else if(file.getName().toUpperCase().length()>=FILE_NAME_LENGHT){
            errors.rejectValue("file", "upload.exceeded.file.name.size");
        }
        
    }



	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
 
}