package com.dxc.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployController {

	@RequestMapping("/EmployInsert")
	public ModelAndView insertEmploy(HttpServletRequest req, HttpServletResponse res) {
		int empno, basic;
		String name, dept, desig;
		empno = Integer.parseInt(req.getParameter("empno"));
		name = req.getParameter("name");
		dept = req.getParameter("dept");
		desig = req.getParameter("desig");
		basic = Integer.parseInt(req.getParameter("basic"));
		ApplicationContext ctx=new
	ClassPathXmlApplicationContext("applicationContext.xml");
				DataSource dataSource=
				(DriverManagerDataSource)ctx.getBean("dataSource");
				JdbcTemplate jt = new JdbcTemplate(dataSource);
		String cmd="insert into Employ values(?,?,?,?,?)";
		jt.update(cmd,new Object[]{empno,name,dept,desig,basic});
		return (new ModelAndView("InsertPage","message",
				"Record Inserted..."));

	}
	@RequestMapping("/employsearch")
	public ModelAndView searchEmploy(HttpServletRequest req, HttpServletResponse res) {
		int empno = Integer.parseInt(req.getParameter("empno"));
		ApplicationContext ctx=new
				ClassPathXmlApplicationContext("applicationContext.xml");
				DataSource dataSource=
				(DriverManagerDataSource)ctx.getBean("dataSource");
				JdbcTemplate jt = new JdbcTemplate(dataSource);
				String sql="select * from employ where Empno=?";
				List emps=null;
				emps=jt.query(sql,new Object[] {empno}, new RowMapper() {
					
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						// TODO Auto-generated method stub
						String empInfo=rs.getInt("empno")+"----"+
									rs.getString("name") +"----"+
								    rs.getString("dept") +"----"+
									rs.getString("desig")+"----"+
								    rs.getString("basic");
						return empInfo;
					}
				});
				return (new ModelAndView("EmployShowResult","emps",emps));
	}
	
	@RequestMapping("/employshow")
	public ModelAndView showEmploy() {
		ApplicationContext ctx=new
		ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource dataSource=
		(DriverManagerDataSource)ctx.getBean("dataSource");
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql="select * from employ";
		List emps=null;
		emps=jt.query(sql,new RowMapper() {
			
			public Object mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				String empInfo=rs.getInt("empno")+"----"+
							rs.getString("name") +"----"+
						    rs.getString("dept") +"----"+
							rs.getString("desig")+"----"+
						    rs.getString("basic");
				return empInfo;
			}
		});
		return (new ModelAndView("ShowPage","emps",emps));
	}
}
