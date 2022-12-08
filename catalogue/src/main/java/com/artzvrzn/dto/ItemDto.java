package com.artzvrzn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
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
public class ItemDto {
  @JsonProperty(access = Access.READ_ONLY)
  private UUID id;
  @JsonProperty(access = Access.READ_ONLY)
  private LocalDateTime createdAt;
  @JsonProperty(access = Access.READ_ONLY)
  private LocalDateTime modifiedAt;
  private String name;
  private String description;
  private Long categoryId;
  private Long measureId;
}
