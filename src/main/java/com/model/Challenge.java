package com.model;

public class Challenge {
	private int challengeid;
	private String challengename;
	private String suggest;

	public Challenge() {

	}

	public String getChallengename() {
		return challengename;
	}

	public void setChallengename(String challengename) {
		this.challengename = challengename;
	}

	public int getChallengeid() {
		return challengeid;
	}

	public void setChallengeid(int challengeid) {
		this.challengeid = challengeid;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public Challenge(int challengeid, String challengename, String suggest) {
		super();
		this.challengeid = challengeid;
		this.challengename = challengename;
		this.suggest = suggest;
	}

}