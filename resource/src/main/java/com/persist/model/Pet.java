package com.persist.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pet {
	public enum PetStatus {
		available(1, "available"), unavailable(2, "unavailable");

		private int value;
		private String code;

		PetStatus(int value, String code) {
			this.value = value;
			this.code = code;
		}

		public int getValue() {
			return value;
		}

		public String getCode() {
			return code;
		}

		public static PetStatus parse(int value) {
			PetStatus petStatus = null; // Default
			for (PetStatus item : PetStatus.values()) {
				if (item.getValue() == value) {
					petStatus = item;
					break;
				}
			}
			return petStatus;
		}
	};

	@Id
	@GeneratedValue
	private long id;
	private String name;

	// @PrimaryKeyJoinColumn
	@ManyToOne
	private Category category;
	// @OneToMany(mappedBy = "pet")
	// private List<Tag> tags = new ArrayList<Tag>();
	// @OneToMany(mappedBy = "pet")
	// private List<String> photoUrls = new ArrayList<String>();
	@ElementCollection
	private Set<String> photoUrls;

//	private PetStatus status;

	@Column(name = "STATUS")
	private int statusCode;

	public PetStatus getPetStatus() {
		return PetStatus.parse(this.statusCode);
	}

	public void setPetStatus(PetStatus petStatus) {
		this.statusCode = petStatus.getValue();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Pet(String name) {
		super();
		this.name = name;
	}

	public Pet() {

	}

	public Set<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(Set<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	/*
	 * public List<Tag> getTags() { return tags; }
	 * 
	 * public void setTags(List<Tag> tags) { this.tags = tags; }
	 * 
	 * public PetStatus getStatus() { return status; }
	 * 
	 * public void setStatus(PetStatus status) { this.status = status; }
	 */

}