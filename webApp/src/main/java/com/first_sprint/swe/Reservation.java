package com.first_sprint.swe;

public class Reservation {
	private String checkin;
	private String checkout;
	private String id;
	private String hotel;
	private String room_type;
	
	public Reservation(String checkin, String checkout, String id, String hotel, String room_type) {
		this.checkin = checkin;
		this.checkout = checkout;
		this.id = id;
		this.hotel = hotel;
		this.room_type = room_type;
	}
	
	public String getCheckin() {
		return this.checkin;
	}
	
	public String getCheckout() {
		return this.checkout;
	}
	
	public String getID() {
		return this.id;
	}
	
	public String getHotel() {
		return this.hotel;
	}
	
	public String getRoomType() {
		return this.room_type;
	}
}
