package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Challenge;
import org.springframework.jdbc.core.RowMapper;

public class ChallengeMapper implements RowMapper<Challenge> {

	@Override
	public Challenge mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		int challengeid = rs.getInt("challengeid");
		String suggest = rs.getString("suggest");
		String challengename = rs.getString("challengename");
		

		return new Challenge(challengeid, challengename,suggest);
	}
	

}