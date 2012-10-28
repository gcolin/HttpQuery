/**
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * @author Gael COLIN
 */
package org.httpclientquery.mock;

import java.io.Serializable;

public class Item<U,O> implements Serializable{

	private U u;
	private O o;
	
	public Item(U u,O o){
		this.o=o;
		this.u=u;
	}
	
	public U getU() {
		return u;
	}
	public void setU(U u) {
		this.u = u;
	}
	public O getO() {
		return o;
	}
	public void setO(O o) {
		this.o = o;
	}

	@Override
	public int hashCode() {
		return (o.hashCode()+43)*43+u.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Item))return false;
		Item<?, ?> i=(Item<?, ?>) obj;
		return equalsO(i) && equalsU(i);
	}

	private boolean equalsO(Item<?, ?> i) {
		return (o==null && i.o==null)||(o!=null && o.equals(i.o));
	}
	
	private boolean equalsU(Item<?, ?> i) {
		return (u==null && i.u==null)||(u!=null && u.equals(i.u));
	}
	
}
