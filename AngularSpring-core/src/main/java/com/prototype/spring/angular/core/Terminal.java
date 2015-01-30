package com.prototype.spring.angular.core;

import java.io.Serializable;
import java.util.UUID;

public class Terminal implements Serializable{
	
	private static final long serialVersionUID = -1581620531506875622L;

	private String id;
	private String name;
	private String locationName;
	private TerminalStatus status;
	private TerminalGroup group;
	private long createdOn;
	private long deletedOn;
	
	/**
	 * Update the terminal Id, 
	 * @param id
	 * @return
	 */
	public Terminal updateId(String id){
		return new TerminalBuilder(id).
				addName(name).addLocation(locationName).addGroup(group).
				addStatus(status).addCreatedOn(createdOn).addDeletedOn(deletedOn).build();
	}
	
	/**
	 * Delete a Terminal If it is in active stage
	 * @return {@link Terminal}
	 */
	public Terminal markAsDeleted(){
		if(group == null || TerminalStatus.ACTIVE != status)
			throw  new IllegalArgumentException();
		
		return new TerminalBuilder(id).
				addName(name).addLocation(locationName).addGroup(group).
				addStatus(TerminalStatus.DELETED).addCreatedOn(createdOn).addDeletedOn(System.currentTimeMillis()).build();
	}
	
	public static class TerminalBuilder{
		private String id;
		private String name;
		private String locationName;
		private TerminalStatus status;
		private long createdOn;
		private long deletedOn;
		private TerminalGroup group;
		
		public TerminalBuilder(String id){
			this.id = id;
		}
		
		public TerminalBuilder addName(String name){
			this.name = name;
			return this;
		}
		
		public TerminalBuilder addLocation(String loc){
			this.locationName = loc;
			return this;
		}
		
		public TerminalBuilder addStatus(String status){
			this.status = TerminalStatus.getEnum(status);
			return this;
		}

		public TerminalBuilder addStatus(TerminalStatus status){
			this.status = status;
			return this;
		}
		
		public TerminalBuilder addCreatedOn(long when){
			this.createdOn = when;
			return this;
		}
		
		public TerminalBuilder addDeletedOn(long when){
			this.deletedOn = when;
			return this;
		}

		public TerminalBuilder createId(){
			this.id = UUID.randomUUID().toString();
			return this;
		}
		
		public TerminalBuilder addGroup(TerminalGroup group){
			this.group = group;
			return this;
			
		}
		
		public Terminal build(){
			return new Terminal(this);
		}
	}
	
	private Terminal(TerminalBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.locationName = builder.locationName;
		this.status = builder.status;
		this.createdOn = builder.createdOn;
		this.deletedOn = builder.deletedOn;
		this.group = builder.group;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @return the status
	 */
	public TerminalStatus getStatus() {
		return status;
	}

	/**
	 * @return the createdOn
	 */
	public long getCreatedOn() {
		return createdOn;
	}

	/**
	 * @return the deletedOn
	 */
	public long getDeletedOn() {
		return deletedOn;
	}

	/**
	 * @return the group
	 */
	public TerminalGroup getGroup() {
		return group;
	}

}
