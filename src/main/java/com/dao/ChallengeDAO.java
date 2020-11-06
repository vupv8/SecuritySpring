package com.dao;

import java.util.List;

import com.model.Challenge;

public interface ChallengeDAO {
	public Challenge getChallengeByChallengeid(int challengeid);
	public List<Challenge> getChallenge();
	public void setAddChallenge(Challenge challenge);

}