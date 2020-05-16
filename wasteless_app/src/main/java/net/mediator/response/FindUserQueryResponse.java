package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.dto.UserDTO;

public class FindUserQueryResponse implements Response {
    private UserDTO userDTO;

    public FindUserQueryResponse(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
