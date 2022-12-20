package br.com.rng.dscatalog.dtos;

import br.com.rng.dscatalog.services.validations.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

    private static final long serialVersionUID = 1L;

    private String password;

    public UserInsertDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}