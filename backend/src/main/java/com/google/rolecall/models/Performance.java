package com.google.rolecall.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.rolecall.restcontrollers.exceptionhandling.RequestExceptions.InvalidParameterException;

@Entity
@Table
public class Performance {

  public enum Status {
    Published, Canceled, Draft;
  }
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  private String title;

  @Column(length=1024)
  private String description;

  @Column(nullable = false)
  private String location;

  @Column(nullable = false)
  private Date dateTime;

  @Enumerated(EnumType.ORDINAL)
  private Status status;

  @OneToMany(mappedBy = "performance", 
      cascade = CascadeType.ALL, 
      orphanRemoval = true, 
      fetch = FetchType.EAGER)
  private List<PerformanceSection> performanceSections = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description == null ? "" : description;
  }

  public String getLocation() {
    return location;
  }

  public Date getDate() {
    return dateTime;
  }

  public Status getStatus() {
    return status;
  }

  public void publish() {
    if(status == Status.Draft) {
      this.status = Status.Published;
    }
  }

  public void cancel() {
    this.status = Status.Canceled;
  }

  public void addPerformanceSection(PerformanceSection performanceSection) {
    performanceSection.setPerformance(this);
    performanceSections.add(performanceSection);
  }

  public void removePerformanceSection(PerformanceSection performanceSection) {
    performanceSection.setPerformance(null);
    performanceSections.remove(performanceSection);
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public Performance() {
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {
    private Performance performance;
    private String title;
    private String description;
    private String location;
    private Date dateTime;

    public Builder setTitle(String title) {
      if(title != null) {
        this.title = title;
      }

      return this;
    }

    public Builder setNotes(String description) {
      if(description != null) {
        this.description = description;
      }

      return this;
    }

    public Builder setLocation(String location) {
      if(location != null) {
        this.location = location;
      }

      return this;
    }

    public Builder setDateTime(Integer dateTimeMilis) {
      if(dateTimeMilis != null) {
        this.dateTime = new Date(dateTimeMilis);
      }

      return this;
    }

    public Performance build() throws InvalidParameterException {
      if(title == null || location == null || dateTime == null) {
        throw new InvalidParameterException("Performance requires a Title, Location, and a Time");
      }

      performance.title = this.title;
      performance.description = this.description;
      performance.location = this.location;
      performance.dateTime = this.dateTime;

      return performance;
    }


    public Builder(Performance performance) {
      this.performance = performance;
      this.title = performance.getTitle();
      this.description = performance.getDescription();
      this.location = performance.getLocation();
      this.dateTime = performance.getDate();
    }

    public Builder() {
      this.performance = new Performance();
      performance.status = Status.Draft;
    }
  }
}
