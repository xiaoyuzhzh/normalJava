package org.iceberg.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by xiaoyuzhzh on 6/28/2018.
 */
public class Test {
    public static void main(String[] args) {
        readFile();
        writeFile();
    }

    private static void readFile() {
        BufferedReader buf = null;
        String line = null;
        try {
            buf = new BufferedReader(new InputStreamReader(new FileInputStream("d:/dbSql.sql"), "utf-8"));
            while ((line = buf.readLine()) != null) {
                line = line.trim(); //去处空格
                System.out.println(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static void writeFile() {
        BufferedWriter buf = null;
        String line = "CREATE TABLE `os_comment` (\n" +
                "`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表记录标识号，数据库主键，不用于实际业务',\n" +
                "`order_no` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '订单号',\n" +
                "`driver_remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '供司机查看的用车备注',\n" +
                "`company_reason` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '供公司内部查看的用车理由',\n" +
                "`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '表记录创建时间',\n" +
                "`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '表记录修改时间',\n" +
                "PRIMARY KEY (`id`),\n" +
                "KEY `idx_create_time` (`create_time`) USING BTREE,\n" +
                "KEY `idx_update_time` (`update_time`) USING BTREE,\n" +
                "KEY `idx_order_no` (`order_no`) USING BTREE\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8 COMMENT='订单各种备注记录表'";
        try {
            buf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/dbSql4H2.sql"), "utf-8"));
            buf.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
