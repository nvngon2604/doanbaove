package J6Store.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto implements Serializable {
	
	String username;
	String password;
	String fullname;
	String email;
	String photo;
}
