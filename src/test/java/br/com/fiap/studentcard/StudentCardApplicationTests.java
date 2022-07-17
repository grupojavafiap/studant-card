package br.com.fiap.studentcard;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import br.com.fiap.studentcard.batch.config.BatchTestConfiguration;

import static org.awaitility.Awaitility.*;

@ContextConfiguration(classes = {StudentCardApplication.class, BatchTestConfiguration.class})
@SpringBootTest
class StudentCardApplicationTests {

	@Autowired
 	private DataSource dataSource;

	@Test
	void contextLoads() throws SQLException {
		ResultSet resultSet = dataSource.getConnection()
				.prepareStatement("select count(*) from STUDENT")
				.executeQuery();
		resultSet.next();
		await().atMost(8, TimeUnit.SECONDS)
				.until(() -> resultSet.getInt(1) == 10929);
		assertEquals( 10929, resultSet.getInt(1));
	}
}
