package com.util;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Util {
	public DataSource datasource = new ComboPooledDataSource();
}
