package com.example.timer.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ignacy on 08.03.18.
 */

@Entity
public class Statistics {

	String formattedSingle;
	String formattedMo3;
	String formattedAvg5;
	String formattedAvg12;
	String formattedAvg50;
	String formattedAvg100;

	long single;
	long mo3;
	long avg5;
	long avg12;
	long avg50;
	long avg100;
	@PrimaryKey(autoGenerate = true)
	private long id;

	public Statistics(String formattedSingle,
			String formattedMo3,
			String formattedAvg5,
			String formattedAvg12,
			String formattedAvg50,
			String formattedAvg100,
			long single,
			long mo3,
			long avg5,
			long avg12,
			long avg50,
			long avg100) {
		this.formattedSingle = formattedSingle;
		this.formattedMo3 = formattedMo3;
		this.formattedAvg5 = formattedAvg5;
		this.formattedAvg12 = formattedAvg12;
		this.formattedAvg50 = formattedAvg50;
		this.formattedAvg100 = formattedAvg100;
		this.single = single;
		this.mo3 = mo3;
		this.avg5 = avg5;
		this.avg12 = avg12;
		this.avg50 = avg50;
		this.avg100 = avg100;
	}

	public String getFormattedSingle() {
		return formattedSingle;
	}

	public String getFormattedMo3() {
		return formattedMo3;
	}

	public String getFormattedAvg5() {
		return formattedAvg5;
	}

	public String getFormattedAvg12() {
		return formattedAvg12;
	}

	public String getFormattedAvg50() {
		return formattedAvg50;
	}

	public String getFormattedAvg100() {
		return formattedAvg100;
	}

	public long getSingle() {
		return single;
	}

	public long getMo3() {
		return mo3;
	}

	public long getAvg5() {
		return avg5;
	}

	public long getAvg12() {
		return avg12;
	}

	public long getAvg50() {
		return avg50;
	}

	public long getAvg100() {
		return avg100;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
