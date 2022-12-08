package com.artzvrzn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@ToString
@Jacksonized
@JsonNaming(SnakeCaseStrategy.class)
public class CategoryDto {
  @JsonProperty(access = Access.READ_ONLY)
  private Long id;
  private String name;
  @JsonProperty(access = Access.READ_ONLY)
  private Long parentId;
  @JsonProperty(access = Access.READ_ONLY)
  private String parentName;
}
