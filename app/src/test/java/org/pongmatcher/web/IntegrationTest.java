package org.pongmatcher.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pongmatcher.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
@WebAppConfiguration
public class IntegrationTest {

    private volatile JdbcTemplate jdbcTemplate;

    private volatile MockMvc mockMvc;

    @Autowired
    final void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    final void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveMvc() throws Exception {
        int previous = countRowsInTable("result");

        this.mockMvc.perform(post("/results")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"winner\": \"test-winner\", \"loser\": \"test-loser\", \"match_id\": \"test-match-id\"}"))
                .andExpect(status().isCreated());

        assertEquals(previous + 1, countRowsInTable("result"));
    }

    private int countRowsInTable(String tableName) {
        return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
    }

}
