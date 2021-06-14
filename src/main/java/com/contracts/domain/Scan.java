package com.contracts.domain;

public class Scan {			//Scan is a document
	
	public String fileName;
	public String text;
	public int pageCount;
	
	public Scan() {}
	
	public Scan(String fileName, String text, int pageCount) {
		this.fileName = fileName;
		this.text = text;
		this.pageCount = pageCount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "fileName = " + fileName + ", text = " + text + ", pageCount = " + pageCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scan other = (Scan) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (pageCount != other.pageCount)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
