package com.artzvrzn.domain;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
@Table(name = "item_stock", schema = "app")
public class Stock {
  @Id
  @GeneratedValue(generator = "UUID")
  @Column(name = "item_id")
  private UUID id;
  @OneToOne(cascade = CascadeType.ALL, optional = false)
  @MapsId
  private Item item;
  private Integer quantity;
}
