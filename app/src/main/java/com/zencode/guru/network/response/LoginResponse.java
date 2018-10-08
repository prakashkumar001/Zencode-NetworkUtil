package com.zencode.guru.network.response;

public class LoginResponse {
    /**
     * access_token : MirwBHn3VkTrooakCeru
     * userid : 32
     * firstname : Deepika
     * lastname : null
     * emailid : deepika1@gmail.com
     * mobileno : 9789701700
     * phoneno :
     * userimage : http://www.steats.com/img/profile/Penguins_1538235977.jpg
     * address :
     * status : 1
     * success : 1
     * message : Login Success
     */

    private String access_token;
    private String userid;
    private String firstname;
    private Object lastname;
    private String emailid;
    private String mobileno;
    private String phoneno;
    private String userimage;
    private String address;
    private String status;
    private String success;
    private String message;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Object getLastname() {
        return lastname;
    }

    public void setLastname(Object lastname) {
        this.lastname = lastname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
