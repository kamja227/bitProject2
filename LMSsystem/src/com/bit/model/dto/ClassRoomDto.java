package com.bit.model.dto;

public class ClassRoomDto {
	private int roomNum;
	private int inUse;
	
	public ClassRoomDto(){}
	
	public ClassRoomDto(int roomNum, int inUse){
		this.roomNum = roomNum;
		this.inUse = inUse;
	}
	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getInUse() {
		return inUse;
	}

	public void setInUse(int inUse) {
		this.inUse = inUse;
	}

	@Override
	public String toString() {
		return "ClassRoomDto [roomNum=" + roomNum + ", inUse=" + inUse + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassRoomDto other = (ClassRoomDto) obj;
		if (roomNum != other.roomNum)
			return false;
		return true;
	}
	
	
}
