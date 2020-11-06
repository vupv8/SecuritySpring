package com.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ChallengeDAO;
import com.mapper.ChallengeMapper;
import com.model.Challenge;

@Service
@Transactional
public class ChallengeDAOImpl extends JdbcDaoSupport implements ChallengeDAO {

	@Autowired
	public ChallengeDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	@Override
	public Challenge getChallengeByChallengeid(int challengid) {
		String sql = "Select * "//
				+ " from challenges where challengeid=? ";

		Object[] params = new Object[] { challengid };
		ChallengeMapper mapper = new ChallengeMapper();
		try {
			Challenge challenge = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return challenge;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	@Override
	public List<Challenge> getChallenge() {
		String sql = "Select * "//
				+ " from challenges";
		Object[] params = new Object[] { };
		ChallengeMapper mapper = new ChallengeMapper();
		try {
			List<Challenge> challenge = this.getJdbcTemplate().query(sql, params, mapper);
			return challenge;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void setAddChallenge(Challenge challenge) {


		String sql = "INSERT challenges SET challengename=?, suggest=?";

		Object[] params = new Object[] { challenge.getChallengename(), challenge.getSuggest() };
		try {
			this.getJdbcTemplate().update(sql, params);

		} catch (EmptyResultDataAccessException e) {

		}
	}
	

}