package com.google.rolecall.jsonobjects;

import java.util.Date;

/* Represents JSON information for a User recieved from a client. A null property means
 * it was not supplied in the json request body.
 */
public class UserInfo {
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private Date dateOfBirth;
  private String emergencyContactName;
  private String emergencyContactNumber;
  private String comments;
  private Boolean isActive;

  public Integer getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public String getEmergencyContactName() {
    return emergencyContactName;
  }

  public String getEmergencyContactNumber() {
    return emergencyContactNumber;
  }

  public String getComments() {
    return comments;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setFirstName(String name) {
    this.firstName = name;
  }

  public void setLastName(String name) {
    this.lastName = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setEmergencyContactName(String emergencyContactName) {
    this.emergencyContactName = emergencyContactName;
  }

  public void setEmergencyContactNumber(String emergencyContactNumber) {
    this.emergencyContactNumber = emergencyContactNumber;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public UserInfo() {}
}
