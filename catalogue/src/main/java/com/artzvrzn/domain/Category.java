package com.artzvrzn.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", schema = "app")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(
    name = "parent_id",
    referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_category_category")
  )
  private Category parent;

  @Override
  public String toString() {
    return "Category{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
