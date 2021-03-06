package com.voidapp.ApplicationWeb.Musique;

public class Musique {

	private String id;
	private String title;   // titre de la chanson, sans son extension
	private String format;  // extension de la chanson, comme mp3 ou wav. Remarquez qu'il n'y a pas le '.'
	private String author;
	private String path;    // chemin d'accès
	
	/* Constructeur par défaut */
	public Musique() {
		
	}
	
	/* Constructeur avec paramètres */
	public Musique(String paramId, String paramTitle, String paramFormat, String paramAuthor, String paramPath) {
		this.id = paramId;
		this.title = paramTitle;
		this.format = paramFormat;
		this.author = paramAuthor;
		this.path = paramPath;
	}
	
	/* Accesseurs */
	public String getId() {
		return this.id;
	}
	public String getTitle() {
		return this.title;
	}
	public String getFormat() {
		return this.format;
	}
	public String getAuthor() {
		return this.author;
	}
	public String getPath() {
		return this.path;
	}
	
	/* Mutateurs */
	public void setId(String paramId) {
		this.id = paramId;
	}
	public void setTitle(String paramTitle) {
		this.title = paramTitle;
	}
	public void setFormat(String paramFormat) {
		this.format = paramFormat;
	}
	public void setAuthor(String paramAuthor) {
		this.author = paramAuthor;
	}
	public void setPath(String paramPath) {
		this.path = paramPath;
	}
	
	
}
