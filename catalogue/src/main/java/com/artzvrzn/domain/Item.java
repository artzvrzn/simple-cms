package com.artzvrzn.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item", schema = "app")
@EntityListeners(AuditingEntityListener.class)
public class Item {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  @CreatedDate
  private LocalDateTime createdAt;
  @Version
  @LastModifiedDate
  private LocalDateTime modifiedAt;
  private String name;
  private String description;
  @ManyToOne
  @JoinColumn(
    name = "category_id",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_item_category")
  )
  private Category category;
  @ManyToOne
  @JoinColumn(
    name = "measure_id",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_item_measure")
  )
  private Measure measure;
}
