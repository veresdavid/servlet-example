package com.first.examples;

import java.time.LocalDate;

/**
 * Created by ui2016 on 2016.08.02..
 */
public class User {

  private String name;
  private LocalDate birthday;
  private double weight;

  public User(String name, LocalDate birthday, double weight) {
    this.name = name;
    this.birthday = birthday;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public double getWeight() {
    return weight;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    if (Double.compare(user.weight, weight) != 0) {
      return false;
    }
    if (!name.equals(user.name)) {
      return false;
    }
    return birthday.equals(user.birthday);

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = name.hashCode();
    result = 31 * result + birthday.hashCode();
    temp = Double.doubleToLongBits(weight);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", birthday=" + birthday +
        ", weight=" + weight +
        '}';
  }

}
