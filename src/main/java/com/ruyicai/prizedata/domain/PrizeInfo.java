package com.ruyicai.prizedata.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Entity
@Table(name="tprizeinfo")
public class PrizeInfo {

	@Id
	@GeneratedValue
	private int id;
	private String lotno;
	private String batchcode;
	private String winbasecode;
	private String winspecialcode;
	private Date createdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public String getBatchcode() {
		return batchcode;
	}
	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}
	public String getWinbasecode() {
		return winbasecode;
	}
	public void setWinbasecode(String winbasecode) {
		this.winbasecode = winbasecode;
	}
	public String getWinspecialcode() {
		return winspecialcode;
	}
	public void setWinspecialcode(String winspecialcode) {
		this.winspecialcode = winspecialcode;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").deepSerialize(this);
    }
	
	public static PrizeInfo fromJsonToPrizeInfo(String json) {
        return new JSONDeserializer<PrizeInfo>().use(null, PrizeInfo.class)
        			.deserialize(json);
    }

	
	
	
}
