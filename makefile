all:	task

task:
	mvn clean package

db:	quartz
	# create database tasks first, then
	mysql -u root -p tasks < quartz/docs/dbTables/tables_mysql.sql

clean:
	rm -r target log

.PHONY: task db clean
