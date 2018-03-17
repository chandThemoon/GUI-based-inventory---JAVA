/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inven;

/**
 *
 * @author chand
 */
public class Entry {

	String name;
	String quantity;
	String notes;

	Entry() {
		this.name = name;
		this.quantity = quantity;
		this.notes = notes;
	}

	Entry(String newname, String nq, String nn) {
		name = newname;
		quantity = nq;
		notes = nn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
