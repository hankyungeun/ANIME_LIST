package anime_list.model.dto;

import org.checkerframework.common.returnsreceiver.qual.This;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@This
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
	private String passwd;
	private String name;
	public UserDto(String userId) {
		super();
		this.userId = userId;
	}
	public UserDto(String passwd, String name) {
		super();
		this.passwd = passwd;
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ddddd [userId=" + userId + ", passwd=" + passwd + ", name=" + name + "]";
	}



    
}
    