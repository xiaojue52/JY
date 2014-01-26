/**
 * 用于生产cabinet、device、detector的命名规则
 */

package com.station.generator;

import java.util.Properties;
import java.io.Serializable;
import org.hibernate.dialect.Dialect;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.*;
import org.hibernate.type.*;
import org.hibernate.util.PropertiesHelper;

public class JYPrimaryKeyGenerator extends TableGenerator {

	public static final String PREFIX = "prefix";
	private String prefix;

	public void configure(Type type, Properties params, Dialect d) {
		super.configure(type, params, d);
		prefix = PropertiesHelper.getString(PREFIX, params, "");
	}

	public synchronized Serializable generate(SessionImplementor session,
			Object obj) throws HibernateException {
		int val = ((Integer) super.generate(session, obj)).intValue();
		return prefix + val;
	}

}
