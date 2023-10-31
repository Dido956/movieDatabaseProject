package bg.softuni.moviedatabase.service;

import bg.softuni.moviedatabase.model.dto.UserRegisterDTO;

public interface UserService {


    boolean register(UserRegisterDTO userRegisterDTO);
}
