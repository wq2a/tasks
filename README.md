# tasks

```
change Protocol line 131 built path
```

- Ubuntu install jdk and maven

  sudo apt-get install default-jdk
  java -version
  echo $JAVA_HOME
  $JAVA_HOME
  vi .bash_profile
JAVA_HOME="/usr/lib/jvm/open-jdk"
  source .bash_profile 
  echo $JAVA_HOME


  wget http://mirrors.sonic.net/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
  tar -xvf apache-maven-3.3.9-bin.tar.gz 
  cd apache-maven-3.3.9/
  vi .profile
if [ -d "$HOME/apache-maven-3.3.9/bin" ] ; then
    PATH="$HOME/apache-maven-3.3.9/bin:$PATH"
fi
  source .profile
