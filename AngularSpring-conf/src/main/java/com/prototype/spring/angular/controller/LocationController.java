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
package com.prototype.spring.angular.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.owlike.genson.Genson;

/**
 * @author Jay
 */
@RestController
@RequestMapping("/")
public class LocationController {
	
	@RequestMapping(method = RequestMethod.GET, value="/lookup/loc/{groupId}")
	public String location(@PathVariable String groupId) {
		Map<String, String> loc = new WeakHashMap<String, String>();
		loc.put("id", "1");
		loc.put("name", "Delhi");
		List<Map<String, String>> locs = new ArrayList<Map<String, String>>();
		locs.add(loc);
		return new Genson().serialize(locs);
	}

}
