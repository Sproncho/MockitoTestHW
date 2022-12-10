package telran.java2022.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegisterDto {
	String login;
	String password;
	String firstName;
	String lastName;
}
