package com.restaurant.app.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ibm.icu.text.SimpleDateFormat;

public class Utils {
	final static Logger logger = Logger.getLogger(Utils.class);
	public static String PATH_MYSQL_INSTALLER = "C:\\SistemaDePesaje\\mysql\\bin\\";

	public static Date convertToDate(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate convertoToLocalDate(Date dateToconvert) {
		return Instant.ofEpochMilli(dateToconvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static boolean isDebug() {
		return java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
				.indexOf("-agentlib:jdwp") > 0;
	}

	public static void generarBackup(String path) throws IOException {
		SimpleDateFormat formatDate = new SimpleDateFormat("ddmmyyyy_HHmm");
		String command = PATH_MYSQL_INSTALLER + "mysqldump -u root sist_pesada -p1234";
		logger.info(command);
		Process p = Runtime.getRuntime().exec(command);
		InputStream is = p.getInputStream();
		FileOutputStream fos = new FileOutputStream(path + "\\backup_" + formatDate.format(new Date()) + ".sql");
		byte[] buffer = new byte[1000];

		int leido = is.read(buffer);
		while (leido > 0) {
			fos.write(buffer, 0, leido);
			leido = is.read(buffer);
		}

		fos.close();
	}

	public static void restaurarBackup(String path) throws IOException {
		String command = PATH_MYSQL_INSTALLER + "mysql -u root -p1234 sist_pesada < " + path;
		logger.info(command);
		Runtime.getRuntime().exec(command);
	}

	public static boolean restoreDB(String path) {
		String[] executeCmd = new String[] { PATH_MYSQL_INSTALLER + "mysql", "--user=root", "--password=1234", "sist_pesada", "-e",
				" source " + path };
		Process runtimeProcess;
		try {
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				System.out.println("Backup restored successfully");
				return true;
			} else {
				System.out.println("Could not restore the backup");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
