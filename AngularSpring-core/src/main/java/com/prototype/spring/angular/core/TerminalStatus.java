/*******************************************************************************
 * Copyright (c) 2015 Uimirror.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of Apache V2.0
 * which accompanies this distribution, and is available at
 * 
 *
 * Contributors:
 * Uimirror.com. - initial API and implementation
 *******************************************************************************/
package com.prototype.spring.angular.core;

import org.springframework.util.StringUtils;

/**
 * @author Jay
 *
 */
public enum TerminalStatus {

	INACTIVE("IN"),
	ACTIVE("A"),
	DELETED("D");
	private final String status;
	
	TerminalStatus(String status){
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	@Override
	public String toString(){
		return this.status;
	}
	
	public static TerminalStatus getEnum(String status){
		if(StringUtils.isEmpty(status))
			return null;
		for(TerminalStatus st : TerminalStatus.values()){
			if(status.equalsIgnoreCase(st.getStatus())){
				return st;
			}
		}
		return null;
	}
	
}
