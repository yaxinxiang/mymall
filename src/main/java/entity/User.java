package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private String user_id;
    private String user_name;
    private String user_pwd;
    private String user_sex;
    private String user_birthday;
    private String user_identity_code;
    private String user_email;
    private String user_tel;
    private String user_address;
    private Integer user_status;

    public User() {
    }

    public User(String user_id, String user_name, String user_pwd, String user_sex, String user_birthday, String user_identity_code, String user_email, String user_tel, String user_address, Integer user_status) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pwd = user_pwd;
        this.user_sex = user_sex;
        this.user_birthday = user_birthday;
        this.user_identity_code = user_identity_code;
        this.user_email = user_email;
        this.user_tel = user_tel;
        this.user_address = user_address;
        this.user_status = user_status;
    }
}
