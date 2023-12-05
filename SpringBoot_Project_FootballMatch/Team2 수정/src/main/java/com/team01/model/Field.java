package com.team01.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "project_field")
public class Field implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "field_num")
	private Long fieldNum;
	@Column(name = "field_name")
	private String fieldName;
	@Column(name = "field_form")
	private String fieldForm;
	@Column(name = "field_tel")
	private String fieldTel;
	@Column(name = "field_area")
	private String fieldArea;
	@Column(name = "field_unit")
	private int fieldUnit;
	@Column(name = "field_open")
	private String fieldOpen;
	@Column(name = "field_close")
	private String fieldClose;
	@Column(name = "field_memo")
	private String fieldMemo;
	@Column(name = "field_address")
	private String fieldAddress;
	@Column(name = "field_detail_ddress")
	private String fieldDetailAddress;
	@Column(name = "field_extra_ddress")
	private String fieldExtraAddress;
	@Column(name = "field_zipcode")
	private String fieldZipcode;
	@Column(name = "field_sigungu")
	private String fieldSigungu;
	
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "field", cascade = CascadeType.REMOVE)
	private List<Book> book;

	
	public void field_update(Field field){
        this.fieldName = field.getFieldName();
        this.fieldForm = field.getFieldForm();
        this.fieldArea = field.getFieldArea();
        this.fieldUnit = field.getFieldUnit();
        this.fieldOpen = field.getFieldOpen();
        this.fieldClose = field.getFieldClose();
        this.fieldMemo = field.getFieldMemo();
        this.fieldAddress = field.getFieldAddress();
        this.fieldSigungu = field.getFieldSigungu();
    }
}
