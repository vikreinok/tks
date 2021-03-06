package ee.ttu.catering.rest.model;

import org.jsondoc.core.annotation.ApiObject;

import javax.persistence.*;

@Entity
@Table(name = "image")
@ApiObject(name= "Image", description = "Contains bytes and file name attributes")
public class Image {

	@Id
	private String name;

	private String fileName;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Diner diner;

	@Column(nullable = false)
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] data;

	public Image() {
	}

	public Image(String name, byte[] data) {
		this.name = name;
		this.data = data;
	}

	public Image(String name, byte[] data, String fileName) {
		this(name, data);
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Diner getDiner() {
		return diner;
	}

	public void setDiner(Diner diner) {
		this.diner = diner;
	}
}
