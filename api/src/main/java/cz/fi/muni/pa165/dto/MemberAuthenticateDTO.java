package cz.fi.muni.pa165.dto;

import org.hibernate.validator.constraints.NotBlank;
/**
 *
 * @author tchomo
 */
public class MemberAuthenticateDTO {
    
    @NotBlank
    private String memberEmail;

    @NotBlank
    private String password;

    public MemberAuthenticateDTO() {
    }
        public MemberAuthenticateDTO(String memberEmail, String password) {
        this.memberEmail = memberEmail;
        this.password = password;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
