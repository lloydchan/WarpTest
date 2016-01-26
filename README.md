============= Platform =========
OS: Window 7
Java: JRE 1.8.0_71
Apache Maven: 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-11T00:41:47+08:00)

============= Build =========
1) goto project dir (WarpTestHandGame) where pom.xml located 
2) Run below command to 2.1) clean target build directory 2.2) Download all dependencies library to target/dependency/ folder 2.3) package jar executable in target/ 
> mvn clean dependency:copy-dependencies package	
 
============= Run (Window) ===========
1) open command console 
2) goto project dir (WarpTestHandGame) where pom.xml located 
3) Run below command
> handgame.cmd

============= Future Enhancement ===========
1) Testing Database - increase sample size
2) Q-Learning - support n state prediction
3) DB Connection as individual thread -> callback to TwoStateDbConnector 

