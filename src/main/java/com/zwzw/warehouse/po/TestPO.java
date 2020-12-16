package com.zwzw.warehouse.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "TEST")
public class TestPO {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqFleet")
	@SequenceGenerator(name = "seqFleet", sequenceName = "defaultSequence", allocationSize = 1)
	private Long ID;
	
	@Column(name = "NAME")
	private String name;
}
