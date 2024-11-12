package pro.sky.skyproList.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class EmployeeNamesNotValidation extends RuntimeException {
    public EmployeeNamesNotValidation() { super(); }
}
