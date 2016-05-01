all:	task

task:
	mvn clean package

clean:
	rm -r target log
