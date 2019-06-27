package com.app.ascension.model;

import lombok.*;

@Data
public class VersionModel  {
  private String version = null;
  private Integer major = null;
  private Integer minor = null;
  private Integer patch = null;
}
